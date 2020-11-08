package DB;

import java.io.*;
import java.text.*;
import java.util.*;

import Entities.*;

public class AdminDBApp {

	public static void main(String[] args) throws FileNotFoundException,ParseException {
		// TODO Auto-generated method stub
		//admin
//		ArrayList<User> admin = new ArrayList<User>();
//		AdminDB adminDB = new AdminDB();
//		admin = adminDB.retrieveAdmin();
//		System.out.println("My Admin are " + admin);
//		//student
//		ArrayList<Student> stud = new ArrayList<Student>();
//		StudentDB studDB = new StudentDB();
//		stud = studDB.retrieveStudent();
//		System.out.println("My Student are " + stud);
//		//course
//		ArrayList<Course> crs = new ArrayList<Course>();
//		CourseDB crsDB = new CourseDB();
//		crs = crsDB.retrieveCourse();
//		System.out.println("My Course are " + crs);
		//course reg
//		ArrayList<CourseRegister> crsReg = new ArrayList<CourseRegister>();
//		CourseRegDB crsRegDB = new CourseRegDB();
//		crsReg = crsRegDB.retrieveCourseRegister();
//		System.out.println("My Course Registered are " + crsReg);		
		//index
//		ArrayList<Index> idxReg = new ArrayList<Index>();
//		IndexDB idxRegDB = new IndexDB();
//		idxReg = idxRegDB.retrieveIndex();
//		System.out.println("My index are " + idxReg);	
		//lesson
		ArrayList<Lesson> lessons = new ArrayList<Lesson>();
		LessonDB lessonDB = new LessonDB();
		lessons = lessonDB.retrieveLesson();
		for(Lesson lesson:lessons) {
		System.out.println("My Lesson are " + lesson.getCrsCode());	}
	}

}
