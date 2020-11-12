package myStars;

import java.util.Calendar;
import java.util.Date;

import Entities.*;
import DB.*;

public class AdminStudCtrl {
    public static void editStudentAccessTime(String matric, Calendar accessStart, Calendar accessEnd){

    }
    public static void addStudent(Student student){
        // add student to db
        StudentDB studentDB = new StudentDB();
        //studentDB.save(student);
    }
    public static boolean checkStudentExists(String matric){
        // check database
        return true;
    }
}
