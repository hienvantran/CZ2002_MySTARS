package Entities;

public class CourseRegister {
	
	private Boolean status;
	private String course;
	private int index;
	private String student;
	
	public CourseRegister(String student, String course, int index,  Boolean status)
	{
		this.status = status;
		this.student = student;
		this.course = course;
		this.index = index;
	}
	
	public Boolean getStatus() { return status;}
	public void setStatus(Boolean status) { this.status = status;}
	
	public String getCourse() { return course;}
	public void setCourse(String course) { this.course = course;}
	
	public int getIndex() { return index;}
	public void setIndex(int index) { this.index = index;}
	
	public String getStudent() { return student;}
	public void setStudent(String student) { this.student = student;}
}
