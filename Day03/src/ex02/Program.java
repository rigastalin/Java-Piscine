package ex02;

import java.util.Arrays;
import java.util.Random;

public class Program {
    public static void main(String[] args) {
        int sum = 0, temp = 0, sectionSize;

        if (args.length != 2 || !args[0].startsWith("--arraySize=") ||
                !args[1].startsWith("--threadsCount=")) {
            System.err.println("Wrong input");
            System.exit(-1);
        }
        int arrSize = Integer.parseInt(args[0].substring("--arraySize=".length()));
        int threadSize = Integer.parseInt(args[1].substring("--threadsCount=".length()));

        if (arrSize < 0 || threadSize < 0) {
            System.err.println("Sizes must be bigger than 0");
            System.exit(-1);
        }

        if (arrSize > 2000000 || threadSize > arrSize) {
            System.err.println("Not right sizes");
            System.exit(-1);
        }

        int[] arr = new int[arrSize];
        int[] res = new int[threadSize];
        sectionSize = (int)round((double) arrSize / threadSize);

        Random random = new Random();
        for (int i = 0; i < arrSize; ++i) {
            arr[i] = random.nextInt(1000);
//            arr[i] = 1;
        }
        System.out.println("Sum: " + Arrays.stream(arr).sum());

        for (int i = 0; i < threadSize; i++) {
            int[] arrThread = new int[sectionSize];
            if (i < threadSize - 1) {
                System.arraycopy(arr, sectionSize * i, arrThread, 0, sectionSize);
            } else {
                System.arraycopy(arr, sectionSize * i, arrThread, 0, arr.length - sectionSize * i);
            }
            Calculation calc = new Calculation(arrThread, res, i);
            temp = calc.getArr(i);
            sum += temp;
            try {
                calc.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int lastSect = 0;
        for (int i = 0; i < res.length; i++) {
            if (i != res.length - 1) {
                System.out.println("Thread " + (i + 1) + ": from " + sectionSize * i + " to " + (sectionSize * i + (sectionSize - 1)) +
                        " sum is " + res[i]);
            } else {
                lastSect = arrSize - 1;
                System.out.println("Thread " + (i + 1) + ": from " + sectionSize * i + " to " + lastSect +
                        " sum is " + res[i]);
            }
        }
        System.out.println("Sum by threads: " + Arrays.stream(res).sum());

    }

    private static double round(double val) {
        return (int)val < val ? (int)val + 1 : val;
    }
}
