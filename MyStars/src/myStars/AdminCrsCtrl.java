package myStars;

import Entities.Course;
import Entities.Index;
import Entities.ModeType;

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
    CourseCtrl courseCtrl = new CourseCtrl();
    
    public void addCourse(Course course){
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

    public void createIndex(String courseCode, int indexNum, String group, int totalSlot){
        try{
            ArrayList<Index> indices = courseCtrl.getIndexList();
            Index newIndex = new Index(courseCode, indexNum, "TEST", totalSlot, 0);
        }catch(IOException e){
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void updateIndexTotalSlot(String courseCode, int indexNum, int totalSlot){
        try{
            ArrayList<Index> indices = courseCtrl.getIndexList();
            for(Index index: indices){
                if ((index.getCourseCode().equals(courseCode)) && index.getIndex()==indexNum){
                    index.setTotalSlot(totalSlot);
                }
            }
            courseCtrl.setIndexList(indices);
            updateRegisteredList(courseCode, indexNum, ModeType.ADMIN);
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

}
