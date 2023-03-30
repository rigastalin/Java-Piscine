package ex01;

import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {

        try {
            BufferedReader reader1 = new BufferedReader(new FileReader(args[0]));
            BufferedReader reader2 = new BufferedReader(new FileReader(args[1]));

            String str1 = reader1.readLine();
            String[] arr1 = str1.split(" ");
            reader1.close();

            String str2 = reader2.readLine();
            String[] arr2 = str2.split(" ");
            reader2.close();

            Set<String> dictionary = new HashSet<String>();
            Collections.addAll(dictionary, arr1);
            Collections.addAll(dictionary, arr2);

            int[] vec1 = new int[dictionary.size()];
            int[] vec2 = new int[dictionary.size()];

            for (String n : arr1) {
                int i = Arrays.stream(dictionary.toArray()).toList().indexOf(n);
                vec1[i] += 1;
            }

            for (String m : arr2) {
                int i = Arrays.stream(dictionary.toArray()).toList().indexOf(m);
                vec2[i] += 1;
            }

            if (vec1 != null && vec2 != null) {
                double similarity = calc(vec1, vec2);
                String res = String.format("%.2f", similarity);
                System.out.println("Similarity = " + res);
            } else {
                System.out.println("Similarity = 0");
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("Dictionary.txt"));
            for (String n : dictionary) {
                writer.write(n);
                writer.newLine();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("There must be 2 valid arguments");
        } catch (Exception e) {
            System.err.println("Similarity = 0");
        }
    }


    private static double calc(int[] vec1, int[] vec2) {
        double numerator = 0;
        double den1 = 0;
        double den2 = 0;
        double similarity  = 0;
        for (int i = 0; i < vec1.length; ++i) {
            numerator += vec1[i] * vec2[i];
            den1 += vec1[i] * vec1[i];
            den2 += vec2[i] * vec2[i];
        }

        if (den1 > 0 || den2 > 0) {
            similarity  = numerator / (Math.sqrt(den1) * Math.sqrt(den2));
        }
        return similarity;
    }
}
