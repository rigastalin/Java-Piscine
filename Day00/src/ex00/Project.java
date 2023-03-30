package ex00;

public class Project {
    public static void main(String[] args) {
        int num = 479598;
        int res = 0;

        for (int i = 0; i < 6; i++) {
            res += num % 10;
            num /= 10;
        }
        System.out.println(res);
    }
}
