package ex00;

public class Egg extends Thread{
    private final int num;

    public Egg(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            System.out.println("Egg");
        }
    }
}
