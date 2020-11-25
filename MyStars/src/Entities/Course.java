package Entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a Course offered in the school
 * A school can have many different types of courses
 */
public class Course{
	/**
	 * name/id of the course
	 */
	private String courseCode;
	/**
	 * name of the course
	 */
	private String courseName;
	/**
	 * name of the school
	 */
	private String school;
	/**
	 * number of AU for the course
	 */
	private int courseAU;
	/**
	 * type of the course
	 */
	private String courseType;

	/**
	 * lesson type
	 */
	private int lessonType;
	/**
	 * the ArrayList for the index
	 */
	private ArrayList<Index> index;
	
	/**
	 * default constructor for Course
	 */
	public Course() {}
	
	/**
	 * Create a new course with these parameters
	 * @param courseCode
	 * @param courseName
	 * @param courseAU
	 * @param school
	 * @param courseType
	 * @param lessonType
	 */
	public Course(String courseCode, String courseName, int courseAU, String school, String courseType, int lessonType)
	{
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.school = school;
		this.courseAU = courseAU;
		this.courseType = courseType;
		this.lessonType = lessonType;
	}

	/**
	 * Get the lesson type of the course
	 * @return
	 */
	public int getLessonType(){
		return lessonType;
	}

	/**
	 * Set the lesson type of the course
	 */
	public void setLessonType(int lessonType){
		this.lessonType = lessonType;
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
