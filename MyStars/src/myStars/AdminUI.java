package myStars;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import Entities.*;

public class AdminUI {

	public AdminUI() {
		
	}

	public void printUI() {
		int choice=1;
		Scanner sc = new Scanner(System.in);

		while(choice!=0){
			System.out.println("Welcome to the Admin UI");
			System.out.println("===========================");
			System.out.println("1. Edit student access period");
			System.out.println("2. Add a student");
			System.out.println("3. Add a course");
			System.out.println("4. Update a course");
			System.out.println("5. Check available slot for an index number (vacancy in a class)");
			System.out.println("6. Print student list by index");
			System.out.println("7. Print student list by course (All student registered for the selected course)");
			System.out.println("0. Exit");
			System.out.println("===========================");
			System.out.println("Select a number from 1 to 7: ");
			choice = sc.nextInt();

			switch (choice){
				case 1: editStudentAccessTimeUI();
					break;
				case 2: addStudentUI();
					break;
				case 3: addCourseUI();
					break;
				case 4: updateCourseUI();
					break;
				case 5: checkVacancyUI();
					break;
				case 6: printStudentByIndexUI();
					break;
				case 7: printStudentByCourseUI();
					break;
				case 0: break;
				default: System.out.println("Invalid entry. Please select a number from 1 to 7: ");
					choice = sc.nextInt();
					break;
			}
		}
	}

	public void editStudentAccessTimeUI() {
		String matric;
		Calendar accessStart = null;
		Calendar accessEnd = null;

		Scanner sc = new Scanner(System.in);

		System.out.println("===========================");
		System.out.println("Edit student access time UI");
		System.out.println("===========================");

		System.out.println("Enter matriculation number of student: ");
		matric = sc.next();

		if(AdminStudCtrl.checkStudentExists(matric)==false){
			System.out.println("Student does not exist!");
			System.out.println("Returning to Admin UI...");
			return;
		}

		System.out.println("Enter access start dd/MM/yyyy HH:mm");
		int ok=0;
		while(ok==0){
			try {
				accessStart = CalendarCtrl.stringToCalendar(sc.next());
				ok=1;
			} catch (ParseException e) {
				System.out.println("You should input a good format dd/MM/yyyy HH:mm");
			}
		}


		System.out.println("Enter access start dd/MM/yyyy HH:mm");
		try {
			accessEnd = CalendarCtrl.stringToCalendar(sc.next());
		} catch (ParseException e) {
			System.out.println("You should input a good format dd/MM/yyyy HH:mm");
			return;
		}

		AdminStudCtrl.editStudentAccessTime(matric, accessStart, accessEnd);

	}

	public void addStudentUI(){
		String matric, nationality, email;
		int yearOfStudy;
		Calendar accessStart, accessEnd;

		Scanner sc = new Scanner(System.in);

		System.out.println("===========================");
		System.out.println("Add a student UI");
		System.out.println("===========================");

		System.out.println("Enter matriculation number of student: ");
		matric = sc.next();
		System.out.println("Enter nationality of student: ");
		nationality = sc.next();
		System.out.println("Enter year of study of student: ");
		yearOfStudy = sc.nextInt();
		System.out.println("Enter email of student: ");
		email = sc.next();
/*		System.out.println("Enter access start for student: ");
		accessStart = sc.next();
		System.out.println("Enter access end for student: ");
		accessEnd = sc.next();

		Student student = new Student(matric, nationality, yearOfStudy, email, accessStart, accessEnd);
		AdminStudCtrl.addStudent(student);*/

	}
	public void addCourseUI(){
		String courseCode, courseName, school, courseType;
		int courseAU;

		Scanner sc = new Scanner(System.in);

		System.out.println("===========================");
		System.out.println("Add a course UI");
		System.out.println("===========================");

		//todo check the exisiting course and return error

		System.out.println("Enter course code: ");
		courseCode = sc.next();
		System.out.println("Enter course name: ");
		courseName = sc.next();
		System.out.println("Enter school: ");
		school = sc.next();
		System.out.println("Enter course AU: ");
		courseAU = sc.nextInt();
		System.out.println("Enter course type: ");
		courseType = sc.next();

		Course course = new Course(courseCode, courseName, courseAU, school, courseType);
		AdminCrsCtrl.addCourse(course);

	}
	public void updateCourseUI(){
		int choice;
		String courseCode;

		Scanner sc = new Scanner(System.in);

		System.out.println("===========================");
		System.out.println("Update a course UI");
		System.out.println("===========================");

		// todo check exists, if does not exists, return error
		System.out.println("What is the course code you would like to update?");
		courseCode = sc.next();

		System.out.println("===========================");
		System.out.println("1. Update the course's code");
		System.out.println("2. Update the course's school");
		System.out.println("3. Update the course's index number");
		System.out.println("4. Update the course's vacancy");
		System.out.println("5. Exit update course UI");
		System.out.println("===========================");

		System.out.println("What part of the course do you want to update?");

		choice = sc.nextInt();

		switch(choice){
			case (1):
				System.out.println("Enter the course code you'd like to update");
				break;
			case (2):
				System.out.println("Enter the course code you'd like to update");
				break;
			case(3):
				System.out.println("Enter the course code you'd like to update");
				break;
			case(4):
				break;
		}
	}
	public void checkVacancyUI(){
		int indexNo;
		String courseCode;

		Scanner sc = new Scanner(System.in);

		System.out.println("===========================");
		System.out.println("Check vacancy UI");
		System.out.println("===========================");

		System.out.println("Enter the course code of the index you'd like to check for vacancy");

		courseCode = sc.next();

		System.out.println("Enter the index number you'd like to check for vacancy");
		indexNo = sc.nextInt();




	}
	public void printStudentByIndexUI(){
		System.out.println("===========================");
		System.out.println("Print student list by index UI");
		System.out.println("===========================");


	}
	public void printStudentByCourseUI(){
		System.out.println("===========================");
		System.out.println("Print student list by course UI");
		System.out.println("===========================");
	}
	
}
