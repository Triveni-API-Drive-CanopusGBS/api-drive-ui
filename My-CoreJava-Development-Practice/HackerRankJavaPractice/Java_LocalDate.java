package HackerRankJavaPractice;

import java.time.LocalDate;
import java.util.Scanner;

public class Java_LocalDate {
    public static void main(String[] argh) {
        //String month = in.next();
        Scanner in = new Scanner(System.in);
        int mm = in.nextInt();
        //String day = in.next();
        int dd = in.nextInt();
        //String year = in.next();
        int yy = in.nextInt();
        in.close();
        LocalDate dt = LocalDate.of(yy, mm, dd);
        System.out.print(dt.getDayOfWeek());
    }
}