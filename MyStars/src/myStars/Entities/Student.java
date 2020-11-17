package myStars.Entities;

import java.util.Calendar;
import java.util.Date;

public class Student extends User{
    private int matricNum;
    private int yearOfStudy;
    private Date accessStart;
    private Date accessEnd;
    private String nationality;

    public Student(String username, String password, ModeType modeType, int matricNum, int yearOfStudy, Date accessStart, Date accessEnd, String nationality){
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

    public Date getAccessStart() {
        return accessStart;
    }

    public void setAccessStart(Date accessStart) {
        this.accessStart = accessStart;
    }

    public Date getAccessEnd() {
        return accessEnd;
    }

    public void setAccessEnd(Date accessEnd) {
        this.accessEnd = accessEnd;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getMatricNum() {
        return matricNum;
    }

    public void setMatricNum(int matricNum) {
        this.matricNum = matricNum;
    }
}
