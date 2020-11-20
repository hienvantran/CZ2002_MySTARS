package myStars;

import java.io.*;
import java.text.*;
import java.util.*;
import Entities.Course;
import Entities.Index;
import DB.CourseDB;
import DB.IndexDB;

public class CourseCtrl {
	public ArrayList<Course> getCourseList()throws IOException, ParseException {
		ArrayList<Course> courseList = CourseDB.retrieveCourse();
		return courseList;
	}
	public void setCourseList(ArrayList<Course> courseList)throws IOException, ParseException {
		CourseDB.saveCourse(courseList);
	}
	public Course getCrsbyCode(String CourseCode)throws IOException, ParseException {
		ArrayList<Course> courseList = CourseDB.retrieveCourse();
		Course myCourse = new Course("Unknown", "Unknown", 0, "Unknown","Unknown");
		System.out.println(myCourse.getCourseCode());
		for(Course c : courseList){
			if (c.getCourseCode().equals(CourseCode)){
				System.out.println("The course: "+ c.getCourseCode()+ "," + c.getCourseName());
				myCourse = c;
			}
		}

		return myCourse;
	}
	public ArrayList<Index> getIndexList()throws IOException, ParseException {
		ArrayList<Index> indexList = IndexDB.retrieveIndex();
		return indexList;
	}
	public void setIndexList(ArrayList<Index> indexList)throws IOException, ParseException {
		IndexDB.saveIndex(indexList);
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
