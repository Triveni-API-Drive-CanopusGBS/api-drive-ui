package HackerRankJavaPractice.Strings;

import java.util.Scanner;

public class SplitString_Into_MultipleStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine().trim();   //Take a sentence and trim ends


        if (str.isEmpty()) {  // Or just to str.length()==0
            System.out.println(0);
            return;  //return nothing
        }

        String[] str_arr = str.split("[!,?._'@ ]+");
        System.out.println(str_arr.length);
        for (String s : str_arr) {
            System.out.println(s);
        }
        sc.close();
    }
}
