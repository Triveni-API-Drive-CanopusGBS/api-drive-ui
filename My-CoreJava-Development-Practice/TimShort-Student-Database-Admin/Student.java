package StudentDatabaseAdmin;

import java.util.Scanner;

public class Student {
    private static int id = 1000;   //Static variable meaning it is available to all student objects and its value is same.
    private final String firstName;
    private final String lastName;
    private final int costOfEachCourse = 600;
    private final int gradYear;
    private int tuitionBalance = 0;
    private String payment;
    private String courses = "";
    private String studentId;

    // Constructor to populate the name and year.
    // Driving Code of the student class.
    public Student() {
        System.out.print("Enter the student Firstname: ");
        Scanner sc1 = new Scanner(System.in);
        this.firstName = sc1.nextLine();
        System.out.print("Enter the student Lastname: ");
        Scanner sc2 = new Scanner(System.in);
        this.lastName = sc2.nextLine();
        System.out.println(" 1 - Freshmen "+"\n 2 - Sophomore"+"\n 3 - Junior "+"\n 4 - Senior ");
        System.out.print("Enter the Student GradYear: ");
        Scanner sc3 = new Scanner(System.in);
        this.gradYear = sc3.nextInt();
        generateStudentId();
        System.out.println("Name: " + firstName + " " + lastName + "\nGrade Level: " + gradYear + "\nStudentID: " + studentId);
        enrollInCourses();  //You can also call these form the main method,
        viewBalance();
        payTheBalance();
        toString();
    }

    // Generate ID
    public void generateStudentId() {
        id++;
        this.studentId = gradYear + "" + id; // I took grad year and id as int, So I converted them to String by adding "" since my student id and grad year are strings.
    }

    //Enroll in courses
    public void enrollInCourses() {
        //Get inside a loop to enter multiple courses until Q is pressed.
        do {
            System.out.println("Please choose the courses you wish to enroll (press Q for Quit): ");
            Scanner sc = new Scanner(System.in);
            String addNewCourse = sc.nextLine();
            if (!addNewCourse.equals("Q")) {
                this.courses = courses + addNewCourse + " ";
                tuitionBalance = tuitionBalance + costOfEachCourse;
            } else {break;}
        } while (1 != 0);
        System.out.println("Enrolled courses by the student in the current sem :" + courses); //To see the tuition balance and courses at the end of loop.
    }

    // view balance
    public void viewBalance() {
        System.out.println("Balance tuition of the student for the enrolled courses: " + tuitionBalance + "\n");
    }

    //pay the balance
    public void payTheBalance() {
        System.out.print
                ("Do you want to pay the balance?" + "\nIf you want to pay the balance, enter the $amount if not enter 0: ");

        Scanner sc = new Scanner(System.in);
        int payment = sc.nextInt();
        tuitionBalance -= payment;
        viewBalance();
    }
    public String showInfo() {
       return  "NAME: "+ firstName+" "+lastName+
                "\nCourses Enrolled: "+ courses+
                "\nBalance: "+ tuitionBalance;
    }
    //show all the details of the class by using a regular tostring method of the class or you can have a seperate method for it.

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tuitionBalance=" + tuitionBalance +
                ", courses='" + courses + '\'' +
                ", studentId='" + studentId + '\'' +
                ", gradYear='" + gradYear + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getTuitionBalance() {
        return tuitionBalance;
    }

    public void setTuitionBalance(int tuitionBalance) {
        this.tuitionBalance = tuitionBalance;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getGradYear() {
        return gradYear;
    }
}
