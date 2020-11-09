package myStars;
import java.io.*;
import java.text.*;
import java.util.*;
import Entities.Student;
import Entities.Course;
import Entities.Index;
import Entities.Lesson;
import Entities.CourseRegister;
import DB.StudentDB;
import DB.CourseDB;
import DB.IndexDB;
import DB.CourseRegDB;

public class StudentCtrl {
	public void registerCourse(String studentID, String courseCode,int indexNo) throws FileNotFoundException, ParseException, IOException{
		System.out.println("Going to Registration");
		Student currentStudent = null;
		ArrayList<Student> studList = StudentDB.retrieveStudent();
		for (Student stud : studList) {
			if(stud.getUsername().equals(studentID)) {
				currentStudent = stud;
			}
		}
        Index currentIndex = null;
        ArrayList<Index> indexList = IndexDB.retrieveIndex();
		for (Index idx : indexList) {
			if(idx.getIndex()==indexNo) {
				currentIndex = idx;
				int vacancy = idx.getVacancy();
				int waitingList = idx.getWaitList();
				String registerStatus = "On Waiting List";
				String crsCode = idx.getCourseCode();
				if (idx.getVacancy() <= 0) {
					System.out.println("Sorry, the course has no vacancies any more.");
					waitingList++;
				    System.out.println("Student " + currentStudent.getUsername()+ " with ID: " + currentStudent.getId() +
			                " wants to register " + currentIndex.getCourseCode());
				    }
				else if (idx.getVacancy() > 0){
					vacancy--;
					registerStatus = "Registered";
				}
				// Adding
				boolean crsStt = false;
				if (registerStatus == "Registered") {crsStt = true;}
				else if (registerStatus == "On Waiting List") {crsStt = false;}
				CourseRegister newStudentCourse = new CourseRegister(studentID, courseCode, indexNo, crsStt);
				CourseRegDB.courseList.add(newStudentCourse);
				CourseRegDB.saveCourse(CourseRegDB.courseList);
			    
				// Update new vacancy & waiting list
				IndexDB.indexList.remove(idx); 
			    Index newIndex = new Index(idx.getCourseCode(), indexNo, idx.getGroup(), vacancy, waitingList);
			    IndexDB.indexList.add(newIndex);
			    IndexDB.saveIndex(IndexDB.indexList);
			
				System.out.println();
				if (registerStatus.equals("On Waiting List")){
					System.out.println("Due to lack of vacancy, your Index " + indexNo + " (" + courseCode + ") will be put into waiting list.");
				}
				else if (registerStatus.equals("Registered")){
					System.out.println("Index " + indexNo + " (" + courseCode + ") has been successfully added!");
				}
				return;
			}
		}
		
		if (this.checkCourseRegistrationExists(studentID, courseCode) == true) {
			System.out.println("This student already registers this course.");
            return;
        }	
		
	}
	public void dropCourse(String studentID, String courseCode,int indexNo) throws FileNotFoundException, ParseException, IOException{
		System.out.println("Going to Drop Registration");
		if (this.checkCourseRegistrationExists(studentID, courseCode) == false) {
			System.out.println("Sorry. This student has not yet registers this course.");
            return;
        }	
		ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
		ArrayList<Index> indexList = IndexDB.retrieveIndex();
		
		for(CourseRegister course : courseRegistrations){
			
			if (course.getIndex() == indexNo && course.getStudent().equals(studentID)){
				System.out.println("Index " + course.getIndex() + " (" + course.getCourse() + ") for student "+course.getStudent());
				CourseRegDB.courseList.remove(course);
				CourseRegDB.saveCourse(CourseRegDB.courseList);

				System.out.println("Index " + course.getIndex() + " (" + course.getCourse() + ") has been removed!");
				
				for (Index i : indexList){
					int vacancy = i.getVacancy();
					int waitingList = i.getWaitList();
					
					if (course.getStatus()==true){
						vacancy++;
					}
					else{
						waitingList--;
					}
					
					if (i.getIndex() == indexNo){
						// Update new vacancy & waiting list
						IndexDB.indexList.remove(i); 
					    Index newIndex = new Index(i.getCourseCode(), indexNo, i.getGroup(), vacancy, waitingList);
					    IndexDB.indexList.add(newIndex);
					    IndexDB.saveIndex(IndexDB.indexList);
						return;
					}
				}
			}
		}
		
	}
	public boolean checkCourseRegistrationExists(String studentID, String courseID) throws FileNotFoundException, ParseException{
        ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
        for(CourseRegister course:courseRegistrations) {
        	if(course.getCourse().equals(courseID)&& course.getStudent().equals(studentID)) {
        		
        		return true;
			}
        }
        return false;

    }
}
