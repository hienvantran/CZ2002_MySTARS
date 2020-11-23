
package myStars;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import DB.CourseDB;
import DB.CourseRegDB;
import DB.IndexDB;
import DB.StudentDB;
import Entities.Course;
import Entities.CourseRegister;
import Entities.Index;
import Entities.Student;

/**
 * This class is created solely for the Student's user-interface
 * @author zappe
 *
 */
public class StudentUI extends UserInterface{
	
	Scanner sc = new Scanner(System.in);
	StudentCtrl mystudent = new StudentCtrl();
	public StudentUI() {
	}
	/**
	 * Display different kinds of options for students to choose from 
	 * Student can register/drop courses, view their already registered courses
	 * Check class vacancy, change index number, swap index with peers
	 * @param studentID
	 */
	public void printUI(String studentID)
	{
		while(true)
		{
			printHeader("Hello " + studentID + " how can I help you today?\n",
					"Please select an option below",
					"1: Register Course",
					"2: Drop Course",
					"3: Check/Print Course Registered",
					"4: Check Vacancies available",
					"5: Change Index Number of Course",
					"6: Swap Index Number with another Student",
					"-1. Exit");
			int choice = 1;
			
			try
			{
				System.out.println("Select a number from 1 to 6: ");
				choice = Integer.parseInt(sc.nextLine());
				switch(choice)
				{
					case 1:
						registerCourseUI(studentID);
						break;
					case 2:
						dropCourseUI(studentID);
						break;
					case 3:
						courseRegisterUI(studentID);
						break;
					case 4:
						checkVacancyUI(studentID);
						break;
					case 5:
						changeIndexUI(studentID);
						break;
					case 6:
						swapIndexUI(studentID);
						break;
					default:
						System.out.println("Invalid Input! Please select a number from 1 to 6: ");
				}
			}
			catch (Exception e)
			{
				System.out.println("Invalid Input! Please enter a valid input ");
				System.out.println();
			}
		}
	}
	/**
	 * Display RegisterCourse interface, prompting student for input
	 * @param studentID
	 * @throws ParseException
	 * @throws IOException
	 */
	public void registerCourseUI(String studentID) throws ParseException, IOException
	{
		//retrieve indexList/CourseList
		ArrayList<Index> indexList = IndexDB.retrieveIndex();
		ArrayList<Course> courseList = CourseDB.retrieveCourse();
		
		int inputIndex = 0;
		String inputCrsCode = null;
		boolean again = true;
		boolean validInput = false;
		
		//Ask student to input CourseCode
		while (!validInput)
		{
			inputCrsCode = getStringInput("Enter the course code: ");
			inputCrsCode = inputCrsCode.toUpperCase();
			for (Course course : courseList)
			{
				if(course.getCourseCode().equals(inputCrsCode))
				{
					validInput = true;
					break;
				}
				if(inputCrsCode.equals("-1")) return;
			}
			if (validInput == false)
			{
				System.out.println("No such coursecode... Please try again!");
			}
		}
		//To see if student input a number
		while(again)
		{
			try	
			{
				//System.out.println("Enter the index number you want to register: \n");
				inputIndex = getIntInput("Enter the index number you want to register: ", 0,99999);
				again = false;
				if (inputIndex == -1) return;
			}
			catch(Exception e)
			{
				System.out.println("Invalid Input! Please enter only valid number ");
			}
		}
		// Check the database to see if this Index exist
		boolean checkIndex = false;
		
		for (Index idx : indexList)
		{
		
			if(idx.getIndex()== inputIndex && idx.getCourseCode().equals(inputCrsCode))
			{
				checkIndex = true;
				inputCrsCode = idx.getCourseCode();
				break;
			}
		}
		if (checkIndex == false)
		{
			System.out.println("The Index you have entered does not exist");
			return;
		}
		
		//Print Index Info Index -> CourseCode -> Lesson Type -> Group -> Day -> Time -> Venue
		System.out.println("Confirm to register this index? Please enter (Y|N) ");
		String sel= sc.nextLine();
		sel = sel.toUpperCase();
		
		if(sel.equals("Y"))
		{
			mystudent.registerCourse(studentID,inputCrsCode,inputIndex);
		}
	 }
	/**
	 * Display DropCourse interface, prompting student for input
	 * @param studentID
	 * @throws ParseException
	 * @throws IOException
	 */
	 public void dropCourseUI(String studentID) throws ParseException, IOException
	 {
		ArrayList<Course> courseList = CourseDB.retrieveCourse();
		//Display the list of registeredCourses
		mystudent.printRegCourse(studentID);
		
		int inputIndex = 0;
		String inputCrsCode = null;
		boolean again = true;
		boolean validInput = false;
		
		//Ask user to input the CourseCode
		while (!validInput)
		{
			inputCrsCode = getStringInput("Enter the course code: ");
			inputCrsCode = inputCrsCode.toUpperCase();
			for (Course course : courseList)
			{
				if(course.getCourseCode().equals(inputCrsCode))
				{
					validInput = true;
					break;
				}
				if(inputCrsCode.equals("-1")) return;
			}
			if (validInput == false)
			{
				System.out.println("No such coursecode... Please try again!");
			}
		}
		
		//To see if student input a number
		while(again)
		{
			try	
			{
				inputIndex = getIntInput("Enter the index number you want to Drop: ", 0,99999);
				again = false;
				if (inputIndex == -1) return;
			}
			catch(Exception e)
			{
				System.out.println("Invalid Input! Please enter only valid number ");
			}
		}
		
		
		//Print Index Info Index -> CourseCode -> Lesson Type -> Group -> Day -> Time -> Venue
		System.out.println("Confirm to register this index? Please enter (Y|N) ");
		String sel= sc.nextLine();
		sel = sel.toUpperCase();
		
		if(sel.equals("Y"))
		{
			mystudent.dropCourse(studentID,inputCrsCode,inputIndex);
		}
		System.out.println();
	 }
	 /**
	  * Display CheckVacancy interface, prompting student for input
	  * @param studentID
	  * @throws IOException
	  * @throws ParseException
	  */
	 public void checkVacancyUI(String studentID) throws IOException, ParseException
	 {
		 ArrayList<Index> indexList = IndexDB.retrieveIndex();
		 int inputIndex = 0;
		 String courseCode = null;
		 boolean again = true;
		 
		 while(again)
		 {
			try	
			{
				inputIndex = getIntInput("Enter the index number you want to view: ", 0,99999);
				again = false;
				if (inputIndex == -1) return;
			}
			catch(Exception e)
			{
				System.out.println("Invalid Input! Please enter only valid number ");
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
		 if  (checkIndex == false)
		 {
			 System.out.println("The Index you have entered does not exist");
			 return;
		 }
		 //Print Index Info Index -> CourseCode -> Lesson Type -> Group -> Day -> Time -> Venue
		 mystudent.checkVacancy(courseCode);
	 }
	 /**
	  * Display ChangeIndex interface, prompting student for input
	  * @param studentID
	  * @throws IOException
	  * @throws ParseException
	  */
	 public void changeIndexUI(String studentID) throws IOException, ParseException
	 {
		 ArrayList<Course> courseList = CourseDB.retrieveCourse();
		 StudentCtrl mystudent = new StudentCtrl();
		 int currIndex = 0;
		 int newIndex = 0;
		 String inputCrsCode = null;
		 boolean again = true;
		 boolean validInput = false;
			
		//Ask user to input the CourseCode
		while (!validInput)
		{
			inputCrsCode = getStringInput("Enter the course code: ");
			inputCrsCode = inputCrsCode.toUpperCase();
			for (Course course : courseList)
			{
				if(course.getCourseCode().equals(inputCrsCode))
				{
					validInput = true;
					break;
				}
				if(inputCrsCode.equals("-1")) return;
			}
			if (validInput == false)
			{
				System.out.println("No such coursecode... Please try again!");
			}
		}
		 
		 while(again)
		 {
			try	
			{
				currIndex = getIntInput("Enter current index number: ", 0,99999);
				again = false;
				if (currIndex == -1) return;
			}
			catch(Exception e)
			{
				System.out.println("Invalid Input! Please enter only valid number ");
			}
		 }
		 again = true;
		 // Display New Index Info
		 while(again)
		 {
			try	
			{
				newIndex = getIntInput("Enter new index number: ", 0,99999);
				again = false;
				if (newIndex == -1) return;
			}
			catch(Exception e)
			{
				System.out.println("Invalid Input! Please enter only valid number ");
			}
		 }
		 //Display New Index Info
		 //Print Index Info Index -> CourseCode -> Lesson Type -> Group -> Day -> Time -> Venue
		 mystudent.changeIndex(studentID, inputCrsCode,currIndex, newIndex);
	 }
	 /**
	  * Display SwapIndex interface, prompting student for input
	  * @param studentID
	  * @throws IOException
	  * @throws ParseException
	  */
	 public void swapIndexUI(String studentID) throws IOException, ParseException
	 {
		 ArrayList<Course> courseList = CourseDB.retrieveCourse();
		 StudentCtrl mystudent = new StudentCtrl();
		 String peerID = null;
		 int peerIndex = 0;
		 int ownIndex = 0;
		 String inputCrsCode = null;
		 boolean again = true;
		 boolean validInput = false;
			
		//Ask user to input the CourseCode
		while (!validInput)
		{
			inputCrsCode = getStringInput("Enter the course code: ");
			inputCrsCode = inputCrsCode.toUpperCase();
			for (Course course : courseList)
			{
				if(course.getCourseCode().equals(inputCrsCode))
				{
					validInput = true;
					break;
				}
				if(inputCrsCode.equals("-1")) return;
			}
			if (validInput == false)
			{
				System.out.println("No such coursecode... Please try again!");
			}
		}
		 
		 while(again)
		 {
			try	
			{
				ownIndex = getIntInput("Enter your own index number: ",0,99999);
				again = false;
				if(ownIndex == -1) return;
			}
			catch(Exception e)
			{
				System.out.println("Invalid Input! Please enter only valid number ");
			}
		 }
		 again = true;
		//Ask user to input Peer's studentID
		 peerID = getStringInput("Enter Peer's studentID: ");
		 if(peerID.equals("-1")) return;
		 
		 while(again)
		 {
			try	
			{
				peerIndex = getIntInput("Enter Peer's index number: ",0,99999);
				again = false;
				if(peerIndex == -1) return;
			}
			catch(Exception e)
			{
				System.out.println("Invalid Input! Please enter only valid number ");
			}
		 }
		 //Display New Index Info
		 //Print Index Info Index -> CourseCode -> Lesson Type -> Group -> Day -> Time -> Venue
		 mystudent.swapIdx(studentID, peerID, inputCrsCode, ownIndex, peerIndex);
	 }
	 public void courseRegisterUI(String studentID) throws IOException, ParseException
	 {
		 mystudent.printRegCourse(studentID);
	 }
}


	 
	 
	 





	
