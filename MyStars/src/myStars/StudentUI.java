package myStars;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import DB.CourseRegDB;
import DB.IndexDB;
import DB.StudentDB;
import Entities.CourseRegister;
import Entities.Index;
import Entities.Student;


public class StudentUI {
	
	private Student student;
	private Scanner sc = new Scanner(System.in);
	
	public StudentUI() {
		
	}
	
	public void printUI(Student student)
	{
		
		while(true)
		{
			System.out.println("Hello how can I help you today?");
			System.out.println("Please select an option below");
			System.out.println("1: Register Course");
			System.out.println("2: Drop Course");
			System.out.println("3: Check/Print Course Registered");
			System.out.println("4: Check Vacancies available");
			System.out.println("5: Change Index Number of Course");
			System.out.println("6: Swap Index Number with another Student");
			System.out.println("7: Log out");
			
			int choice =0;
			
			try
			{
				System.out.println("Please select an option below: ");
				choice = Integer.parseInt(sc.nextLine());
				switch(choice)
				{
					case 1:
						registerCourseUI();
						break;
					case 2:
						dropCourseUI();
						break;
					case 3:
						CourseRegisterUI();
						break;
					case 4:
						checkVacancyUI();
						break;
					case 5:
						changeIndexUI();
						break;
					case 6:
						SwapIndexUI();
						break;
					case 7:
						NotificationUI();
					case 8:
						LogOutUI();
					default:
						System.out.println("Invalid Input! \n");
				}
			}
			catch (Exception e)
			{
				System.out.println("Invalid Input! Please enter a valid input: \n");
			}
		}
	}
	
	public void registerCourseUI() throws ParseException, IOException
	{
		//ArrayList<Student> studList = StudentDB.retrieveStudent();
		ArrayList<Index> indexList = IndexDB.retrieveIndex();
		ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
		
		int inputIndex = 0;
		String courseCode;
		String studentID = student.getMatricNum();
		//To see if student input a number
		while(true)
		{
			try	
			{
				System.out.println("Enter the index number you want to register: \n");
				inputIndex = sc.nextInt();
				break;
			}
			catch(Exception e)
			{
				System.out.println("Invalid Input! Input must be a number: \n");
			}
		}
		// Check the database to see if this Index exist
		boolean checkIndex = false;
		
		for (Index idx : indexList)
		{
			if(idx.getIndex() == inputIndex) 
			{
				checkIndex = true;
				courseCode = idx.getCourseCode();
				break;
			}
		}
		if (checkIndex == false)
		{
			System.out.println("The Index you have entered does not exist\n");
			return;
		}
		//Print Index Info Index -> CourseCode -> Lesson Type -> Group -> Day -> Time -> Venue
		System.out.println("Confirm to register this index? Please enter (Y/N): \n");
		char choice = sc.nextLine().charAt(0);
		Character.toUpperCase(choice);
		
		if (choice == 'Y')
		{
			StudentCtrl.registerCourse(studentID,courseCode,inputIndex);
		}
	}
	
	 public void dropCourseUI() throws ParseException, IOException
	 {
		//Display the list of registeredCourses
		String studentID = student.getMatricNum();
		PrintInfoCtrl.printRegCourse(studentID);
		
		ArrayList<Index> indexList = IndexDB.retrieveIndex();
		ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
			
		int inputIndex = 0;
		String courseCode;
			
		//To see if student input a number
		while(true)
		{
			try	
			{
				System.out.println("Enter the index number you want to Drop: \n");
				inputIndex = sc.nextInt();
				break;
			}
			catch(Exception e)
			{
				System.out.println("Invalid Input! Input must be a number\n");
			}
		}
		//Print Index Info Index -> CourseCode -> Lesson Type -> Group -> Day -> Time -> Venue
		System.out.println("Confirm to register this index? Please enter (Y/N): \n");
		char choice = sc.nextLine().charAt(0);
		Character.toUpperCase(choice);
		
		if (choice == 'Y')
		{
			StudentCtrl.dropCourse(studentID,courseCode,inputIndex);
		}
	 }
	 
	 public void checkVacancyUI() throws IOException, ParseException
	 {
		 ArrayList<Index> indexList = IndexDB.retrieveIndex();
		 int inputIndex = 0;
		 String courseCode;
			
		 while(true)
			{
				try	
				{
					System.out.println("Enter the index number you want to view \n");
					inputIndex = sc.nextInt();
					break;
				}
				catch(Exception e)
				{
					System.out.println("Invalid Input! Please enter again\n");
				}
			}
		// Check the database to see if this index exist
			boolean checkIndex = false;
			
			for (Index idx : indexList)
			{
				if(idx.getIndex() == inputIndex) 
				{
					checkIndex = true;
					courseCode = idx.getCourseCode();
					break;
				}
			}
			if (checkIndex == false)
			{
				System.out.println("The Index you have entered does not exist\n");
				return;
			}
			//Print Index Info Index -> CourseCode -> Lesson Type -> Group -> Day -> Time -> Venue
			StudentCtrl.checkVacancy(courseCode);
	 }
	 
	 public void changeIndexUI() throws IOException, ParseException
	 {
		 System.out.print("Enter Current Index Number: \n");
		 int currentIndexNo = sc.nextInt();
		 
		 System.out.print("Enter the New Index Number you want to change: \n");
		 int newIndexNo = sc.nextInt();
		 
		 //Display Old and New Index Info
		 //Print Index Info Index -> CourseCode -> Lesson Type -> Group -> Day -> Time -> Venue
	 }
	 
}
	 
	 
	 





	
