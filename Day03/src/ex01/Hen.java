package ex01;

public class Hen extends Thread{
    private int num;
    private Synchronized sync;

    public Hen(int num, Synchronized sync) {
        this.num = num;
        this.sync = sync;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            try {
                sync.synchronization("Hen", "EGG");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
