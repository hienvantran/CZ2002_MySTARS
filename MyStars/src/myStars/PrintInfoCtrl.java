package myStars;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import DB.*;
import Entities.*;


public class PrintInfoCtrl {
	/**
	 * Prints out the list of indices based on the course code
	 * @param course The course's code
	 */
	public void printIndexByCourse(String course){
		try{
			ArrayList<Index> indices = IndexDB.retrieveIndex();
			for(Index index: indices){
				System.out.println(index.getIndex());
			}
		}catch(FileNotFoundException | ParseException e){
			e.printStackTrace();
		}
	}

	/**
	 * Prints out the list of students registered to the course by indices and course code
	 * This method excludes those students whose status is "On waiting list"
	 * @param indexNum The course's index number
	 * @param course The course's code
	 */
	public void printStudByIndex(int indexNum, String course){
		int count = 0;
		try {
			ArrayList<String> matric = new ArrayList<>();
			ArrayList<Student> students = StudentDB.retrieveStudent();
			ArrayList<CourseRegister> courseRegDBS = CourseRegDB.retrieveCourseRegister();
			for(CourseRegister courseRegister: courseRegDBS){
				if(courseRegister.getIndex()==indexNum && courseRegister.getCourse().equals(course) && courseRegister.getStatus()==true)
					matric.add(courseRegister.getStudent());
			}
			for(Student student: students){
				if(matric.contains(student.getMatricNum())){
					System.out.println(student.getMatricNum());
					count++;
				}
			}
			if(count==0) System.out.println("There are no students registered with: " + course + ", " + indexNum);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prints out the list of registered based on a course code
	 * @param course the course's code
	 */
	public void printStudByCourse(String course){
		int count = 0;
		try {
			ArrayList<String> matric = new ArrayList<>();
			ArrayList<Student> students = StudentDB.retrieveStudent();
			ArrayList<CourseRegister> courseRegDBS = CourseRegDB.retrieveCourseRegister();
			for(CourseRegister courseRegister: courseRegDBS){
				if(courseRegister.getCourse().equals(course) && courseRegister.getStatus()==true)
					matric.add(courseRegister.getStudent());
			}
			for(Student student: students){
				if(matric.contains(student.getMatricNum())){
					System.out.println("Student name: " + student.getFirstName()+ " " + student.getFirstName()
							+ " Gender: " + student.getGender() + "Nationality: " + student.getNationality());
					count++;
				}
			}
			if(count==0) System.out.println("There are no students registered with: " + course + ".");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public void printRegCourse(String studentID) throws FileNotFoundException, ParseException, IOException{
		ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
		ArrayList<Lesson> lessonList = LessonDB.retrieveLesson();
		ArrayList<Course> courseList = CourseDB.retrieveCourse();
		ArrayList<CourseRegister> stCrsReg = new ArrayList<CourseRegister>();
		for(CourseRegister course : courseRegistrations){
			if (course.getStudent().equals(studentID) && course.getStatus()==true){
				stCrsReg.add(course);
			}
		}
		for (CourseRegister regCrs : stCrsReg) {
			System.out.println(regCrs.getCourse()+ regCrs.getIndex() +regCrs.getStudent());
		}
		System.out.println("The registered courses for this student " + studentID + " are as follows ");
		int AUcount =0;
		for (CourseRegister regCrs : stCrsReg) {
			System.out.println(regCrs.getCourse());
			for (Course crs : courseList) {
				if(regCrs.getCourse().equals(crs.getCourseCode())) {
					System.out.println("Course Code " + crs.getCourseCode() + " ( index no." + regCrs.getIndex() +") " + " AU: " + crs.getCourseAU());
					AUcount = AUcount + crs.getCourseAU();
					System.out.println("Scheduled lessons for this index:");
					for (Lesson lesson : lessonList) {
						if(regCrs.getIndex()==lesson.getindexNo()) {
							System.out.print("\t");
							System.out.println(lesson.getLessonType() + " at " + lesson.getLessonVenue() + " on " + lesson.getLessonDay()+ " " + lesson.getLessonTime() + " (" + lesson.getindexNo() + ") ");
						}
					}
				}
			}
		}
		System.out.println("total Au = " + AUcount);
		return;
	}
	
}
