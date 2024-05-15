package HackerRankJavaPractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Java_Loops_I {
    public static void main(String[] args) throws IOException {
        int result=0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine().trim());
        for(int i=1;i<11;i++){
            result = N*i;
            System.out.println(N+" x "+i+ " = "+ result);
        }
        bufferedReader.close();
    }
}

/*
https://www.hackerrank.com/challenges/java-loops-i/problem?isFullScreen=true&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen
 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
 int N = Integer.parseInt(bufferedReader.readLine().trim());
 */