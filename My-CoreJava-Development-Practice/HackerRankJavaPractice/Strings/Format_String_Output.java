package HackerRankJavaPractice.Strings;

import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Format_String_Output {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String A=sc.next();
        String B=sc.next();
        /* Enter your code here. Print output to STDOUT. */

        System.out.println(A.length()+B.length());
        String bool = A.charAt(0)> B.charAt(0)? "Yes":"No";
        System.out.println(bool);

        A= A.substring(0,1).toUpperCase()+A.substring(1,A.length());
        B= B.substring(0,1).toUpperCase()+B.substring(1,B.length());

        System.out.println(A+" "+B);
    }
}
