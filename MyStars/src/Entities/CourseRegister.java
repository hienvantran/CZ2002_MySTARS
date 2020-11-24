package Entities;
/**
 * Represents the courses registered by the student
 * A student can enrolled in many different courses
 */
public class CourseRegister {
	
	private Boolean status;
	private String course;
	private int index;
	private String student;
	
	/**
	 * Constructor for CourseRegister
	 * @param stdUserName
	 * @param course
	 * @param index
	 * @param status
	 */
	public CourseRegister(String stdUserName, String course, int index,  Boolean status)
	{
		this.status = status;
		this.student = stdUserName;
		this.course = course;
		this.index = index;
	}
	/**
	 * Get status
	 * @return status
	 */
	public Boolean getStatus() { return status;}
	/**
	 * Change status
	 * @param status
	 */
	public void setStatus(Boolean status) { this.status = status;}
	/**
	 * Get the course
	 * @return course
	 */
	public String getCourse() { return course;}
	/**
	 * Change the course
	 * @param course
	 */
	public void setCourse(String course) { this.course = course;}
	/**
	 * Get the index
	 * @return index
	 */
	public int getIndex() { return index;}
	/**
	 * Change the index
	 * @param index
	 */
	public void setIndex(int index) { this.index = index;}
	/**
	 * Get the student
	 * @return student
	 */
	public String getStudent() { return student;}
	/**
	 * Change stdUserName
	 * @param stdUserName
	 */
	public void setStudent(String stdUserName) { this.student = stdUserName;}
}
