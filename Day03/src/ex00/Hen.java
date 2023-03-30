package ex00;

public class Hen extends Thread{
    private final int num;

    public Hen(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            System.out.println("Hen");
        }
    }
}
