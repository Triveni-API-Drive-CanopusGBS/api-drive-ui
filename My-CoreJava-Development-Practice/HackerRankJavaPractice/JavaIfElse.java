package HackerRankJavaPractice;

import java.util.Scanner;

public class JavaIfElse {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter a number between 1 and 100");
        int N = scanner.nextInt();
        if (!(1<=N && N<=100)) System.out.println("You entered a number outside of 1 and 100, Please enter in between the range. ");
        if(1<=N && N<=100) {  // While or If can be used on this line
            if (N % 2 != 0) {
                System.out.println("Weird");
            } else if ((N % 2 == 0) && (2 <= N) && (N <= 5)) {  //2<=N<=6 does not work and give compilation errors
                System.out.println("Not Weird");
            } else if ((N % 2 == 0) && (6 <= N) && (N <= 20)) {
                System.out.println("Weird");
            } else System.out.println("Weird");   // for single line you dont need  {}

            //Advanced level of coding
            System.out.println((N % 2 == 1 || (N >= 6 && N <= 20)) ? "Weird" : ((N >= 2 && N <= 5) || N > 20) ? "Not Weird" : "Weird");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            scanner.close();
        }
    }
}

// Description can be found here - https://www.hackerrank.com/challenges/java-if-else/problem?isFullScreen=true
