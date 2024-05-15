package EmployeeEmailApplicationProject;

import java.util.Arrays;
import java.util.Random;

import java.util.*;

class Employee {
    private String fname;
    private String lname;
    private String email;
    private String password;
    private String department;
    private String altEmail;
    private String phoneNumber;
    private int mailboxCapacity = 500;

    // Constructor
    public Employee(String fname, String lname, String department, String altEmail, String phoneNumber) {
        this.fname = fname;
        this.lname = lname;
        this.department = (department != null && !department.isEmpty()) ? department : "sales";
        this.altEmail = altEmail;
        this.phoneNumber = phoneNumber;
        this.email = emailCreation();
        this.password = randomPassword(8);
    }

    // Email creation method
    private String emailCreation() {
        String[] departments = {"sales", "dev", "test", "manager"};
        String dept = (Arrays.asList(departments).contains(this.department)) ? this.department : "sales";
        return fname.toLowerCase() + "." + lname.toLowerCase() + "@" + dept + ".gmail.com";
    }

    // Generate random password
    private String randomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        StringBuilder password = new StringBuilder();
        Random rnd = new Random();
        while (password.length() < length) {
            int index = (int) (rnd.nextFloat() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }

    // Change password method
    public void changePassword(Scanner scanner) {
        System.out.println("Enter new password: ");
        String newPassword = scanner.nextLine();

        System.out.println("Confirm new password: ");
        String confirmPassword = scanner.nextLine();

        if (newPassword.equals(confirmPassword)) {
            this.password = newPassword;
            System.out.println("Password changed successfully!");
        } else {
            System.out.println("Passwords do not match. Try again.");
            changePassword(scanner); // Recursive call to prompt for new passwords again
        }
    }

    // Change alternate email method
    public void changeAltEmail(Scanner scanner) {
        System.out.println("Enter new alternate email: ");
        String newAltEmail = scanner.nextLine();
        this.altEmail = newAltEmail;
        System.out.println("Alternate email changed successfully!");
    }

    // Change phone number method
    public void changePhoneNumber(Scanner scanner) {
        System.out.println("Enter new phone number: ");
        String newPhoneNumber = scanner.nextLine();
        this.phoneNumber = newPhoneNumber;
        System.out.println("Phone number changed successfully!");
    }

    // Account details method
    public void accountDetails() {
        System.out.println("First Name: " + fname);
        System.out.println("Last Name: " + lname);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Department: " + department);
        System.out.println("Alternate Email: " + altEmail);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Mailbox Capacity: " + mailboxCapacity + " mb");
    }
}
