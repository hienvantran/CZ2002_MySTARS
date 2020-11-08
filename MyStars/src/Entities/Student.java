package Entities;

import java.util.Calendar;
import java.util.Date;

public class Student extends User{
    private String matricNum;
    private int yearOfStudy;
    private Calendar accessStart;
    private Calendar accessEnd;
    private String nationality;

    public Student(String username, String password, ModeType modeType, String matricNum, String nationality, int yearOfStudy, Calendar accessStart, Calendar accessEnd){
        super(username, password, modeType);
        this.matricNum = matricNum;
        this.yearOfStudy = yearOfStudy;
        this.accessStart = accessStart;
        this.accessEnd = accessEnd;
        this.nationality = nationality;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public Calendar getAccessStart() {
        return accessStart;
    }

    public void setAccessStart(Calendar accessStart) {
        this.accessStart = accessStart;
    }

    public Calendar getAccessEnd() {
        return accessEnd;
    }

    public void setAccessEnd(Calendar accessEnd) {
        this.accessEnd = accessEnd;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMatricNum() {
        return matricNum;
    }

    public void setMatricNum(String matricNum) {
        this.matricNum = matricNum;
    }
}
