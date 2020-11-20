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
import DB.LessonDB;
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
		if (this.checkCourseRegistrationExists(studentID, courseCode,indexNo) == true) {
			System.out.println("This student already registers this course.");
            return;
        }	
		if (this.checkCourseClash(studentID, courseCode, indexNo)) {
			System.out.println("This course is clashed, cannot add");
            return;
		}
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
					if (course.getStatus()==true){vacancy++;}
					else{waitingList--;}
					if (i.getIndex() == indexNo  && i.getCourseCode().equals(courseCode)){
						// Update new vacancy & waiting list
						ArrayList<Index> idxList = IndexDB.retrieveIndex();
						Index removedIdx = idxList.get(indexList.indexOf(i));
						Index newIndex = new Index(i.getCourseCode(), indexNo, i.getGroup(), vacancy, waitingList);
					    idxList.add(newIndex);
						idxList.remove(removedIdx); 
					    IndexDB.saveIndex(idxList);
					}
				}
				break;
			}

		}

		
	}
	
	public ArrayList<CourseRegister> printRegCourse(String studentID) throws FileNotFoundException, ParseException, IOException{
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
		return stCrsReg;
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
	public boolean checkCourseClash(String studentID, String courseCode, int indexNo) throws FileNotFoundException, ParseException, IOException{
        System.out.println("Going to clash! ");
		ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
        ArrayList<Lesson> lessonList = LessonDB.retrieveLesson();
        boolean clash = false;
        if (this.checkCourseRegistrationExists(studentID, courseCode,indexNo) == false) {
        	// get the lesson time for desired reg course
        	String lecDay = new String();
        	String tutDay = new String();
        	String labDay = new String();
            ArrayList<Double> lechourMin = new ArrayList<Double>();
            ArrayList<Double> tuthourMin = new ArrayList<Double>();
            ArrayList<Double> labhourMin = new ArrayList<Double>();
        	for(Lesson lesson:lessonList) {
            	if(lesson.getCrsCode().equals(courseCode)&&lesson.getindexNo()==indexNo) {
            		if(lesson.getLessonType().equals("LEC")) {
            			lecDay = lesson.getLessonDay();
            			double sHour = Double.parseDouble(lesson.getLessonTime().substring(0,2)) + 0.5;
            			double eHour = Double.parseDouble(lesson.getLessonTime().substring(5,7)) + 0.5;
            			System.out.println(sHour + " " + eHour);
            			lechourMin.add(sHour);
            			lechourMin.add(eHour);

            					}
            		if(lesson.getLessonType().equals("TUT")) {
            			tutDay = lesson.getLessonDay();
            			double sHour = Double.parseDouble(lesson.getLessonTime().substring(0,2)) + 0.5;
            			double eHour = Double.parseDouble(lesson.getLessonTime().substring(5,7)) + 0.5;
            			tuthourMin.add(sHour);
            			tuthourMin.add(eHour);
 
            					}
            		if(lesson.getLessonType().equals("LAB")) {
            			labDay = lesson.getLessonDay();
            			double sHour = Double.parseDouble(lesson.getLessonTime().substring(0,2)) + 0.5;
            			double eHour = Double.parseDouble(lesson.getLessonTime().substring(5,7)) + 0.5;
            			labhourMin.add(sHour);
            			labhourMin.add(eHour);

            					}
    			}
            }
        	
        	// iterate through registered crs to check if the desired one clash or not
    		ArrayList<CourseRegister> crsReg = new ArrayList<CourseRegister>();
    		for(CourseRegister course : courseRegistrations){
    			if (course.getStudent().equals(studentID)){
    				crsReg.add(course);
    			}
    		}
        	for (CourseRegister crs: crsReg) {
        		int idxNo = crs.getIndex();
        		String crsCode = crs.getCourse();
        		for(Lesson lesson:lessonList) {
                	if(lesson.getCrsCode().equals(crsCode)&&lesson.getindexNo()==idxNo) {
                		// get reg crs lesson time
                		String crsDay = lesson.getLessonDay();
                		String crsTime = lesson.getLessonTime();
                		double sHour = Double.parseDouble(crsTime.substring(0,2)) + 0.5;
            			double eHour = Double.parseDouble(crsTime.substring(5,7)) + 0.5;
                		if(crsDay.equals(lecDay)) {                				
                    			if (lechourMin.get(0)<= sHour && sHour <= lechourMin.get(1) ) {
                    				System.out.println("CLASH Lec!!! "+ lechourMin.get(0) + ":" + lechourMin.get(1) 
                    									+" is clashed " +sHour + " of course "+ crsCode + " with index "+idxNo);
                    				clash =  true;
                    			} else if (lechourMin.get(0)<= eHour && eHour <= lechourMin.get(1) ) {
                    				System.out.println("CLASH Lec!!! "+ lechourMin.get(1) + ":" + lechourMin.get(1) 
									+" is clashed " +sHour + " of course "+ crsCode + " with index "+idxNo);
                    				clash = true;
                    			}
                			}
                		
                	
                		else if(crsDay.equals(tutDay)) {                			
                    			if (tuthourMin.get(0)<= sHour && sHour <= tuthourMin.get(1)) {
                    				System.out.println("CLASH tut!!! "+ tuthourMin.get(0) + ":" + tuthourMin.get(1) 
                    									+" is clashed " +sHour + " of course "+ crsCode + " with index "+idxNo);
                    				clash =  true;
                    			} else if (tuthourMin.get(0)<= eHour && eHour <= tuthourMin.get(1) ) {
                    				System.out.println("CLASH Lec!!! "+ tuthourMin.get(1) + ":" + tuthourMin.get(1) 
									+" is clashed " +sHour + " of course "+ crsCode + " with index "+idxNo);
                    				clash = true;
                    			}
                			}
                		else if(crsDay.equals(labDay)) {              
                    			if (labhourMin.get(0)<= sHour && sHour <= labhourMin.get(1)) {
                    				System.out.println("CLASH Lab!!! "+ labhourMin.get(0) + ":" + labhourMin.get(1) 
                    									+" is clashed " +sHour + " of course "+ crsCode + " with index "+idxNo);
                    				clash =  true;
                    			} else if (labhourMin.get(0)<= eHour && eHour <= labhourMin.get(1) ) {
                    				System.out.println("CLASH Lec!!! "+ labhourMin.get(1) + ":" + labhourMin.get(1) 
									+" is clashed " +sHour + " of course "+ crsCode + " with index "+idxNo);
                    				clash = true;
                    			
                			}
                		}
        			}
                }
        		
        	}
        	
        }
        
        return clash;

    }
	
}
