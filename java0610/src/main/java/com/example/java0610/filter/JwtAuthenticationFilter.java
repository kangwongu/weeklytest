package com.example.java0610.filter;

import com.example.java0610.util.JwtTokenCreator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// JWT 사용 위한 필터
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String tokenFromRequest = getTokenFromRequest(request);

            if (JwtTokenCreator.validateToken(tokenFromRequest)) {
                String email = JwtTokenCreator.getInfoFromToken(tokenFromRequest);

                // 시큐리티 인증
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                request.setAttribute("unauthorization", "인증 실패");

            }
        } catch (Exception e) {
            System.out.println("인증 예외 발생");
        }

        filterChain.doFilter(request, response);
    }

    // 요청으로 서버에 접근 시, 쿠키에서 토큰을 확인
    private String getTokenFromRequest(HttpServletRequest request) {
        Cookie token = WebUtils.getCookie(request, "token");
        return token.getValue();
    }
}
