package StudentDatabaseAdmin;

import java.util.Scanner;

public class StudentEnrollmentApp_Second_Approach_Do_While {
    public static void main(String[] args) {            //Driver code of the application.
        //loop to enter multiple student details.
        while (true) {
            System.out.print("Do you want to enter new Student info? (Press Y to enter and Q to Quit): ");
            Scanner sc = new Scanner(System.in);
            String newStudent = sc.nextLine();
            if (newStudent.equals("Y")) {
                Student s1 = new Student();
                System.out.println(s1.showInfo());
                System.out.println(s1); // this implies s1.tostring() by default and is used to print the entire student object.
            } else if (newStudent.equals("Q")) {
                break;
            }
        }
        System.out.print("Have A Nice Day!!");
    }
//    Student s1= new Student();
//    s1.toString();  // writing code outside main gives error, "Can not resolve symbol "String".
}
