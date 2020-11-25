package myStars;

import java.io.*;
import java.text.*;
import java.util.*;
<<<<<<< HEAD

import DB.LessonDB;
import Entities.*;
=======
import Entities.Course;
import Entities.CourseRegister;
import Entities.Index;
import Entities.ModeType;
import Entities.Student;
>>>>>>> 751df938f4b707772b0ec16eedab8819446e5382
import DB.CourseDB;
import DB.CourseRegDB;
import DB.IndexDB;
import DB.StudentDB;

public class CourseCtrl {

    /**
     * Changes student's status from "On waiting list" to "Registered" on a first
     * come first serve basis based on the course code and course index
     * Admin will be informed of those student whose status changed from "On waiting
     * list" to "Registered"
     * @param courseCode the course code
     * @param indexNo the course's index
     * @param mode the mode of the account
     */
    public void updateRegisteredList(String courseCode, int indexNo, ModeType mode){
        try {
            ArrayList<CourseRegister> courseRegisters = CourseRegDB.retrieveCourseRegister();
            ArrayList<Student> studList = StudentDB.retrieveStudent();
            String studentEmail = null;
            //System.out.println("no of index vacancy: " + noOfIndexVacancy(courseCode, indexNo));
            
            for(int i=0; i<noOfIndexVacancy(courseCode, indexNo); i++){
            	
                for(CourseRegister courseRegister: courseRegisters){
                    if(courseRegister.getStatus()==false && courseRegister.getCourse().equals(courseCode) && courseRegister.getIndex()==indexNo){
                        courseRegister.setStatus(true);
                        
                        for (Student stud : studList) {
                			if(stud.getMatricNum().equals(courseRegister.getStudent())) {
                				studentEmail = stud.getEmail();
                			}
                		}
                        //System.out.println(courseRegister.getStudent() + " has been allocated to " + courseCode + ", " + indexNo);
                        NotificationCtrl.sendMail(studentEmail,courseCode,indexNo,1,null,0);
                        if(mode.equals(ModeType.ADMIN))
                        	System.out.println(courseRegister.getStudent() + " has been allocated to " + courseCode + ", " + indexNo);
                        break;
                    }
                }
            }
            CourseRegDB.saveCourse(courseRegisters);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the total number of vacancy for the index
     * @param courseCode the course code
     * @param indexNo the index number
     * @return the total number of vacancy left
     */
    public int noOfIndexVacancy(String courseCode, int indexNo) {
    	int totalSlot, vacancy=0;
    	
    	try {
            totalSlot = getIndexbyCode(courseCode, indexNo).getTotalSlot();
            vacancy = totalSlot;
            // deduct the people taking the index no and registered
            ArrayList<CourseRegister> courseReg = CourseRegDB.retrieveCourseRegister();
            for(CourseRegister course: courseReg) {
            	if (course.getIndex()==indexNo && course.getStatus()==true)
            		vacancy--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return vacancy;
    	
    }

    /**
     * Returns the total number of slot of an index
     * @param courseCode the course code
     * @param indexNo the index number
     * @return the total number of slot of the index
     */
    public int noOfIndexTotalSlot(String courseCode, int indexNo){

        try {
            return getIndexbyCode(courseCode, indexNo).getTotalSlot();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Returns the existence of a course
     * @param courseCode the course code
     * @return true if the course exists, else false
     */
    public boolean isExistingCourse(String courseCode){
        CourseCtrl courseCtrl = new CourseCtrl();
        Course course;
        try {
            course = courseCtrl.getCrsbyCode(courseCode);

            if (course.getCourseCode()=="Unknown") return false;
            else return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Returns the existence of a course group
     * @param courseCode the course's code
     * @param indexNo the index number of the course
     * @param group the group of the course
     * @return true if the group exists, else false
     */
    public boolean isExistingGroup(String courseCode, int indexNo, String group){
        CourseCtrl courseCtrl = new CourseCtrl();
        try{
            ArrayList<Index> indices = courseCtrl.getIndexList();
            for(Index index: indices){
                if (index.getGroup().equals(group) &&
                        index.getCourseCode().equals(courseCode) && index.getIndex()==indexNo) return true;
            }
            return false;
        }catch(IOException e){
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Returns the existence of a course index
     * @param courseCode the course's code
     * @param indexNo the index of the course
     * @return true if the index exists, else false
     */
    public boolean isExistingIndex(String courseCode, int indexNo){
        // checks if the index exists
        CourseCtrl courseCtrl = new CourseCtrl();
        Index index;
        try {
            ArrayList<Index> indices = IndexDB.retrieveIndex();
            index = courseCtrl.getIndexbyCode(courseCode, indexNo);

            if (index.getCourseCode()=="Unknown") return false;
            else return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * Returns an array list of course
     * @return an array list of course
     * @throws IOException if IO exception occurs
     * @throws ParseException if parse exception occurs
     */
	public ArrayList<Course> getCourseList()throws IOException, ParseException {
		ArrayList<Course> courseList = CourseDB.retrieveCourse();
		return courseList;
	}

    /**
     * Returns an array list of lesson
     * @return an array list of lesson
     */
	public ArrayList<Lesson> getLessonList(){
        try {
            ArrayList<Lesson> lessons = LessonDB.retrieveLesson();
            return lessons;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Set an array list of course to the database
     * @param lessonList an array list of course
     * @throws IOException if IO exception occurs
     * @throws ParseException if parse exception occurs
     */
    public void setLessonList(ArrayList<Lesson> lessonList)throws IOException, ParseException {
        LessonDB.saveCourse(lessonList);
    }

    /**
     * Set an array list of course to the database
     * @param courseList an array list of course
     * @throws IOException if IO exception occurs
     * @throws ParseException if parse exception occurs
     */
	public void setCourseList(ArrayList<Course> courseList)throws IOException, ParseException {
		CourseDB.saveCourse(courseList);
	}

    /**
     * Get the course by course code
     * @param CourseCode the course code desired
     * @return course
     * @throws IOException if IO exception occurs
     * @throws ParseException if parse exception occurs
     */
	public Course getCrsbyCode(String CourseCode)throws IOException, ParseException {
		ArrayList<Course> courseList = CourseDB.retrieveCourse();
<<<<<<< HEAD
		Course myCourse = new Course("Unknown", "Unknown", 0, "Unknown","Unknown", 1);
		System.out.println(myCourse.getCourseCode());
=======
		Course myCourse = new Course("Unknown", "Unknown", 0, "Unknown","Unknown");
		//System.out.println(myCourse.getCourseCode());
>>>>>>> 751df938f4b707772b0ec16eedab8819446e5382
		for(Course c : courseList){
			if (c.getCourseCode().equals(CourseCode)){
				System.out.println("The course: "+ c.getCourseCode()+ "," + c.getCourseName());
				myCourse = c;
			}
		}

		return myCourse;
	}


    /**
     * Get the array list of indices
     * @return array list of indices
     * @throws IOException if IO exception occurs
     * @throws ParseException if parse exception occurs
     */
	public ArrayList<Index> getIndexList()throws IOException, ParseException {
		ArrayList<Index> indexList = IndexDB.retrieveIndex();
		return indexList;
	}

    /**
     * set an array list of indices to the database
     * @param indexList index list desired
     * @throws IOException if IO exception occurs
     * @throws ParseException if parse exception occurs
     */
	public void setIndexList(ArrayList<Index> indexList)throws IOException, ParseException {
		IndexDB.saveIndex(indexList);
	}

    /**
     * Get the course index based on course code
     * @param CourseCode the course code
     * @param indexNo the course index
     * @return Index object based on the course code and index number
     * @throws IOException if IO exception occurs
     * @throws ParseException if parse exception occurs
     */
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
