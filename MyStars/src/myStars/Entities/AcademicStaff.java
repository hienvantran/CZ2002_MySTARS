package myStars.Entities;

public class AcademicStaff extends User{
    private double salary;

    public AcademicStaff(){

    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
