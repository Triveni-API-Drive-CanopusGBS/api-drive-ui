package HackerRankJavaPractice.Strings;

import java.util.Scanner;

public class isPalindrome_Own {
    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String A=sc.next();
        /* Enter your code here. Print output to STDOUT. */

        int n= A.length();
        StringBuffer str1 = new StringBuffer();
        StringBuffer str2 = new StringBuffer();
        StringBuffer str3 = new StringBuffer();
        StringBuffer str4 = new StringBuffer();
        str1 = str1.append(A.substring(0, n/2));
        str2 = str2.append(A.substring(n/2, A.length())).reverse();

        str3 = str3.append(A.substring(0, n/2+1));
        str4 = str4.append(A.substring(n/2, A.length())).reverse();


        if(n%2==0 && str1.toString().compareTo(str2.toString())==0){
            System.out.println("Yes");
        }
        else if(n%2==1 && str3.toString().compareTo(str4.toString())==0){
            System.out.println("Yes");
        }
        else System.out.println("No");

    }

}
