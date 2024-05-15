package TimShort_RealWorldApplications.EmailApplicationProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmailApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		List<Employee> employees = new ArrayList<>();

		//Loop keeps running as long it finds a break statement.
		while (true) {
			System.out.print("Enter First Name: ");
			String fname = scanner.nextLine();

			System.out.print("Enter Last Name: ");
			String lname = scanner.nextLine();

			System.out.print("Enter Department (sales/dev/test/manager): ");
			String department = scanner.nextLine();

			System.out.print("Enter Alternate Email: ");
			String altEmail = scanner.nextLine();

			System.out.print("Enter Phone Number: ");
			String phoneNumber = scanner.nextLine();

			// Create new employee object
			Employee employee = new Employee(fname, lname, department, altEmail, phoneNumber);
			employees.add(employee);

			// Option to change email and phone number
			System.out.print("\nDo you want to change alternate email and phone number? (yes/no): ");
			String choice = scanner.nextLine();
			if (!choice.equalsIgnoreCase("yes")) {
				break;
			}
			employee.changeAltEmail(scanner);
			employee.changePhoneNumber(scanner);

			// Display employee details
			System.out.println("\nEmployee Details:");
			employee.accountDetails();

			// Option to add more employees
			System.out.print("\nDo you want to add another employee? (yes/no): ");
			String choice1 = scanner.nextLine();
			if (!choice1.equalsIgnoreCase("yes")) {
				break;
			}

		}

		// Display all employees list
		int employeeNumber = 1;
		for (Employee emp : employees) {
		    System.out.println("Employee " + employeeNumber + ":");
		    emp.accountDetails();
		    employeeNumber++;
		}

		scanner.close();
	}
}
/*
 * EmployeeEmail em1 = new EmployeeEmail("Bala_Gangadhar_Reddy", "Bhuma");
 * 
 * //Use setters to pass values into the dependency class to change them or
 * supply them if the dependency class does not have those values defined.
 * em1.setMailboxCapacity(500); em1.setPassword("BalaBhuma");
 * em1.setAltEmail("bgrbhuma@gmail.com"); em1.setPhoneNumber(219402456);
 * em1.accountDetails();
 */
