package HackerRankJavaPractice.Strings;

import java.util.Collections;
import java.util.Scanner;

public class Java_Substring {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String str  = sc.next();
        int i1 = sc.nextInt();
        int i2 = sc.nextInt();
        sc.close();

        System.out.println(str.substring(i1,i2));

    }
}

