package Entities;

import java.util.Calendar;
import java.util.Date;

/**
 * Student class that extends user!
 * @author Jia Kai
 * @version 1.0
 * @since 2020-11-21
 */
public class Student extends User{
	/**
	 * the matric number of student
	 */
    private String matricNum;
	/**
	 * the nationality of student
	 */
    private String nationality;
	/**
	 * the year of study of student
	 */
    private int yearOfStudy;
	/**
	 * the email of student
	 */
    private String email;
	/**
	 * the access start date time of student
	 */
    private Calendar accessStart;
    /**
	 * the access end date time of student
	 */
    private Calendar accessEnd;

    /**
     * Creates a new student with given parameters
     * @param username
     * @param password
     * @param modeType
     * @param matricNum
     * @param nationality
     * @param yearOfStudy
     * @param email
     * @param accessStart
     * @param accessEnd
     */
    public Student(String username, String password, ModeType modeType, String matricNum,String nationality,int yearOfStudy,String email, Calendar accessStart, Calendar accessEnd){
        super(username, password, modeType);
        this.matricNum = matricNum;
        this.nationality = nationality;
        this.yearOfStudy = yearOfStudy;
        this.email = email;
        this.accessStart = accessStart;
        this.accessEnd = accessEnd;

    }

    /**
     * Gets the year of study of the student
     * @return this student's year of study
     */
    public int getYearOfStudy() {
        return yearOfStudy;
    }

    /**
     * changes the year of study of the student
     * @param yearOfStudy
     */
    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    /**
     * Gets the access start date time of the student
     * @return this student's access start date time
     */
    public Calendar getAccessStart() {
        return accessStart;
    }

    /**
     * changes the access start date time of the student
     * @param accessStart
     */
    public void setAccessStart(Calendar accessStart) {
        this.accessStart = accessStart;
    }

    /**
     * Gets the access end date time of the student
     * @return this student's access end date time
     */
    public Calendar getAccessEnd() {
        return accessEnd;
    }

    /**
     * changes the access end date time of the student
     * @param accessEnd
     */
    public void setAccessEnd(Calendar accessEnd) {
        this.accessEnd = accessEnd;
    }

    /**
     * Gets the nationality of the student
     * @return this student's nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * changes the nationality of the student
     * @param nationality
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * Gets the matric number of the student
     * @return this student's matric number
     */
    public String getMatricNum() {
        return matricNum;
    }

    /**
     * changes the matric number of the student
     * @param matricNum
     */
    public void setMatricNum(String matricNum) {
        this.matricNum = matricNum;
    }
    
    /**
     * Gets the email of the student
     * @return this student's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * changes the email of the student
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
