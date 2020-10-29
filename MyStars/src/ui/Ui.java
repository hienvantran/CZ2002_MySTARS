package ui;

import java.util.Scanner;

public class Ui {
	private static Scanner sc = new Scanner(System.in);
	
	public String readinput() {
		return sc.next();
	}
	
	public void printStudentUi() {
		System.out.println("Hello Student how can I help you today?");
		System.out.println("Please select an option below");
		System.out.println("1: Add Course");
		System.out.println("2: Drop Course");
		System.out.println("3: Check/Print Course Registered");
		System.out.println("4: Check Vacancies available");
		System.out.println("5: Change Index Number of Course");
		System.out.println("6: Swap Index Number with another Student");
		System.out.println("7: Log out");
	}
	
	public void printAdminUi() {
		System.out.println("Hello Admin how can I help you today?");
		System.out.println("Please select an option below");
		System.out.println("1: Edit a student's access period");
		System.out.println("2: Add a student");
		System.out.println("3: Add/Update a course");
		System.out.println("4: Check available slot for an index number");
		System.out.println("5: Print student list by index number");
		System.out.println("6: Print student list by course");
		System.out.println("7: Log out");
	}
}
