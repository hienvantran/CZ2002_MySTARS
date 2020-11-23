package myStars;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Entities.*;
import DB.*;

public class AdminStudCtrl {
    /**
     * Edit the student's access time
     * @param matric the student's matriculation number
     * @param accessStart the desired access start time for the student
     * @param accessEnd the desired access end time for the student
     */
    public static void editStudentAccessTime(String matric, Calendar accessStart, Calendar accessEnd){
        try{
            ArrayList<Student> students = StudentDB.retrieveStudent();
            for(Student student: students){
                if(student.getMatricNum().equals(matric)){
                    student.setAccessStart(accessStart);
                    student.setAccessEnd(accessEnd);
                }
            }
            StudentDB.saveStudent(students);
        }catch (IOException|ParseException e){
            e.printStackTrace();
        }
    }

    /**
     * Adds a student to the database using Student class
     * @param student the student object desired to add to the database
     */
    public static void addStudent(Student student){
        // add student to db
        try {
            ArrayList<Student> students = StudentDB.retrieveStudent();
            students.add(student);
            StudentDB.saveStudent(students);
            // TODO prints out all the students


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether the student exists using matriculation number
     * @param matric String
     * @return true when student exists in the database, else false
     */
    public static boolean isStudentMatricExists(String matric){
        try {
            ArrayList<Student> studentList = StudentDB.retrieveStudent();
            for(Student student: studentList){
                if (student.getMatricNum().equals(matric)) return true;
            }
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks whether a student exists using username
     * @param username The username of a student
     * @return true when username exists in the database, else false
     */
    public static boolean isStudentUserExists(String username){
        try{
            ArrayList<Student> studentList = StudentDB.retrieveStudent();
            ArrayList<User> adminList = AdminDB.retrieveAdmin();
            for(Student student: studentList){
                if(student.getUsername().equals(username)) return true;
            }
            for(User admin: adminList){
                if(admin.getUsername().equals(username)) return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks whether a student email exists
     * @param email
     * @return true when email exists in the database, else false
     *
     */
    public static boolean isStudentEmailExists(String email){
        try{
            ArrayList<Student> studentList = StudentDB.retrieveStudent();
            ArrayList<User> adminList = AdminDB.retrieveAdmin();
            for(Student student: studentList){
                if(student.getUsername().equals(email)) return true;
            }
            for(User admin: adminList){
                if(admin.getUsername().equals(email)) return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
