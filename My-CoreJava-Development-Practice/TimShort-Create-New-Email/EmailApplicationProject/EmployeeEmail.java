package TimShort_RealWorldApplications.EmailApplicationProject;

import java.util.Scanner;


public class EmployeeEmail {

    private final String department;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int mailboxCapacity = 500;
    private String altEmail;
    private String companySuffix = "gmail.com";
    private int phoneNumber;
    private final int defaultPasswordLength = 10;

    //Constructor to receive the firstname and lastname
    //Ask for the department - Method
    //Create an email - Method
    //Generate a password - Method

    //Set the mailbox capacity
    //Set the alt departmentChoice
    //SetorChange the password

    //Get Account details - Method
    //Get email
    //Get password


    //Constructor to receive the firstname and lastname
    //Constructor is a public method.
    //createPassword is a private method and accountDetails is a public method.

    public EmployeeEmail(String firstName, String lastName) {

        //New employee info received and firstname and last name initiated
        this.firstName = firstName;
        this.lastName = lastName;
        System.out.println("New Employee Information Received" + "\nHello Congratulations! We Welcome you to the Company Mr/Mrs " + this.firstName + " " + this.lastName);

        //Call a method asking for a department and return the department - Call a method and assign it to variable at the same time.
        this.department = setDepartment(); //When you are calling a method and want to to assign the returned value into this variable or setting the variable, you need to have a return type and not have void.

        //Create email process
        this.email = setEmail();
        System.out.println("Email of the New Employee Created\n" + email);

        //Generate a random password
        this.password = createPassword(defaultPasswordLength);
        System.out.println("Your Password is Created " + password);

        //Show all the account details
        //accountDetails(); I called this public method from outside of the class.

    }

    // Set method for setting the value to department variable.
    public String setDepartment() {

        System.out.println("Enter the department as \n1 for Sales \n2 for Development \n3 for Prod Support \n0 for none \nEnter your department ");
        Scanner sc = new Scanner(System.in); //Get user info from console, import Scanner utility
        int departmentChoice = sc.nextInt(); //Gets the int value from user and stored here

        //Conditionals to assign department value based on user info
        if (departmentChoice == 1) {
            return "sales";
        } //this.department = "sales"; we are assigning this.department so simply we can return the value and assign it at the call of the function.
        else if (departmentChoice == 2) {
            return "dev";
        } else if (departmentChoice == 3) {
            return "infra-tech";
        } else {
            return "";
        } // {} are needed in every part of if else conditions.
    }

    //Pass an argument of password length you want
    // This method is a private method, meaning it is not called form out-side of this class.
    private String createPassword(int length) {
        String AlphaNumericString = "ABCDEFGHIJHIKLabcdefghijkl123456789!@#$%^&*()";
        char[] password = new char[length]; //The password is set of individual characters, char[] is used instead of String, because we need to read individual characters of a string.
        for (int i = 0; i < length; i++) {
            int random = (int) (Math.random() * AlphaNumericString.length()); //Math.random() gives you a number between 0-1 in double.
            password[i] = AlphaNumericString.charAt(random);
        }
        return new String(password); //Since we need a String in the end , we keep return type as String and not the individual char array.
    }

    //Account details is public method, meaning it needs to be called by other classes outside this class.
    public String accountDetails() {
        System.out.println("Show employee information form a public method available to outside world. \nFIRST NAME: " + firstName + " \nLAST NAME:  " + lastName + " \nCOMPANY EMAIL: " + email + " \nMAILBOX CAPACITY:  Capacity " + mailboxCapacity);
        return "";
    }


    public String getEmail() {
        return email;
    }

    public String setEmail() {
        firstName = firstName.toLowerCase();
        lastName = lastName.toLowerCase();
        email = firstName + "." + lastName + "@" + department + "." + companySuffix;
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    public void setMailboxCapacity(int mailboxCapacity) {
        this.mailboxCapacity = mailboxCapacity;
    }

    public String getAltEmail() {
        return altEmail;
    }

    public void setAltEmail(String altEmail) {
        this.altEmail = altEmail;
    }

    public String getCompanySuffix() {
        return companySuffix;
    }

    public void setCompanySuffix(String companySuffix) {
        this.companySuffix = companySuffix;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
