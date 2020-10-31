package Course;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course{
	
	private String courseCode;
	private String courseName;
	private String school;
	private int courseAU;
	private String courseType;
	private ArrayList<Index> index;
	
	public Course() {}
	
	public Course(String courseCode, String courseName, String school, int courseAU, String courseType)
	{
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.school = school;
		this.courseAU = courseAU;
		this.courseType = courseType;
	}
	public String getCourseCode(){	return courseCode;}
	public void setCourseCode(String courseCode){	this.courseCode = courseCode;}

	public String getCourseName(){	return courseName;}
	public void setCourseName(String courseName){	this.courseName = courseName;}
	
	public String getSchool(){	return school;}
	public void setSchool(String school){	this.school = school;}
	
	public int getCourseAU(){	return courseAU;}
	public void setCourseAU(int courseAU){	this.courseAU = courseAU;}
	
	public String getCourseType(){	return courseType;}
	public void setCourseType(String courseType){	this.courseType = courseType;}
	
	public ArrayList<Index> getIndex() { return index;}
	public void setIndex(ArrayList<Index> index) { this.index = index;}

	
}
