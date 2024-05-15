package HackerRankJavaPractice;

import java.util.Scanner;

public class Java_Loops_II {
    public static void main(String []argh){
        Scanner in = new Scanner(System.in);
        int t=in.nextInt(); //Skips \n
        for(int i=0;i<t;i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            int sum =a;
            for( int j=0;j<n;j++){
                int x = (int) Math.pow(2,j);
                sum = sum + (x * b) ;
                System.out.print(sum+" ");
                //System.out.print(""+(a+=Math.pow(2,j)*b)+" ");}
            }
            System.out.println("");
        }
        in.close();
    }
}
/*
https://www.hackerrank.com/challenges/java-loops/problem?isFullScreen=true&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen
 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
 int N = Integer.parseInt(bufferedReader.readLine().trim());
 */