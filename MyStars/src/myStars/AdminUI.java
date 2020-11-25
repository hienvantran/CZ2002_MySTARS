package myStars;

import java.util.Calendar;
import java.util.Scanner;

import Entities.*;

public class AdminUI extends UserInterface {

	/**
	 * A new object of AdminCtrl
	 */
	AdminCrsCtrl adminCrsCtrl = new AdminCrsCtrl();


	/**
	 * A new object of PrintInfoCtrl
	 */
	PrintInfoCtrl printInfoCtrl = new PrintInfoCtrl();

	/**
	 * Default constructor
	 */
	public AdminUI() {
		
	}

	/**
	 * Provide the UI of the AdminUI
	 */
	public void printUI() {
		int choice=1;
		Scanner sc = new Scanner(System.in);

		while(choice!=-1){
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
				case -1: 
					System.out.println("Exiting... Thank you for using MYSTARS! ");
					break;
				default: 
					System.out.println("Invalid entry. Please select a number from 1 to 7: ");
					choice = sc.nextInt();
					break;
			}
		}
	}

	/**
	 * Provide the UI for editing a student's access time
	 */
	public void editStudentAccessTimeUI() {
		String matric = null;

		Calendar accessStart = null;
		Calendar accessEnd = null;

		boolean validInput;

		printHeader("Edit student access time UI");

		System.out.println("Enter -1 to return to Admin UI");

		validInput = false;

		while(!validInput){
			matric = getStringInput("Enter matriculation number of student: ");

			if(matric.equals("-1")) return;

			if(AdminStudCtrl.isStudentMatricExists(matric)==false){
				System.out.println("Student does not exist!");
				System.out.println("Please try again!");
			} else validInput=true;
		}
		
		validInput = false;

		while(!validInput){
			accessStart = getDateInput("Enter access start for student: ");
			if (accessStart == null) return;

			accessEnd = getDateInput("Enter access end for student: ");
			if (accessEnd == null) return;

			if(accessEnd.after(accessStart)) validInput = true;
			else System.out.println("Access start date cannot be later than access end date!");
		}

		AdminStudCtrl.editStudentAccessTime(matric, accessStart, accessEnd);

		System.out.println("You've successfully edited student's access time!");
	}

	/**
	 * Provide the UI for adding student profile
	 */
	public void addStudentUI(){
		boolean validInput;
		String matric = null, nationality, email = null, username = null, pass;
		int yearOfStudy;
		Calendar accessStart = null, accessEnd = null;

		printHeader("Add a student UI");

		validInput=false;
		while(!validInput){
			matric = getStringInput("Enter matriculation number of student: ");

			if(matric.equals("-1")) return;

			if(AdminStudCtrl.isStudentMatricExists(matric)==true)
				System.out.println("Student already exists!");
			else
				validInput=true;
		}

		validInput=false;
		while(!validInput){
			username = getStringInput("Enter username of student: ");

			if(username.equals("-1")) return;

			if(AdminStudCtrl.isStudentUserExists(username)==true)
				System.out.println("Username already exists!");
			else
				validInput=true;
		}

		validInput=false;
		while(!validInput){
			email = getStringInput("Enter the email of student: ");

			if(email.equals("-1")) return;

			if(AdminStudCtrl.isStudentEmailExists(email)==true)
				System.out.println("Email already exists!");
			else
				validInput=true;
		}

		pass = getStringInput("Enter password of student: ");
		if (pass.equals("-1")) return;

		nationality = getStringInput("Enter nationality number of student: ");
		if (nationality.equals("-1")) return;

		yearOfStudy = getIntInput("Enter year of study of student: ", 0,10);
		if(yearOfStudy==-1) return;

		validInput = false;
		while(!validInput){
			accessStart = getDateInput("Enter access start for student: ");
			if (accessStart == null) return;

			accessEnd = getDateInput("Enter access end for student: ");
			if (accessEnd == null) return;

			if(accessEnd.after(accessStart)) validInput = true;
			else System.out.println("Access start date cannot be later than access end date!");
		}

		pass = HashCtrl.hashPassword(pass);

		Student student = new Student(username, name, pass, ModeType.USER, matric, nationality, yearOfStudy, email, accessStart, accessEnd);
		AdminStudCtrl.addStudent(student);

		System.out.println("You've successfully added a student! The new list of students: ");
		printInfoCtrl.printStudents();

		System.out.println("Bringing you back to the main admin UI...");

	}

	/**
	 * Provide the UI for adding course
	 */
	public void addCourseUI(){
		String courseCode = null, courseName, school, courseType;
		int courseAU;
		boolean validInput;

		printHeader("Add a course UI");

		validInput = false;

		while(!validInput){
			courseCode = getStringInput("Enter Course Code: ");

			if(courseCode.equals("-1")) return;

			if(adminCrsCtrl.isExistingCourse(courseCode)==true){
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
		adminCrsCtrl.addCourse(course);

		System.out.println("You've successfully added a course! The new list of courses: ");
		printInfoCtrl.printCourse();

		System.out.println("Bringing you back to the main admin UI...");
	}

	/**
	 * Provide the UI for updating a course
	 */
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

			if(adminCrsCtrl.isExistingCourse(courseCode)==false){
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
				"7. Update the total slot of a course index",
				"8. Remove the course",
				"9. Create a new index",
				"10. Remove a course's index",
				"-1. Exit update course UI");

		System.out.println("What part of the course do you want to update?");

		choice = sc.nextInt();

		switch(choice){
			case 1:
				// update course code
				validInput = false;
				while(!validInput){
					String newCourseCode = getStringInput("Enter the new course code for: " + courseCode);

					if(newCourseCode.equals("-1")) return;

					if(adminCrsCtrl.isExistingCourse(newCourseCode))
						System.out.println("Course already exist! Change to other course code");
					else {
						adminCrsCtrl.updateCourseCode(courseCode, newCourseCode);
						System.out.println("You've successfully updated the course code! Bringing you back to the main admin UI...");
						validInput = true;
					}
				}
				break;
			case 2:
				// update course name
				String newCourseName = getStringInput("Enter the course name for: " + courseCode);

				if(newCourseName.equals("-1")) return;

				adminCrsCtrl.updateCourseName(courseCode, newCourseName);
				System.out.println("You've successfully updated the course name! Bringing you back to the main admin UI...");
				break;
			case 3:
				String newCourseSchool = getStringInput("Enter the school name for course: " + courseCode);

				if(newCourseSchool.equals("-1")) return;

				adminCrsCtrl.updateCourseSchool(courseCode, newCourseSchool);
				System.out.println("You've successfully updated the course school! Bringing you back to the main admin UI...");
				break;
			case 4:
				int newCourseAU = getIntInput("Enter the AU you'd like to change to for course: " + courseCode,0, 5000);

				if(newCourseAU==-1) return;

				adminCrsCtrl.updateCourseAU(courseCode, newCourseAU);

				System.out.println("You've successfully updated the course AU! Bringing you back to the main admin UI...");
				break;
			case 5:
				String newCourseType = getStringInput("Enter the type you'd like to change to for course: " + courseCode);

				if(newCourseType.equals("-1")) return;

				adminCrsCtrl.updateCourseType(courseCode, newCourseType);

				System.out.println("You've successfully updated the course type! Bringing you back to the main admin UI...");
				break;
			case 6:
				// update course code
				validInput = false;
				while(!validInput){
					System.out.println("Here is a list of index to update for the course code: " + courseCode);
					printInfoCtrl.printIndexByCourse(courseCode);
					int index = getIntInput("Enter the index you'd like to update: ", 0,99999);

					if(index==-1) return;

					if(adminCrsCtrl.isExistingIndex(courseCode,index)){
						do{
							int newIndex = getIntInput("Enter the new index", 0,99999);
							if (adminCrsCtrl.isExistingIndex(courseCode,newIndex)==false){
								adminCrsCtrl.updateCourseIndex(courseCode, index, newIndex);
								System.out.println("You've successfully updated the course index! Bringing you back to the main admin UI...");
								break;
							}
							else{
								System.out.println("The course index you've entered already exist!");
							}
						}
						while(true);

						validInput = true;
					}
					else {
						validInput = false;
					}
				}
				break;
			case 7:
				validInput = false;
				while(!validInput){
					System.out.println("Here is a list of index to update for the course code: " + courseCode);
					printInfoCtrl.printIndexByCourse(courseCode);
					int index = getIntInput("Enter the index you'd like to update the course's total slot: ", 0, 99999);

					if (index==-1) return;

					if(adminCrsCtrl.isExistingIndex(courseCode, index)){
						int totalSlot = getIntInput("Enter your desired total slot: ", 0, 5000);
						if (totalSlot==-1) return;

						adminCrsCtrl.updateIndexTotalSlot(courseCode, index, totalSlot);
						System.out.println("You've successfully updated the course total index slot! Bringing you back to the main admin UI...");
						validInput = true;
					}else{
						System.out.println("The course index does not exist!");
					}
				}
				break;
			case 8:
				printHeader("Removing a course UI");
				adminCrsCtrl.removeCourse(courseCode);
				System.out.println("You've successfully removed the course code: " + courseCode + "! Bringing you back to the main admin UI...");
				break;
			case 9:
				validInput = false;
				while(!validInput){
					System.out.println("Here is a list of index already exist for the course code: " + courseCode);
					printInfoCtrl.printIndexByCourse(courseCode);
					int index = getIntInput("Enter an index you'd like to create: ", 0, 99999);
					if (index==-1) return;

					String group = getStringInput("Enter the group of the index: ");
					if (group.equals("-1")) return;

					int totalSlot = getIntInput("Enter the total slot of the index: ", 0, 10000);

					if(adminCrsCtrl.isExistingIndex(courseCode, index)){
						// reject the person
						System.out.println("The index already exists!");
					}
					if (adminCrsCtrl.isExistingGroup(courseCode, index, group)){
						System.out.println("The group already exists!");
					}else{
						// create the index
						adminCrsCtrl.createIndex(courseCode, index, group, totalSlot);
						System.out.println("You've successfully created an index!");
					}
				}
				break;
			case 10:
				validInput = false;
				while(!validInput){
					System.out.println("Here is a list of index to remove for the course code: " + courseCode);
					printInfoCtrl.printIndexByCourse(courseCode);
					int index = getIntInput("Enter an index you'd like to remove: ", 0, 99999);
					if (index==-1) return;

					if(adminCrsCtrl.isExistingIndex(courseCode, index)){
						adminCrsCtrl.removeIndex(courseCode, index);
						System.out.println("You've successfully removed the course index: " + index + "! Bringing you back to the main admin UI...");
						validInput = true;
					}else{
						System.out.println("Index doesn't exists!");
					}
				}
				break;
		}
	}

	/**
	 * Provide the UI for checking vacancy of a course
	 */
	public void checkVacancyUI(){
		int indexNo = 0;
		boolean validInput;
		String courseCode = null;

		printHeader("Check vacancy UI");

		validInput = false;
		while(!validInput){
			courseCode = getStringInput("Enter Course Code: ");

			if(courseCode.equals("-1")) return;

			if(adminCrsCtrl.isExistingCourse(courseCode)==false){
				System.out.println("Course code does not exist!");
				System.out.println("Please try again!");
			} else validInput=true;
		}

		validInput = false;
		while(!validInput){
			indexNo = getIntInput("Enter the index number you'd like to check for vacancy",10000,99999);

			if (indexNo==-1) return;

			if(adminCrsCtrl.isExistingIndex(courseCode, indexNo)==false){
				System.out.println("Index does not exist!");
				System.out.println("Please try again!");
			} else validInput=true;
		}

		System.out.println("The vacancy is: " +
		adminCrsCtrl.noOfIndexVacancy(courseCode, indexNo) + "/" + adminCrsCtrl.noOfIndexTotalSlot(courseCode, indexNo));
		
		System.out.println("Done printing, bringing you back to the admin UI...");
	}

	/**
	 * Provide the UI for printing student by index
	 */
	public void printStudentByIndexUI(){
		boolean validInput;
		String courseCode = null;
		int indexNo = 0;

		printHeader("Print student list by index UI");
		validInput = false;
		while(!validInput){
			courseCode = getStringInput("Enter Course Code: ");

			if(courseCode.equals("-1")) return;

			if(adminCrsCtrl.isExistingCourse(courseCode)==false){
				System.out.println("Course code does not exist!");
				System.out.println("Please try again!");
			} else validInput=true;
		}

		validInput = false;
		while(!validInput){
			indexNo = getIntInput("Enter the index number you'd like to print",10000,99999);

			if (indexNo==-1) return;

			if(adminCrsCtrl.isExistingIndex(courseCode, indexNo)==false){
				System.out.println("Index does not exist!");
				System.out.println("Please try again!");
			} else validInput=true;
		}

		printInfoCtrl.printStudByIndex(indexNo, courseCode);
		System.out.println("\n\n");
		System.out.println("Done printing, bringing you back to the admin UI...");
	}

	/**
	 * Provide the UI for printing student by course
	 */
	public void printStudentByCourseUI(){
		boolean validInput;
		String courseCode = null;
		int indexNo = 0;

		printHeader("Print student list by course UI");
		validInput = false;
		while(!validInput){
			courseCode = getStringInput("Enter Course Code: ");

			if(courseCode.equals("-1")) return;

			if(adminCrsCtrl.isExistingCourse(courseCode)==false){
				System.out.println("Course code does not exist!");
				System.out.println("Please try again!");
			} else validInput=true;
		}

		printInfoCtrl.printStudByCourse(courseCode);
		System.out.println("\n\n");
		System.out.println("Done printing, bringing you back to the admin UI...");
	}

}
