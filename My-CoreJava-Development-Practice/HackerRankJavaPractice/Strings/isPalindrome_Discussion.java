package HackerRankJavaPractice.Strings;

import java.util.Scanner;

public class isPalindrome_Discussion {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String s = sc.next();
        StringBuffer sb= new StringBuffer();
        sb.append(s).reverse();

       if(s.compareTo(sb.toString())==0) System.out.println("Yes");
       //Use toString() to convert StringBuffer to String

       else System.out.println("No");

    }
}
