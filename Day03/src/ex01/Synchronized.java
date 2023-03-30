package ex01;

public class Synchronized {
    private Category typeOfMsg = Category.EGG;

    private enum Category {
        EGG, HEN
    }

    public synchronized void synchronization(String str1, String str2) {
        try {
            Category typeOfMsgInput  = Category.valueOf(str1.toUpperCase());
            if (typeOfMsgInput != typeOfMsg) {
                wait();
            } else {
                System.out.println(str1);
                typeOfMsg = Category.valueOf(str2);
                notify();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
