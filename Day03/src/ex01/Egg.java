package ex01;

public class Egg extends Thread{
    private int num;
    private Synchronized sync;

    public Egg(int num, Synchronized sync) {
        this.num = num;
        this.sync = sync;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            try {
                sync.synchronization("Egg", "HEN");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
