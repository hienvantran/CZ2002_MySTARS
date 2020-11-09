package Entities;

import java.util.Calendar;
import java.util.Date;

public class Student extends User{
    private String matricNum;
    private String nationality;
    private int yearOfStudy;
    private String email;
    private Calendar accessStart;
    private Calendar accessEnd;

    public Student(String username, String password, ModeType modeType, String matricNum,String nationality,int yearOfStudy,String email, Calendar accessStart, Calendar accessEnd){
        super(username, password, modeType);
        this.matricNum = matricNum;
        this.nationality = nationality;
        this.yearOfStudy = yearOfStudy;
        this.email = email;
        this.accessStart = accessStart;
        this.accessEnd = accessEnd;

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
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
