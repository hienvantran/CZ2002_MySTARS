package myStars;

import java.io.*;
import java.text.*;
import java.util.*;
import Entities.Course;
import Entities.Index;
import DB.CourseDB;
import DB.IndexDB;

public class CourseCtrl {
	public Course getCrsbyCode(String CourseCode)throws IOException, ParseException {
		ArrayList<Course> courseList = CourseDB.retrieveCourse();
		Course myCourse = new Course("Unknown", "Unknown", 0, "Unknown","Unknown");
		for(Course c : courseList){
			if (c.getCourseCode()==CourseCode) 
				System.out.println("The course: "+ c.getCourseCode()+ "," + c.getCourseName());
				myCourse = c;
			} 
		return myCourse;
	}
	public Index getIndexbyCode(String CourseCode, int indexNo) throws IOException, ParseException{
		ArrayList<Index> indexList = IndexDB.retrieveIndex();
		Index myIndex = new Index("Unknown", 0, "Unknown", 0, 0);
		for(Index i : indexList){
			if (i.getIndex()==indexNo && i.getCourseCode().equals(CourseCode))
				myIndex= i;
			}
		return myIndex;
		}
		
	
	
}
