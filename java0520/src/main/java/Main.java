import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

abstract class Player{
    private String name;
    private String initNumber;
    private List<String> fightList = new LinkedList<String>();
    private Boolean isEnd;
    public Player(String name, String initNumber) {
        this.name = name;
        this.initNumber = initNumber;
        this.isEnd = false;
    }
    public abstract void play(String number) throws Exception;
    public void addFightList(String number) { fightList.add(number); }
    public Boolean getIsEnd() { return isEnd; }
    public void setIsEnd(Boolean isEnd) { this.isEnd = isEnd; }
    public String getInitNumber() { return initNumber; }
    public String getName() { return name; }
    public void printFightList() {
        // TODO
        System.out.println(this.name);
        System.out.println("---------------------------------");

        System.out.println("---------------------------------");

    }
}
class PlayerATeam extends Player
{
    public PlayerATeam(String name, String number) {
        super(name, number);
    }

    // 숫자야구 숫자 부르기
    @Override
    public void play(String number) throws Exception {
        int strike = 0;
        int ball = 0;
        int out = 0;

        // 문자열 길이가 4인지?
        if (number.length() > 5) {
            System.out.println("문자열 길이가 4를 초과했습니다.");
            return;
        }

        // TODO
        // number로 받은 숫자의 자리수를 체크해서 정답과 비교 1234
        // 자리와 숫자가 일치하면 strike, 숫자만 일치하면 ball, 해당 숫자가 없으면 out
        for (int i=0; i<4; i++) {
            String mine = number.charAt(i) + "";
            String correct = this.getInitNumber().charAt(i) + "";

            if (mine.equals(correct)) {
                strike++;
            } else if (this.getInitNumber().contains(mine)) {
                ball++;
            } else {
                out++;
            }
        }

        if(strike == 4) {
            System.out.println("Congratulation!");
            super.setIsEnd(true);
        } else {
            System.out.println("Strike : "+strike+", Ball :"+ball+", Out: "+out);

        }
    }
}

class PlayerBTeam extends Player
{
    public PlayerBTeam(String name, String number) {
        super(name, number);
    }
    @Override
    public void play(String number) throws Exception {
        int strike = 0;
        int ball = 0;
        int out = 0;

        // 문자열 길이가 4인지?
        if (number.length() > 5) {
            System.out.println("문자열 길이가 4를 초과했습니다.");
            return;
        }

        // TODO
        for (int i=0; i<4; i++) {
            String mine = number.charAt(i) + "";
            String correct = this.getInitNumber().charAt(i) + "";

            if (mine.equals(correct)) {
                ball++;
            } else if (this.getInitNumber().contains(mine)) {
                strike++;
            } else {
                out++;
            }
        }


        if(ball == 4) {
            System.out.println("Congratulation!");
            super.setIsEnd(true);
        } else {
            System.out.println("Strike : "+strike+", Ball :"+ball+", Out: "+out);
        }
    }
}

public class Main {

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Player playerArray[] = {new PlayerATeam("A Team","1234"), new PlayerBTeam("B Team","5678")};
        int checkPlayer = 0;

        while(true) {
            checkPlayer = 0;
            for(Player player : playerArray) {
                if (player.getIsEnd() == false) {
                    System.out.print("Please enter a 4 digit number (" + player.getName() + ") defence : ");
                    String number = scanner.nextLine();
                    try {
                        player.play(number);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    checkPlayer++;
                }
            }
            if(checkPlayer == playerArray.length) break;
        }

        for(Player player : playerArray) {
            player.printFightList();
        }
    }
}