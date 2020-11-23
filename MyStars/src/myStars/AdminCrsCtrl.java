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
    public static void addCourse(Course course){
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

    public static void updateCourseCode(String courseCode, String newCourseCode){
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

    public static void updateCourseName(String courseCode, String newCourseName){
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

    public static void updateCourseSchool(String courseCode, String newCourseSchool){
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

    public static void updateCourseAU(String courseCode, int newAU){
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

    public static void updateCourseType(String courseCode, String newType){
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

    public static void updateCourseIndex(String courseCode, int indexNum, int newIndexNum){
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

    public static void createIndex(String courseCode, int indexNum, String group, int vacancy){
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

    public static void updateIndexVacancy(String courseCode, int indexNum, int vacancy){
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
    public static int noOfIndexVacancy(String courseCode, int indexNo){
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

    public static boolean isExistingIndex(String courseCode, int indexNo){
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

    public static boolean isExistingGroup(String courseCode, int indexNo, String group){
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

    public static boolean isExistingCourse(String courseCode){
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

}
