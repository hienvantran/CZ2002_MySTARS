package myStars;

import DB.CourseDB;
import Entities.Course;
import Entities.Index;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class AdminCrsCtrl {
    public static void addCourse(Course course){

        CourseCtrl courseCtrl = new CourseCtrl();

        try {
            courseCtrl.addCrsByCode(course);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static int checkIndexVacancy(String courseCode, int indexNo){
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

    public static boolean checkExistIndex(String courseCode, int indexNo){
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

    public static boolean checkExistCourse(String courseCode){
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
