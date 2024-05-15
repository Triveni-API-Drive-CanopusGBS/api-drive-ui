package HackerRankJavaPractice.Strings;

import java.util.Scanner;

public class LexicologicalOrderOf_Strings_NormalFormat {
    static  String smallest = "";
   static String largest = "";

    @Override
    public String toString() {
        return "LexicologicalOrderOf_Strings_NormalFormat{}";
    }
// Complete the function
    // 'smallest' must be the lexicographically smallest substring of length 'k'
    // 'largest' must be the lexicographically largest substring of length 'k'

    private  static String  getSmallestAndLargest(String s, int k) {
        smallest = s.substring(0,k); //Make the first sub string as smallest , then compare
        for(int i=0; i<=s.length()-k; i++){  //coz w which is 0th index is alredy checked initially
             String str = s.substring(i, i+k); //elc, lco....

            if(str.compareTo(smallest)<0){
                smallest = str;
            }

            else if(str.compareTo(largest)>0){
                largest = str;
            }

        }
        return smallest + "\n" + largest;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int k = scan.nextInt();
        scan.close();

        System.out.println(getSmallestAndLargest(s, k));
    }


}
