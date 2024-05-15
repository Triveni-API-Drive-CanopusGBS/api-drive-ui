package StudentDatabaseAdmin;

import java.util.Scanner;

public class StudentEnrollmentApp {
    public static void main(String[] args) {            //Driver code of the application.
        //loop to enter multiple student details and add them to student array
        System.out.print("How many new Students do you want to add: ");
        Scanner sc = new Scanner(System.in);
        int numberOfStudents = sc.nextInt();
        Student[] students = new Student[numberOfStudents];
        //Create n number of new Students
        for (int i = 0; i < numberOfStudents; i++) {
            //Students[0]=new student();
            students[i] = new Student();  // Loop number is nothing but array index, you will add a new student at every index position.
            System.out.println(students[i].showInfo());
        }
        //We need another loop to print all the students of the array.
        // System.out.println(students); This lin of code PRINTS HASH CODE only and not the actual content of the object.
        System.out.println("\n\nPrint out all the list of Students: ");
        for (int i = 0; i < numberOfStudents; i++){
            System.out.println(students[i]); // This implies student.tostring() by default and is used to print the entire student object.
        }
        System.out.print("Have A Nice Day!!");
    }
//    Student s1= new Student();
//    s1.toString();  // writing code outside main gives error, "Can not resolve symbol "String".
}
