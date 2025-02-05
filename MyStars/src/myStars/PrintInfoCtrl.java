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
			System.out.println("Course: " + course);
			System.out.println("Course Index");
			System.out.println("------------");
			for(Index index: indices){
				if(index.getCourseCode().equals(course))
					System.out.println(index.getIndex());
			}
		}catch(FileNotFoundException | ParseException e){
			e.printStackTrace();
		}
	}

	/**
	 * Prints out the list of courses available
	 */
	public void printCourse(){
		try {
			ArrayList<Course> courseList = CourseDB.retrieveCourse();

			System.out.println("Course\t\tCourse Name");
			System.out.println("---------------------------");
			for(Course course: courseList){
				System.out.println(course.getCourseCode() + "\t\t" + course.getCourseName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Prints out the list of students
	 */
	public void printStudents(){
		try {
			ArrayList<Student> students = StudentDB.retrieveStudent();
			System.out.println("Name\tMatriculation\tGender\tNationality");
			System.out.println("--------------------------------------------------------");
			for(Student student: students){
				System.out.println(student.getFirstName()+"\t"+ student.getMatricNum()+"\t"+student.getGender()+"\t"+student.getNationality());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
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

			System.out.println("Name\tMatriculation\tGender\tNationality");
			System.out.println("--------------------------------------------------------");
			for(Student student: students){
				if(matric.contains(student.getMatricNum())){
					System.out.println(student.getFirstName()+"\t"+ student.getMatricNum()+"\t"+student.getGender()+"\t"+student.getNationality());
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

			System.out.println("Name\tMatriculation\tGender\tNationality");
			System.out.println("--------------------------------------------------------");
			for(Student student: students){
				if(matric.contains(student.getMatricNum())){
					System.out.println(student.getFirstName()+"\t"+ student.getMatricNum()+"\t"+student.getGender()+"\t"+student.getNationality());
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
	/**
	 * Prints out all the courses registered by the students
	 * @param studentID
	 * @throws FileNotFoundException
	 * @throws ParseException
	 * @throws IOException
	 */
	public void printRegCourse(String studentID) throws FileNotFoundException, ParseException, IOException{
		ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
		ArrayList<Lesson> lessonList = LessonDB.retrieveLesson();
		ArrayList<Course> courseList = CourseDB.retrieveCourse();
		ArrayList<CourseRegister> stCrsReg = new ArrayList<CourseRegister>();
		for(CourseRegister course : courseRegistrations){
			if (course.getStudent().equals(studentID)){
				stCrsReg.add(course);
			}
		}
//		for (CourseRegister regCrs : stCrsReg) {
//			System.out.println(regCrs.getCourse()+ regCrs.getIndex() +regCrs.getStudent());
//		}
		System.out.println("The registered courses for this student " + studentID + " are as follows: \n");
		int AUcount =0;
		System.out.println("CourseCode\tIndex\tCourse Type\tAU\tStatus\t\tLesson Type\tLesson Venue\tLesson Day\tLesson Time");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
		for (CourseRegister regCrs : stCrsReg) {
			for (Course crs : courseList) {
				if(regCrs.getCourse().equals(crs.getCourseCode())) {
					System.out.print(crs.getCourseCode() + "\t\t");
					System.out.print(regCrs.getIndex() + "\t");
					System.out.print(crs.getCourseType()+"\t\t");
					System.out.print(crs.getCourseAU() + "\t");
					if (regCrs.getStatus() == true)
					{
						System.out.print("REGISTERED" + "\t");
					}
					else
					{
						System.out.print("ON WAITLIST" + "\t");
					}
					AUcount = AUcount + crs.getCourseAU();
					for (Lesson lesson : lessonList) {
						if(regCrs.getIndex()==lesson.getindexNo()) {
							System.out.print(lesson.getLessonType() + "\t\t");
							System.out.print(lesson.getLessonVenue()+ "\t\t");
							System.out.print(lesson.getLessonDay()+ "\t\t");
							System.out.print(lesson.getLessonTime());
							System.out.println();
							System.out.print("\t\t\t\t\t\t\t\t");
						}
					}
				}
			}
			System.out.println();
		}
		System.out.println("Total Course AU = " + AUcount);
		return;
	}
	/**
	 * Prints out all the information of this IndexNo
	 * @param IndexNo
	 * @param courseCode
	 * @throws FileNotFoundException
	 * @throws ParseException
	 * @throws IOException
	 */
	public void printIndexInfo(int IndexNo, String courseCode) throws FileNotFoundException, ParseException, IOException{
		ArrayList<Lesson> lessonList = LessonDB.retrieveLesson();
		ArrayList<Index> indexList = IndexDB.retrieveIndex();
		ArrayList<Course> courseList = CourseDB.retrieveCourse();
		for (Course crs : courseList) {
			for (Index idx : indexList) {
				if(idx.getIndex()==IndexNo && idx.getCourseCode().equals(courseCode)) {
					System.out.println("CourseCode\tIndex\tLesson Type\tLesson Venue\tLesson Day\tLesson Time");
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.print(courseCode + "\t\t");
					System.out.print(IndexNo + "\t");
					for(Lesson lesson : lessonList) {
						if (idx.getIndex() == lesson.getindexNo() && courseCode.equals(lesson.getCrsCode())) {
							System.out.print(lesson.getLessonType() + "\t\t");
							System.out.print(lesson.getLessonVenue()+ "\t\t");
							System.out.print(lesson.getLessonDay()+ "\t\t");
							System.out.print(lesson.getLessonTime());
							System.out.println();
							System.out.print("\t\t\t");
						}
					}
				}
			}
			return;
		}
	}
}
