package myStars;

import Entities.Course;
import Entities.Index;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class AdminCrsCtrl extends CourseCtrl {
    public static void addCourse(Course course){

        // I think this one we can remove it from the courseCtrl because courseCtrol seems to be for everone and don't have to add course in there

        CourseCtrl courseCtrl = new CourseCtrl();

        try {
            courseCtrl.addCrs(course);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

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
