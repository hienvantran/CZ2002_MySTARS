package Entities;

public class AcademicStaff extends User{
    private double salary=1000;

    public AcademicStaff(String username, String password, ModeType modeType){
    	super(username,password,modeType);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}