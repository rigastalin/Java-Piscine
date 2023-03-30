package ex02;

import java.util.Arrays;

public class Calculation extends Thread {
    private final int[] arr;
    private final int[] res;
    private final int inputNum;

    public Calculation(int[] arr, int[] res, int inputNum) {
        this.arr = arr;
        this.res = res;
        this.inputNum = inputNum;
        start();
    }

    @Override
    public void run() {
        res[inputNum] = Arrays.stream(arr).sum();
    }

    public int getArr(int index) {
        return res[index];
    }
}
