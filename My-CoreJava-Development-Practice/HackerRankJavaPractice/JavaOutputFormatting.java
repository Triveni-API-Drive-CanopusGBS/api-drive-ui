package HackerRankJavaPractice;

//import com.sun.javafx.binding.StringFormatter;

import java.util.ArrayList;
import java.util.Scanner;

public class JavaOutputFormatting {
    public static void main(String[] args) {
        String y = "";
        String s = "";
        int x;
        ArrayList<String>  str = new ArrayList<>(2); //You need to use List or Arraylist to add the strings to the String[]
        Scanner sc = new Scanner(System.in);
        System.out.println("================================");
        for (int i = 0; i < 3; i++) {
            s = sc.next();
            x = sc.nextInt();
            s=String.format("%-15s",s );
            y=String.format("%03d", x);
            //System.out.printf( "%-15s%03d\n", s, x);
            str.add(s+y);
        }
        System.out.println(str.get(0)+"\n"+str.get(1)+"\n"+str.get(2));
        System.out.println("================================");
        sc.close();
        }
    }

/*
https://www.hackerrank.com/challenges/java-output-formatting/problem?isFullScreen=true&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen

            System.out.printf("%-15s", s1);
            System.out.printf("%03d%n", x);
            System.out.printf( "%-15s%03d\n", s1, x);

            s1=s1+"               ";
            if(0<=x && x<=9) {
                y= "00"+x;
                str.add(s1+y);
            }else if (10<=x && x<=99){
                 y= "0"+x;
                System.out.println(s1+y);
            }else  {
                y=""+x;
                System.out.println(s1+y);
            }

 */