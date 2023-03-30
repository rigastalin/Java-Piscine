package ex00;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.err.println("Wrong input");
            System.exit(-1);
        }

        try {
            int inputNum = Integer.parseInt(args[0].substring("--count=".length()));
            if (inputNum <= 0) {
                System.err.println("Number must be bigger than 0");
                System.exit(-1);
            }

            Egg threadEgg = new Egg(inputNum);
            Hen threadHen = new Hen(inputNum);

            threadEgg.start();
            threadHen.start();

            threadEgg.join();
            threadHen.join();

            for (int i = 0; i < inputNum; i++) {
                System.out.println("Human");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
