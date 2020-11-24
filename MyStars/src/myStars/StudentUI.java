
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
 * Student's user interface
 * Display different kinds of options for students to choose from
 */
	public class StudentUI extends UserInterface{
	/**
	 * A new object for Scanner
	 */
	Scanner sc = new Scanner(System.in);
	/**
	 * A new object for StudentCtrl
	 */
	StudentCtrl mystudent = new StudentCtrl();
	/**
	 * A new object for CourseCtrl
	 */
	CourseCtrl courseCtrl = new CourseCtrl();
	/**
	 * A new object for PrintInfoCtrl
	 */
	PrintInfoCtrl printInfo = new PrintInfoCtrl();
	/**
	 * default constructor
	 */
	public StudentUI() {
	}
	/**
	 * Provide the user-interface for the StudentUI
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
	 * Provide the UI for student to register for a course
	 * @param studentID
	 * @throws ParseException
	 * @throws IOException
	 */
	public void registerCourseUI(String studentID) throws ParseException, IOException
	{
		//retrieve indexList/CourseList
		ArrayList<Index> indexList = IndexDB.retrieveIndex();
		
		int inputIndex = 0;
		String inputCrsCode = null;
		boolean again = true;
		boolean validInput = false;

		//Ask student to input CourseCode
		while(!validInput){
			inputCrsCode = getStringInput("Enter Course Code: ");
			inputCrsCode = inputCrsCode.toUpperCase();
			if(inputCrsCode.equals("-1")) return;

			if(courseCtrl.isExistingCourse(inputCrsCode)==false){
				System.out.println("Input courseCode does not exists!");
				System.out.println("Please try again!");
			} 
			else {validInput = true;}
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
		printInfo.printIndexInfo(inputIndex, inputCrsCode);
		System.out.println("\nConfirm to register this index? Please enter (Y|N) ");
		String sel= sc.nextLine();
		sel = sel.toUpperCase();
		
		if(sel.equals("Y"))
		{
			mystudent.registerCourse(studentID,inputCrsCode,inputIndex);
		}
	 }
	/**
	 * Provide the UI for student to drop the course
	 * @param studentID
	 * @throws ParseException
	 * @throws IOException
	 */
	 public void dropCourseUI(String studentID) throws ParseException, IOException
	 {
		//Display the list of registeredCourses
		printInfo.printRegCourse(studentID);
		
		int inputIndex = 0;
		String inputCrsCode = null;
		boolean again = true;
		boolean validInput = false;
		
		//Ask user to input the CourseCode
		while(!validInput){
			inputCrsCode = getStringInput("Enter Course Code you want to DROP: ");
			inputCrsCode = inputCrsCode.toUpperCase();
			if(inputCrsCode.equals("-1")) return;

			if(courseCtrl.isExistingCourse(inputCrsCode)==false){
				System.out.println("Input courseCode does not exists!");
				System.out.println("Please try again!");
			} 
			else {validInput = true;}
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
		printInfo.printIndexInfo(inputIndex, inputCrsCode);
		System.out.println("\nConfirm to DROP this index? Please enter (Y|N) ");
		String sel= sc.nextLine();
		sel = sel.toUpperCase();
		
		if(sel.equals("Y"))
		{
			mystudent.dropCourse(studentID,inputCrsCode,inputIndex);
		}
		System.out.println();
	 }
	 /**
	  * Provide the UI for student to check vacancy slot for the course
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
		 mystudent.checkVacancy(courseCode);
	 }
	 /**
	  * Provide the UI for student to change index
	  * @param studentID
	  * @throws IOException
	  * @throws ParseException
	  */
	 public void changeIndexUI(String studentID) throws IOException, ParseException
	 {
		 int currIndex = 0;
		 int newIndex = 0;
		 String inputCrsCode = null;
		 boolean again = true;
		 boolean validInput = false;
			
		//Ask user to input the CourseCode
		 while(!validInput){
				inputCrsCode = getStringInput("Enter Course Code: ");
				inputCrsCode = inputCrsCode.toUpperCase();
				if(inputCrsCode.equals("-1")) return;

				if(courseCtrl.isExistingCourse(inputCrsCode)==false){
					System.out.println("Input courseCode does not exists!");
					System.out.println("Please try again!");
				} 
				else {validInput = true;}
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
		 mystudent.changeIndex(studentID, inputCrsCode,currIndex, newIndex);
	 }
	 /**
	  * Provide the UI for student to swap index with their peer's index
	  * @param studentID
	  * @throws IOException
	  * @throws ParseException
	  */
	 public void swapIndexUI(String studentID) throws IOException, ParseException
	 {
		 String peerID = null;
		 int peerIndex = 0;
		 int ownIndex = 0;
		 String inputCrsCode = null;
		 boolean again = true;
		 boolean validInput = false;
			
		//Ask user to input the CourseCode
		 while(!validInput){
				inputCrsCode = getStringInput("Enter Course Code: ");
				inputCrsCode = inputCrsCode.toUpperCase();
				if(inputCrsCode.equals("-1")) return;

				if(courseCtrl.isExistingCourse(inputCrsCode)==false){
					System.out.println("Input courseCode does not exists!");
					System.out.println("Please try again!");
				} 
				else {validInput = true;}
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
	 /**
	  * Provide the UI for student view their registered courses
	  * @param studentID
	  * @throws IOException
	  * @throws ParseException
	  */
	 public void courseRegisterUI(String studentID) throws IOException, ParseException
	 {
		 printInfo.printRegCourse(studentID);
	 }
}


	 
	 
	 





	
