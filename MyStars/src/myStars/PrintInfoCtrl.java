package myStars;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import DB.CourseRegDB;
import Entities.CourseRegister;


public class PrintInfoCtrl {
	
	public static void printRegCourse(String studentID) throws FileNotFoundException, ParseException, IOException
	{
		ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
		ArrayList<CourseRegister> stCrsReg = new ArrayList<CourseRegister>();
		for(CourseRegister course : courseRegistrations){
			
			if (course.getStudent().equals(studentID)){
				stCrsReg.add(course);
			}
		}
		System.out.println("The registered courses for this student " + studentID + " is \n");
		for (CourseRegister regCrs : stCrsReg) {
			System.out.println("Index " + regCrs.getIndex() + " (" + regCrs.getCourse() + ") ");
		}
	}
	
}
