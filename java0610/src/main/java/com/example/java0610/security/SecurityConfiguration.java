package com.example.java0610.security;

import com.example.java0610.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();

        // 세션 안씀
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 인증 부분
        http.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/create/**").permitAll()
                .antMatchers("/refresh/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable();

        return http.build();

    }

}
