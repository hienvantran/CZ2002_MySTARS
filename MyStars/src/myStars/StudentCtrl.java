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
		ArrayList<Index> idxList = IndexDB.retrieveIndex();
		System.out.println(indexList.size());
		for (Index idx : indexList) {
			if(idx.getIndex()==indexNo && idx.getCourseCode().equals(courseCode)) {
				currentIndex = idx;
				int vacancy = idx.getVacancy();
				int waitingList = idx.getWaitList();
				String registerStatus = "On Waiting List";
				String crsCode = idx.getCourseCode();
				if (idx.getVacancy() <= 0) {
					System.out.println("Sorry, the course has no vacancies any more.");
					waitingList++;
				    System.out.println("Student " + currentStudent.getUsername() + " wants to register " + currentIndex.getCourseCode());
				    break;
				    }
				else if (idx.getVacancy() > 0){
					vacancy--;
					registerStatus = "Registered";
					
				}
				// Adding course
				boolean crsStt = false;
				if (registerStatus == "Registered") {crsStt = true;}
				else if (registerStatus == "On Waiting List") {crsStt = false;}
				CourseRegister newStudentCourse = new CourseRegister(studentID, courseCode, indexNo, crsStt);
				ArrayList<CourseRegister> crsReg = CourseRegDB.retrieveCourseRegister();
				crsReg.add(newStudentCourse);
				CourseRegDB.saveCourse(crsReg);
			    
				// Update new vacancy & waiting list

				System.out.println(idxList.size());
			    Index newIndex = new Index(idx.getCourseCode(), indexNo, idx.getGroup(), vacancy, waitingList);
			    idxList.add(newIndex);
				idxList.remove(idxList.get(indexList.indexOf(idx))); 
				IndexDB.saveIndex(idxList);
				System.out.println(idxList.size());
				if (registerStatus.equals("On Waiting List")){
					System.out.println("Due to lack of vacancy, your Index " + indexNo + " (" + courseCode + ") will be put into waiting list.");
				}
				else if (registerStatus.equals("Registered")){
					System.out.println("Index " + indexNo + " (" + courseCode + ") has been successfully added!");
				}
			break;
			}
		}
		
		if (this.checkCourseRegistrationExists(studentID, courseCode,indexNo) == true) {
			System.out.println("This student already registers this course.");
            return;
        }	
		
	}
	public void dropCourse(String studentID, String courseCode,int indexNo) throws FileNotFoundException, ParseException, IOException{
		System.out.println("Going to Drop Registration");
		if (this.checkCourseRegistrationExists(studentID, courseCode,indexNo) == false) {
			System.out.println("Sorry. This student has not yet registered this course.");
            return;
        }	
		ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
		
		ArrayList<Index> indexList = IndexDB.retrieveIndex();
		for(CourseRegister course : courseRegistrations){
			if (course.getIndex() == indexNo && course.getStudent().equals(studentID)){
				ArrayList<CourseRegister> courseReg = CourseRegDB.retrieveCourseRegister();
				courseReg.remove(courseReg.get(courseRegistrations.indexOf(course)));
				CourseRegDB.saveCourse(courseReg);
				System.out.println("Index " + indexNo + " (" + courseCode +  " for student "+course.getStudent() + ") has been removed!");
				for (Index i : indexList){
					int vacancy = i.getVacancy();
					int waitingList = i.getWaitList();
					if (course.getStatus()==true){
						vacancy++;
					}
					else{
						waitingList--;
					}
					
					if (i.getIndex() == indexNo  && i.getCourseCode().equals(courseCode)){
						// Update new vacancy & waiting list
						ArrayList<Index> idxList = IndexDB.retrieveIndex();
						Index removedIdx = idxList.get(indexList.indexOf(i));
						System.out.println(removedIdx.getIndex());
						Index newIndex = new Index(i.getCourseCode(), indexNo, i.getGroup(), vacancy, waitingList);
					    idxList.add(newIndex);
						idxList.remove(removedIdx); 
					    IndexDB.saveIndex(idxList);
					    System.out.println("Done");
					}
				}
				break;
			}

		}

		
	}
	
	public void printRegCourse(String studentID) throws FileNotFoundException, ParseException, IOException{
		ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
		ArrayList<CourseRegister> stCrsReg = new ArrayList<CourseRegister>();
		for(CourseRegister course : courseRegistrations){
			if (course.getStudent().equals(studentID)){
				stCrsReg.add(course);
			}
		}
		System.out.println("The registered courses for this student " + studentID + " is ");
		for (CourseRegister regCrs : stCrsReg) {
			System.out.println("Index " + regCrs.getIndex() + " (" + regCrs.getCourse() + ") ");
		}
	}
	
	public void checkVacancy(String courseID) throws FileNotFoundException, ParseException, IOException{
		ArrayList<Index> indexList = IndexDB.retrieveIndex();
		ArrayList<Index> crsindexList = new ArrayList<Index>();
		HashMap<Integer, Integer> idxVacancy = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> idxWaitlist = new HashMap<Integer, Integer>();
		for(Index idx:indexList) {
			if (idx.getCourseCode().equals(courseID)) {
				idxVacancy.put(idx.getIndex(), idx.getVacancy());
				idxWaitlist.put(idx.getIndex(), idx.getWaitList());
			}
		}
		System.out.println("The vacancy for this courses " + courseID + " is \n");
		for (int i : idxVacancy.keySet()) {
			System.out.println("Index " + i + " has " + idxVacancy.get(i) + " vacancies ");
		}
		
	}
	public void changeIndex(String studentID, String courseCode,int curidxNo, int newidxNo) throws FileNotFoundException, ParseException, IOException{
		System.out.println("Going to change index");
		if (this.checkCourseRegistrationExists(studentID, courseCode, curidxNo) == false) {
			System.out.println("Sorry. This student has not yet registers this course.");
            return;
        }	else {
        	System.out.println("Do you want to change from index " + curidxNo + " to " + newidxNo + "? (Y|N) \n");
        	Scanner sc= new Scanner(System.in);
        	String sel= sc.nextLine();
        	switch(sel) {
        	  case "Y":
        		  this.dropCourse(studentID, courseCode, curidxNo);
        		  this.registerCourse(studentID, courseCode, newidxNo);
        		  break;
        	  case "N":
        		  System.out.println("Come back to last page!");
        	    break;
        	  default:
        		  System.out.println("Invalid input!");
        	}
        }
	}
	public void swapIdx(String ownStudId, String peerStudId, String courseCode, int yourIdx, int peerIdx) throws FileNotFoundException, 
	 ParseException, IOException {
		System.out.println("Go to swap index");
		System.out.println("Swap index with student " + peerStudId + "(his index " + peerIdx + ")");

			this.dropCourse(ownStudId, courseCode, yourIdx);
			this.dropCourse(peerStudId, courseCode, peerIdx);
			this.registerCourse(ownStudId, courseCode, peerIdx);
			this.printRegCourse(ownStudId);
			this.registerCourse(peerStudId, courseCode, yourIdx);
			this.printRegCourse(peerStudId);
			System.out.println("Swap index successfully");
	}
	
	public boolean checkCourseRegistrationExists(String studentID, String courseID, int indexNo) throws FileNotFoundException, ParseException{
        ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
        for(CourseRegister course:courseRegistrations) {
        	if(course.getCourse().equals(courseID)&& course.getStudent().equals(studentID) &&course.getIndex()==indexNo) {
        		
        		return true;
			}
        }
        return false;

    }
}
