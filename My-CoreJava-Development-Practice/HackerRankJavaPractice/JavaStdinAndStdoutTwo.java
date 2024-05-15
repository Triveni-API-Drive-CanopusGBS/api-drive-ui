package HackerRankJavaPractice;

import java.util.Scanner;

public class JavaStdinAndStdoutTwo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt(); // Leaves the invisible \n
        double d= scan.nextDouble(); // Leaves the invisible \n
        scan.nextLine(); // gets rid of the pesky newline (\n gets skipped due to this)
        String s = scan.nextLine();  // It stops when \n is called.
        String s1 = scan.nextLine();  // if you want to read more lines

        // Write your code here.
        //System.out.println("String: " + s1);
        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);
    }
}
// https://www.hackerrank.com/challenges/java-stdin-stdout/problem?isFullScreen=true&h_r=next-challenge&h_v=zen