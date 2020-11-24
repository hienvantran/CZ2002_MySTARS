package Entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a Course offered in the school
 * A school can have many different types of courses
 */
public class Course{
	
	
	private String courseCode;
	private String courseName;
	private String school;
	private int courseAU;
	private String courseType;
	private ArrayList<Index> index;
	
	/**
	 * default constructor for Course
	 */
	public Course() {}
	
	/**
	 * Constructor for Course
	 * @param courseCode
	 * @param courseName
	 * @param courseAU
	 * @param school
	 * @param courseType
	 */
	public Course(String courseCode, String courseName, int courseAU, String school,  String courseType)
	{
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.school = school;
		this.courseAU = courseAU;
		this.courseType = courseType;
	}
	/**
	 * Get the courseCode
	 * @return this courseCode	
	 */
	public String getCourseCode(){	return courseCode;}
	/**
	 * Add/Change the courseCode
	 * @param courseCode
	 */
	public void setCourseCode(String courseCode){	this.courseCode = courseCode;}
	/**
	 * Get the courseName
	 * @return courseName
	 */
	public String getCourseName(){	return courseName;}
	/**
	 * Add/Change the courseName
	 * @param courseName
	 */
	public void setCourseName(String courseName){	this.courseName = courseName;}
	/**
	 * Get the school
	 * @return school
	 */
	public String getSchool(){	return school;}
	/**
	 * Add/Change the school
	 * @param school
	 */
	public void setSchool(String school){	this.school = school;}
	/**
	 * Get the course's AU
	 * @return courseAU
	 */
	public int getCourseAU(){	return courseAU;}
	/**
	 * Add/Change the course's AU
	 * @param courseAU
	 */
	public void setCourseAU(int courseAU){	this.courseAU = courseAU;}
	/**
	 * Get the course type
	 * @return courseType
	 */
	public String getCourseType(){	return courseType;}
	/**
	 * Add/Change the course type
	 * @param courseType
	 */
	public void setCourseType(String courseType){	this.courseType = courseType;}
	/**
	 * Get the ArrayList<Index>
	 * @return index
	 */
	public ArrayList<Index> getIndex() { return index;}
	/**
	 * Add/Change the ArrayList<Index>
	 * @param index
	 */
	public void setIndex(ArrayList<Index> index) { this.index = index;}

	
}
