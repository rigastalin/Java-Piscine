package ex01;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.err.println("Wrong input");
            System.exit(-1);
        }

        try {
            Synchronized sync = new Synchronized();
            int inputNum = Integer.parseInt(args[0].substring("--count=".length()));
            if (inputNum <= 0) {
                System.err.println("Number must be bigger than 0");
                System.exit(-1);
            }
            Egg threadEgg = new Egg(inputNum, sync);
            Hen threadHen = new Hen(inputNum, sync);
            threadEgg.start();
            threadHen.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
