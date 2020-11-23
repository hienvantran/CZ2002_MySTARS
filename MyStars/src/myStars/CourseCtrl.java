package myStars;

import java.io.*;
import java.text.*;
import java.util.*;
import Entities.Course;
import Entities.CourseRegister;
import Entities.Index;
import DB.CourseDB;
import DB.CourseRegDB;
import DB.IndexDB;

public class CourseCtrl {

    public int noOfIndexVacancyLeft(String courseCode, int indexNo) {
    	int totalVacancy, vacancyLeft=0;
    	
    	try {
            totalVacancy = getIndexbyCode(courseCode, indexNo).getVacancy();
            vacancyLeft = totalVacancy;
            // deduct the people taking the index no and registered
            ArrayList<CourseRegister> courseReg = CourseRegDB.retrieveCourseRegister();
            for(CourseRegister course: courseReg) {
            	System.out.println(course.getIndex());
            	if (course.getIndex()==indexNo && course.getStatus()==true)
            		vacancyLeft--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return vacancyLeft;
    	
    }
    public int noOfIndexVacancy(String courseCode, int indexNo){

        try {
            return getIndexbyCode(courseCode, indexNo).getVacancy();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean isExistingCourse(String courseCode){
        // checks if the course exists
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

    public boolean isExistingGroup(String courseCode, int indexNo, String group){
        CourseCtrl courseCtrl = new CourseCtrl();
        try{
            ArrayList<Index> indices = courseCtrl.getIndexList();
            for(Index index: indices){
                if (index.getGroup().equals(group)) return true;
            }
            return false;
        }catch(IOException e){
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isExistingIndex(String courseCode, int indexNo){
        // checks if the index exists
        CourseCtrl courseCtrl = new CourseCtrl();
        Index index;
        try {
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
