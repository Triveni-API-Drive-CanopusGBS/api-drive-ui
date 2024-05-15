package HackerRankJavaPractice;

//import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Scanner;

public class Java_Datatypes {
    public static void main(String []argh)
    {
        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();

        for(int i=0;i<t;i++)
        {
            try
            {
                long x=sc.nextLong();
                System.out.println(x+" can be fitted in:");

               /*
               Approach 1: Mine
                if(x>=-128 && x<=127)System.out.println("*byte"+"\n*short"+"\n*int"+"\n*long");
                else if(x>=-32768 && x<=32767)System.out.println("*short"+"\n*int"+"\n*long");
                else if(x>=-2147483648 && x<=2147483647)System.out.println("*int"+"\n*long");
                else System.out.println("*long");
                */

                //Approach : 2  Dont go with if else, just go with multiple if only
                if(x>=-128 && x<=127)System.out.println("* byte");
                //Complete the code
                if(x >= -Math.pow(2, 15) && x <= Math.pow(2, 15) - 1)
                    System.out.println("* short");
                if(x >= -Math.pow(2, 31) && x <= Math.pow(2, 31) - 1)
                    System.out.println("* int");
                if(x >= -Math.pow(2, 63) && x <= Math.pow(2, 63) - 1)
                    System.out.println("* long");

                /*
                if(x>=Byte.MIN_VALUE && x<=Byte.MAX_VALUE) System.out.println("* byte");
                else if(x>=Short.MIN_VALUE && x<=Short.MAX_VALUE) System.out.println("* short");
                else if(x>=Integer.MIN_VALUE && x<=Integer.MAX_VALUE) System.out.println("* int");
                else  System.out.println("* long"); // No need of condition since it is the last condition -
                // else if (x>=Long.MIN_VALUE && x<=Long.MAX_VALUE) else  System.out.println("* long");
                // else throw new Exception();
                */
            }
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println(sc.next()+" can't be fitted anywhere.");
            }

        }
    }
}


//https://www.hackerrank.com/challenges/java-datatypes/problem?isFullScreen=true&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen&h_r=next-challenge&h_v=zen