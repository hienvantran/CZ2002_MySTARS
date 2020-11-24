package myStars;
import java.io.*;
import java.text.*;
import java.util.*;
import Entities.Student;
import Entities.Course;
import Entities.Index;
import Entities.Lesson;
import Entities.ModeType;
import Entities.CourseRegister;
import DB.StudentDB;
import DB.CourseDB;
import DB.IndexDB;
import DB.LessonDB;
import DB.CourseRegDB;

public class StudentCtrl {
	private static final int numAUlimit = 21;
	private CourseCtrl crsCtrl = new CourseCtrl();
	public void registerCourse(String studentID, String courseCode,int indexNo) throws FileNotFoundException, ParseException, IOException{
		System.out.println("Going to Registration");
		Student currentStudent = null;
		String studentEmail = null;
		ArrayList<Student> studList = StudentDB.retrieveStudent();
		ArrayList<Course> courseList = CourseDB.retrieveCourse();
		for (Course crs : courseList) {
			if(crs.getCourseCode().equals(courseCode)) {
				int crsAU = crs.getCourseAU();
				if (this.totalAU(studentID) + crsAU > numAUlimit) {
					System.out.println("Exceed the number of AUs can register per sem: " + numAUlimit + "AUs");
		            return;
				}
			}}
		for (Student stud : studList) {
			if(stud.getMatricNum().equals(studentID)) {
				currentStudent = stud;
				studentEmail = stud.getEmail();
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
				// get vacancy from the CourseCtrl method
				int vacancy = crsCtrl.noOfIndexVacancy(courseCode, indexNo);
				int totalSlot = idx.getTotalSlot();
				int waitingList = idx.getWaitList();
				String registerStatus = "On Waiting List";
				if (vacancy <= 0) {
					System.out.println("Sorry, the course has no vacancies any more.");
					waitingList++;
				    System.out.println("Student " + currentStudent.getUsername() + " wants to register " + currentIndex.getCourseCode());
				    }
				else{
					registerStatus = "Registered";
				}
				// Adding course
				boolean crsStt;
				if (registerStatus == "Registered") {crsStt = true;}
				else {crsStt = false;}
				CourseRegister newStudentCourse = new CourseRegister(studentID, courseCode, indexNo, crsStt);
				ArrayList<CourseRegister> crsReg = CourseRegDB.retrieveCourseRegister();
				crsReg.add(newStudentCourse);
				CourseRegDB.saveCourse(crsReg);
				// Update new vacancy & waiting list
			    Index newIndex = new Index(idx.getCourseCode(), indexNo, idx.getGroup(), totalSlot, waitingList);
			    idxList.add(newIndex);
				idxList.remove(idxList.get(indexList.indexOf(idx))); 
				IndexDB.saveIndex(idxList);
				if (registerStatus.equals("On Waiting List")){
					System.out.println("Due to lack of vacancy, your Index " + indexNo + " (" + courseCode + ") will be put into waiting list.");
					//NotificationCtrl.sendMail(studentEmail, courseCode, indexNo, 2, null, 0);
				}
				else if (registerStatus.equals("Registered")){
					System.out.println("Index " + indexNo + " (" + courseCode + ") has been successfully added!");
					//NotificationCtrl.sendMail(studentEmail,courseCode,indexNo,1,null,0);
				}
			break;
			}
		}
	}
	public void dropCourse(String studentID, String courseCode,int indexNo, boolean swapIdx) throws FileNotFoundException, ParseException, IOException{
		System.out.println("Going to Drop Registration");
		String studentEmail = null;
		ArrayList<Student> studList = StudentDB.retrieveStudent();
		for (Student stud : studList) {
			if(stud.getMatricNum().equals(studentID)) {
				studentEmail = stud.getEmail();
			}
		}
		if (this.checkCourseRegistrationExists(studentID, courseCode,indexNo) == false) {
			System.out.println("Sorry. This student has not yet registered this course.");
            return;
        }	
		ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
		ArrayList<CourseRegister> courseReg = CourseRegDB.retrieveCourseRegister();
		ArrayList<Index> indexList = IndexDB.retrieveIndex();

		for(CourseRegister course : courseRegistrations){
			if (course.getIndex() == indexNo && course.getStudent().equals(studentID)){
				// need to edit
				for (Index i : indexList){
					// get from CourseCtrl vacancy
					if (i.getIndex() == indexNo  && i.getCourseCode().equals(courseCode)){
						// Update new vacancy & waiting list
						int totalSlot = i.getTotalSlot();
						int waitingList = i.getWaitList();
						if (course.getStatus()==true){waitingList--;}
						ArrayList<Index> idxList = IndexDB.retrieveIndex();
						Index removedIdx = idxList.get(indexList.indexOf(i));
						Index newIndex = new Index(i.getCourseCode(), indexNo, i.getGroup(), totalSlot, waitingList);
					    idxList.add(newIndex);
						idxList.remove(removedIdx); 
					    IndexDB.saveIndex(idxList);
					}
				}
				
				courseReg.remove(courseReg.get(courseRegistrations.indexOf(course)));
				CourseRegDB.saveCourse(courseReg);
				
				if(swapIdx==false) {
					crsCtrl.updateRegisteredList(courseCode, indexNo, ModeType.USER);
					}
				
				System.out.println("Index " + indexNo + " (" + courseCode +  " for student "+course.getStudent() + ") has been removed!");
				//NotificationCtrl.sendMail(studentEmail,courseCode,indexNo,3,null,0);

				break;
			}

		}
	}
	
	public  int totalAU(String studentID) throws FileNotFoundException, ParseException, IOException{
		ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
		ArrayList<Course> courseList = CourseDB.retrieveCourse();
		ArrayList<CourseRegister> stCrsReg = new ArrayList<CourseRegister>();
		for(CourseRegister course : courseRegistrations){
			if (course.getStudent().equals(studentID) && course.getStatus()==true){
				stCrsReg.add(course);
			}
		}
		int AUcount = 0;
		for (CourseRegister regCrs : stCrsReg) {
			for (Course crs : courseList) {
				if(regCrs.getCourse().equals(crs.getCourseCode())) {	
					AUcount = AUcount + crs.getCourseAU();
				}
			}
		}
		return AUcount;
	}
	
	public void checkVacancy(String courseID) throws FileNotFoundException, ParseException, IOException{
		ArrayList<Index> indexList = IndexDB.retrieveIndex();
		System.out.println("The vacancy for this courses " + courseID + " is \n");
		for(Index idx:indexList) {
			if (idx.getCourseCode().equals(courseID)) {
				System.out.println("Index " + idx.getIndex() + " has " 
						+ crsCtrl.noOfIndexVacancy(courseID, idx.getIndex()) 
						+ "/" + crsCtrl.noOfIndexTotalSlot(courseID, idx.getIndex()) + " (vacancy/total size)");
			}
		}

	}
	public void changeIndex(String studentID, String courseCode,int curidxNo, int newidxNo) throws FileNotFoundException, ParseException, IOException{
		System.out.println("Going to change index");
		ArrayList<Student> studList = StudentDB.retrieveStudent();
		ArrayList<Index> indexList = IndexDB.retrieveIndex();
		if (this.checkCourseRegistrationExists(studentID, courseCode, curidxNo) == false) {
			System.out.println("Sorry. This student has not yet registers this course.");
            return;
        }	
		else {
			String studentEmail = null;
			for (Student stud : studList) 
			{
				if(stud.getMatricNum().equals(studentID)) 
				{
					studentEmail = stud.getEmail();
				}
			}
        	System.out.println("Do you want to change from index " + curidxNo + " to " + newidxNo + "? (Y|N) \n");
        	Scanner sc= new Scanner(System.in);
        	String sel= sc.nextLine();
        	switch(sel.toUpperCase()) {
        	  case "Y":
                  int vacancy;
                  for(Index idx:indexList) 
                  {
                	  if (idx.getCourseCode().equals(courseCode) && idx.getIndex()==newidxNo)
                	  {
                		  vacancy =  crsCtrl.noOfIndexVacancy(courseCode, idx.getIndex());
                		  if (vacancy>0) 
                		  {
                			  if (this.checkCourseClash(studentID, courseCode, newidxNo) == false) 
                			  {
                				  this.dropCourse(studentID, courseCode, curidxNo, false);
                				  this.registerCourse(studentID, courseCode, newidxNo);
                				  //NotificationCtrl.sendMail(studentEmail,courseCode,newidxNo,4,null,0);
                			  }
                		  }
                		  else
                    	  {
                    		  System.out.println("no more vacancy, no change!");
                    	  }
                	  }
                	  
                   }
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
		ArrayList<Student> studList = StudentDB.retrieveStudent();
		System.out.println("Go to swap index");
		System.out.println("Swap index with student " + peerStudId + "(his index " + peerIdx + ")");
		String studentEmail = null;
		String peerEmail = null;
		for (Student stud : studList) 
		{
			if(stud.getMatricNum().equals(ownStudId)) 
			{
				studentEmail = stud.getEmail();
			}
			if(stud.getMatricNum().equals(peerStudId)) 
			{
				peerEmail = stud.getEmail();
			}
		}

		this.dropCourse(ownStudId, courseCode, yourIdx,true);
		this.dropCourse(peerStudId, courseCode, peerIdx,true);
		this.registerCourse(ownStudId, courseCode, peerIdx);
		this.registerCourse(peerStudId, courseCode, yourIdx);
		System.out.println("Swap index successfully");
		NotificationCtrl.sendMail(studentEmail,courseCode,yourIdx,5,peerStudId,peerIdx);
		NotificationCtrl.sendMail(peerEmail,courseCode,peerIdx,5,ownStudId,yourIdx);
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
		ArrayList<CourseRegister> courseRegistrations = CourseRegDB.retrieveCourseRegister();
        ArrayList<Lesson> lessonList = LessonDB.retrieveLesson();
        boolean clash = false;
        for (CourseRegister regCrs: courseRegistrations) 
        {
        	if (regCrs.getCourse().equals(courseCode)) return false;
        }
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
