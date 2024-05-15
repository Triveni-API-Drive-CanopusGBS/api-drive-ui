package HackerRankJavaPractice;

import java.util.Scanner;

public class Java_EndOfFile {

    public static void main(String[] args) {
        int id = 0;
        Scanner sc= new Scanner(System.in);
        while(sc.hasNext()){
            id++;
            String s1= sc.nextLine();
            System.out.println(id + " "+s1);
        }
    }
}

/*
https://www.hackerrank.com/challenges/java-end-of-file/problem?isFullScreen=true&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen
while(sc.hasNext());*/
