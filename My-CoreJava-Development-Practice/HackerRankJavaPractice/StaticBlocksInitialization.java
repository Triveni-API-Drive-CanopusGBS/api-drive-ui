package HackerRankJavaPractice;

import java.util.Scanner;

public class StaticBlocksInitialization {
    static int B,H;
    static boolean  flag;
    static{
         Scanner scan = new Scanner(System.in);
          B = scan.nextInt();
          H = scan.nextInt();
          scan.close();  //Scan.close can only be  written inside a method,constructor or static block
          flag = true; //by default flag is set to true, but will be changed if the conditions did not meet like the constraints.
          scan.close();
        try{
            if(B <= 0 || H <= 0){
                flag = false;
                throw new Exception("Breadth and height must be positive"); //No need of else case, just simple if condition.
            }
        }catch(Exception e){
            System.out.println(e);
        }

        /*if(B>0 && H>0){
            flag = true;

        }else if(B<=0 || H<=0){
            flag=false;
            System.out.println("java.lang.Exception: Breadth and height must be positive");   */

    }
    public static void main(String[] args){
        if(flag){
            int area=B*H;
            System.out.print(area);
        }

    }//end of main

}//end of class//https://www.hackerrank.com/challenges/java-static-initializer-block/problem?isFullScreen=true