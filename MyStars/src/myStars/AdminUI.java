package myStars;

import java.util.Calendar;
import java.util.Scanner;

import Entities.*;

public class AdminUI extends UserInterface {

	public AdminUI() {
		
	}

	public void printUI() {
		int choice=1;
		Scanner sc = new Scanner(System.in);

		while(choice!=0){
			printHeader("Welcome to the Admin UI",
					"1. Edit student access period",
					"2. Add a student",
					"3. Add a course",
					"4. Update a course",
					"5. Check available slot for an index number (vacancy in a class)",
					"6. Print student list by index",
					"7. Print student list by course (All student registered for the selected course)",
					"-1. Exit");

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

	// TODO studentCtrl
	public void editStudentAccessTimeUI() {
		String matric = null;

		Calendar accessStart;
		Calendar accessEnd;

		boolean validInput;

		printHeader("Edit student access time UI");

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
		//todo check the two dates accessEnd and accessStart if they're lesser or more
		accessStart = getDateInput("Enter access start for student: ");
		if (accessStart == null) return;

		accessEnd = getDateInput("Enter access end for student: ");
		if (accessEnd == null) return;

		AdminStudCtrl.editStudentAccessTime(matric, accessStart, accessEnd);

	}

	// TODO studentCtrl
	public void addStudentUI(){
		boolean validInput;
		String matric = null, nationality, email = null, username = null, pass;
		int yearOfStudy;
		Calendar accessStart, accessEnd;

		printHeader("Add a student UI");

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

		printHeader("Add a course UI");

		validInput = false;

		while(!validInput){
			courseCode = getStringInput("Enter Course Code: ");

			if(courseCode.equals("-1")) return;

			if(AdminCrsCtrl.isExistingCourse(courseCode)==true){
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
		String courseCode = null;
		boolean validInput;

		Scanner sc = new Scanner(System.in);

		printHeader("Update a course UI");

		validInput = false;
		while(!validInput){
			courseCode = getStringInput("Enter Course Code: ");

			if(courseCode.equals("-1")) return;

			if(AdminCrsCtrl.isExistingCourse(courseCode)==false){
				System.out.println("Course code does not exist!");
				System.out.println("Please try again!");
			} else validInput=true;
		}

		printHeader("1. Update the course's code",
				"2. Update the course's name",
				"3. Update the course's school",
				"4. Update the course's AU",
				"5. Update the course's type",
				"6. Update the course's index number",
				"7. Update the vacancy of a course index",
				"8. Create a new index",
				"-1. Exit update course UI");

		System.out.println("What part of the course do you want to update?");

		choice = sc.nextInt();

		switch(choice){
			case 1:
				// update course code
				validInput = false;
				while(!validInput){
					String newCourseCode = getStringInput("Enter the new course code for " + courseCode);

					if(newCourseCode.equals("-1")) return;

					if(AdminCrsCtrl.isExistingCourse(newCourseCode))
						System.out.println("Course already exist! Change to other course code");
					else {
						AdminCrsCtrl.updateCourseCode(courseCode, newCourseCode);
						validInput = true;
					}
				}
				break;
			case 2:
				// update course name
				//TODO testing
				String newCourseName = getStringInput("Enter the course name for " + courseCode);

				if(newCourseName.equals("-1")) return;

				AdminCrsCtrl.updateCourseName(courseCode, newCourseName);
				break;
			case 3:
				//TODO testing
				String newCourseSchool = getStringInput("Enter the school name for course " + courseCode);

				if(newCourseSchool.equals("-1")) return;

				AdminCrsCtrl.updateCourseSchool(courseCode, newCourseSchool);
				break;
			case 4:
				//TODO testing
				int newCourseAU = getIntInput("Enter the AU you'd like to change to for course " + courseCode,0, 5000);

				if(newCourseAU==-1) return;

				AdminCrsCtrl.updateCourseAU(courseCode, newCourseAU);

				break;
			case 5:
				//TODO testing
				String newCourseType = getStringInput("Enter the type you'd like to change to for course " + courseCode);

				if(newCourseType.equals("-1")) return;

				AdminCrsCtrl.updateCourseType(courseCode, newCourseType);

				break;
			case 6:
				System.out.println("Enter the index you'd like to update");
				// get the valid index first then update
				break;
			case 7:
				System.out.println("Enter the course index you'd like to update the vacancy");
				break;
			case 8:
				System.out.println("Enter an index you'd like to create");
				//get the valid index first then create
				break;

		}
	}
	public void checkVacancyUI(){
		int indexNo = 0;
		boolean validInput;
		String courseCode = null;

		System.out.println("===========================");
		System.out.println("Check vacancy UI");
		System.out.println("===========================");

		validInput = false;
		while(!validInput){
			courseCode = getStringInput("Enter Course Code: ");

			if(courseCode.equals("-1")) return;

			if(AdminCrsCtrl.isExistingCourse(courseCode)==false){
				System.out.println("Course code does not exist!");
				System.out.println("Please try again!");
			} else validInput=true;
		}

		validInput = false;
		while(!validInput){
			indexNo = getIntInput("Enter the index number you'd like to check for vacancy",10000,99999);

			if (indexNo==-1) return;

			if(AdminCrsCtrl.isExistingIndex(courseCode, indexNo)==false){
				System.out.println("Index does not exist!");
				System.out.println("Please try again!");
			} else validInput=true;
		}

		System.out.println("The vacancy left is: " +  AdminCrsCtrl.noOfIndexVacancy(courseCode, indexNo));

	}

	public void printStudentByIndexUI(){
		printHeader("Print student list by index UI");
	}

	public void printStudentByCourseUI(){
		printHeader("Print student list by course UI");
	}

}
