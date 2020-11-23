package myStars;
import java.io.*;
import java.text.*;
import java.util.*;

import DB.CourseRegDB;
import DB.IndexDB;
import Entities.CourseRegister;
import Entities.Index;

public class StudCrsCtrlApp {

	public static void main(String[] args) throws FileNotFoundException, ParseException, IOException
	{
		// TODO Auto-generated method stub
		StudentUI studentUI = new StudentUI();

		Scanner sc= new Scanner(System.in); //System.in is a standard input stream.

		// studentID
		System.out.print("Enter student ID- ");
		String studentID= sc.next();
		System.out.print("Your entered student ID- " + studentID +"\n");

		studentUI.printUI(studentID);
	}
}

