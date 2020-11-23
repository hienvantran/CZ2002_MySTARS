package myStars;

import DB.CourseDB;
import Entities.Course;
import Entities.Index;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Admin course controller!
 * The Admin course controller extends course controller and
 * The Admin course controller is a controller helps to interface
 * UI with the course.
 * <p>
 * <b>Note:</b> Giving proper comments in your program makes it more
 * user friendly and it is assumed as a high quality code.
 * </p>
 * @author Ong Eng Hao
 * @version 1.0
 * @since 2020-11-21
*/
public class AdminCrsCtrl extends CourseCtrl {
    public void addCourse(Course course){
        CourseCtrl courseCtrl = new CourseCtrl();
        try {
            ArrayList<Course> courseList = courseCtrl.getCourseList();
            courseList.add(course);
            courseCtrl.setCourseList(courseList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
/*
    public void removeCrsByCode(Course course)throws IOException, ParseException {
        ArrayList<Course> courseList = CourseDB.retrieveCourse();
        courseList.remove(course);
        CourseDB.saveCourse(courseList);
    }*/

    public void updateCourseCode(String courseCode, String newCourseCode){
        CourseCtrl courseCtrl = new CourseCtrl();
        try {
            ArrayList<Course> courses = courseCtrl.getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setCourseCode(newCourseCode);
            }
            ArrayList<Index> indices = courseCtrl.getIndexList();
            for(Index index: indices){
                if(index.getCourseCode().equals(courseCode))
                    index.setCourseCode(newCourseCode);
            }
            courseCtrl.setCourseList(courses);
            courseCtrl.setIndexList(indices);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void updateCourseName(String courseCode, String newCourseName){
        CourseCtrl courseCtrl = new CourseCtrl();
        try {
            ArrayList<Course> courses = courseCtrl.getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setCourseName(newCourseName);
            }
            courseCtrl.setCourseList(courses);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void updateCourseSchool(String courseCode, String newCourseSchool){
        CourseCtrl courseCtrl = new CourseCtrl();
        try {
            ArrayList<Course> courses = courseCtrl.getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setSchool(newCourseSchool);
            }
            courseCtrl.setCourseList(courses);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void updateCourseAU(String courseCode, int newAU){
        CourseCtrl courseCtrl = new CourseCtrl();
        try {
            ArrayList<Course> courses = courseCtrl.getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setCourseAU(newAU);
            }
            courseCtrl.setCourseList(courses);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void updateCourseType(String courseCode, String newType){
        CourseCtrl courseCtrl = new CourseCtrl();
        try {
            ArrayList<Course> courses = courseCtrl.getCourseList();
            for(Course course:courses){
                if (course.getCourseCode().equals(courseCode))
                    course.setCourseType(newType);
            }
            courseCtrl.setCourseList(courses);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void updateCourseIndex(String courseCode, int indexNum, int newIndexNum){
        CourseCtrl courseCtrl = new CourseCtrl();
        try {
            ArrayList<Index> indices = courseCtrl.getIndexList();

            for(Index index: indices){
                if(index.getCourseCode().equals(courseCode) && index.getIndex()==indexNum)
                    index.setIndexNumber(newIndexNum);
            }
            courseCtrl.setIndexList(indices);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void createIndex(String courseCode, int indexNum, String group, int vacancy){
        CourseCtrl courseCtrl = new CourseCtrl();
        try{
            ArrayList<Index> indices = courseCtrl.getIndexList();
            Index newIndex = new Index(courseCode, indexNum, "TEST", vacancy, 0);
        }catch(IOException e){
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void updateIndexVacancy(String courseCode, int indexNum, int vacancy){
        CourseCtrl courseCtrl = new CourseCtrl();
        try{
            ArrayList<Index> indices = courseCtrl.getIndexList();
            for(Index index: indices){
                if ((index.getCourseCode().equals(courseCode)) && index.getIndex()==indexNum){
                    index.setVacancy(vacancy);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     * @return void
     * int This returns sum of numA and numB.
     * @param courseCode course's code.
     * @param indexNo index number of the course
     *
     */
    public int noOfIndexVacancy(String courseCode, int indexNo){
        CourseCtrl courseCtrl = new CourseCtrl();

        try {
            return courseCtrl.getIndexbyCode(courseCode, indexNo).getVacancy();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
