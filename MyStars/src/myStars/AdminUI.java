package myStars;

import java.text.ParseException;
import java.util.Calendar;
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
		String matric = null;

		Calendar accessStart;
		Calendar accessEnd;

		boolean validInput;

		System.out.println("===========================");
		System.out.println("Edit student access time UI");
		System.out.println("===========================");
		System.out.println("Enter -1 to return to Admin UI");

		validInput = false;

		while(!validInput){
			matric = getStringInput("Enter matriculation number of student: ");

			if(matric.equals("-1")) return;

			if(AdminStudCtrl.checkStudentExists(matric)==false){
				System.out.println("Student does not exist!");
				System.out.println("Please try again!");
			} else validInput=true;
		}
		//to do check the two dates accessEnd and accessStart if they're lesser or more
		accessStart = getDateInput("Enter access start for student: ");
		if (accessStart == null) return;

		accessEnd = getDateInput("Enter access end for student: ");
		if (accessEnd == null) return;

		AdminStudCtrl.editStudentAccessTime(matric, accessStart, accessEnd);

	}

	public void addStudentUI(){
		boolean validInput;
		String matric = null, nationality, email = null, username = null, pass;
		int yearOfStudy;
		Calendar accessStart, accessEnd;

		System.out.println("===========================");
		System.out.println("Add a student UI");
		System.out.println("===========================");

		validInput=false;
		while(!validInput){
			matric = getStringInput("Enter matriculation number of student: ");

			if(matric.equals("-1")) return;

			if(AdminStudCtrl.checkStudentExists(matric)==true)
				System.out.println("Student already exists!");
			else
				validInput=true;
		}

		validInput=false;
		while(!validInput){
			username = getStringInput("Enter username of student: ");

			if(username.equals("-1")) return;

			if(AdminStudCtrl.checkUsernameExists(username)==true)
				System.out.println("Username already exists!");
			else
				validInput=true;
		}

		pass = getStringInput("Enter password of student: ");
		if (pass.equals("-1")) return;

		validInput=false;
		while(!validInput){
			email = getStringInput("Enter username of student: ");

			if(email.equals("-1")) return;

			if(AdminStudCtrl.checkEmailExists(email)==true)
				System.out.println("Username already exists!");
			else
				validInput=true;
		}

		nationality = getStringInput("Enter nationality number of student: ");
		if (nationality.equals("-1")) return;

		yearOfStudy = getIntInput("Enter year of study of student: ", 0,10);
		if(yearOfStudy==-1) return;

		accessStart = getDateInput("Enter access start for student: ");
		if (accessStart == null) return;

		accessEnd = getDateInput("Enter access end for student: ");
		if (accessEnd == null) return;

		// create Student object and add to student list
		Student student = new Student(username, pass, ModeType.USER, matric, nationality, yearOfStudy, email, accessStart, accessEnd);
		AdminStudCtrl.addStudent(student);

	}

	public void addCourseUI(){
		String courseCode = null, courseName, school, courseType;
		int courseAU;
		boolean validInput;

		System.out.println("===========================");
		System.out.println("Add a course UI");
		System.out.println("===========================");

		validInput = false;

		while(!validInput){
			courseCode = getStringInput("Enter Course Code: ");

			if(courseCode.equals("-1")) return;

			if(AdminCrsCtrl.checkExistCourse(courseCode)==true){
				System.out.println("Course code already exists!");
				System.out.println("Please try again!");
			} else validInput=true;
		}

		courseName = getStringInput("Enter course name: ");
		if (courseName.equals("-1")) return;

		school = getStringInput("Enter school name: ");
		if (school.equals("-1")) return;

		courseAU = getIntInput("Enter course AU: ", 0, 20);
		if (courseAU==-1) return;

		courseType = getStringInput("Enter Course Type: ");
		if (courseType.equals("-1")) return;

		Course course = new Course(courseCode, courseName, courseAU, school, courseType);
		AdminCrsCtrl.addCourse(course);

	}

	public void updateCourseUI(){
		int choice;
		String courseCode;
		boolean validInput;

		Scanner sc = new Scanner(System.in);

		System.out.println("===========================");
		System.out.println("Update a course UI");
		System.out.println("===========================");

		validInput = false;

		while(!validInput){
			courseCode = getStringInput("Enter Course Code: ");

			if(courseCode.equals("-1")) return;

			if(AdminCrsCtrl.checkExistCourse(courseCode)==false){
				System.out.println("Course code does not exist!");
				System.out.println("Please try again!");
			} else validInput=true;
		}

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
			case (3):
				System.out.println("Enter the course code you'd like to update");
				break;
			case (4):
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

	private Calendar getDateInput(String prompt){
		boolean validInput = false;
		String temp;
		Scanner sc = new Scanner(System.in);
		Calendar result = null;

		while(!validInput){
			System.out.println(prompt);
			temp = sc.nextLine();

			if(temp.equals("-1")) return null;

			try {
				result = CalendarCtrl.stringToCalendar(temp);
				validInput=true;
			} catch (ParseException e) {
				System.out.println("You should input a good format dd/MM/yyyy HH:mm");
			}
		}
		return result;
	}

	private String getStringInput(String prompt){
		String temp;
		Scanner sc = new Scanner(System.in);

		System.out.println(prompt);

		temp = sc.nextLine();
		if(temp.equals("-1")) return "-1";
		else return temp;
	}

	private int getIntInput(String prompt, int rangeStart, int rangeEnd){
		int temp = 0;
		boolean valid = false;
		Scanner sc = new Scanner(System.in);

		System.out.println(prompt);

		while(!valid){
			temp = sc.nextInt();
			if (temp > rangeEnd || temp < rangeStart)
				System.out.println("Invalid range. Please enter from " + rangeStart + " to " + rangeEnd);
			else valid = true;
		}

		return temp;
	}

}
