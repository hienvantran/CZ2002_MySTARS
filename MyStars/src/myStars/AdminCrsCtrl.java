package myStars;

import DB.CourseDB;
import Entities.Course;

import java.io.IOException;
import java.util.ArrayList;

public class AdminCrsCtrl {
    public static void addCourse(Course course){
        // add course to file
        ArrayList<Course> courses = new ArrayList<Course>();
        courses.add(course);

        try {
            CourseDB.saveCourse(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
