package myStars;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Entities.*;
import DB.*;

public class AdminStudCtrl {
    public static void editStudentAccessTime(String matric, Calendar accessStart, Calendar accessEnd){
        System.out.println("You've added a new student");

    }
    public static void addStudent(Student student){
        // add student to db
        ArrayList<Student> students = new ArrayList<>();
        StudentDB studentDB = new StudentDB();
        //studentDB.save(student);
    }
    public static boolean checkStudentExists(String matric){
        // check database

        return true;
    }
    public static boolean checkEmailExists(String email){
        // check database

        return true;
    }
    public static boolean checkUsernameExists(String username){
        // check database

        return true;
    }
}
