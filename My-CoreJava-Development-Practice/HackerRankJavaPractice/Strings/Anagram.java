package HackerRankJavaPractice.Strings;

import java.util.Scanner;

public class Anagram {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String str1 = sc.next();
        String str2 = sc.next();
        System.out.println(isAnagram(str1,str2)?"Anagrams":"Not Anagrams");
            }
    public static boolean isAnagram(String a,String b)
    {
        a = a.toLowerCase();
        b = b.toLowerCase();
        char[] al = a.toCharArray();
        char[] bl = b.toCharArray();
        //sorting them by alphabetic order
        java.util.Arrays.sort(al);
        java.util.Arrays.sort(bl);
        return java.util.Arrays.equals(al,bl);
    }

}
