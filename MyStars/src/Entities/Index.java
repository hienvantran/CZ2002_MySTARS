package Entities;

import java.util.ArrayList;
/**
 * Index serves as the id number for students to registered a course
 *
 */
public class Index {
	/**
	 * name of the course code
	 */
	private String courseCode;
	/**
	 * id number for the index
	 */
	private int indexNo;
	/**
	 * group number/id for the index
	 */
	private String group;
	/**
	 * total size a class can hold for that index
	 */
	private int totalSlot;
	/**
	 * number of students on the waitlist
	 */
	private int waitList;
	/**
	 * ArrayList for the lesson
	 */
	private ArrayList<Lesson> lessonList;
	
	/**
	 * Create index with these parameters
	 * @param courseCode
	 * @param indexNo
	 * @param group
	 * @param totalSlot
	 * @param waitList
	 */
	public Index(String courseCode, int indexNo, String group, int totalSlot, int waitList) {
		this.courseCode = courseCode;
		this.indexNo = indexNo;
		this.group = group;
		this.totalSlot = totalSlot;
		this.waitList = waitList;
		lessonList = new ArrayList<Lesson>();
	}
	
	/**
	 * Get the coursecode
	 * @return coursecode
	 */
	public String getCourseCode()
	{
		return courseCode;
	}
	/**
	 * Add/Change the course code
	 * @param courseCode
	 */
	public void setCourseCode(String courseCode)
	{
		this.courseCode = courseCode;
	}
	/**
	 * Get the index number
	 * @return indexNo
	 */
	public int getIndex()
	{
		return indexNo;
	}
	/**
	 * Add/Change the index number
	 * @param indexNo
	 */
	public void setIndexNumber(int indexNo)
	{
		this.indexNo = indexNo;
	}
	/**
	 * Get the group of the index number
	 * @return group
	 */
	public String getGroup()
	{
		return group;
	}
	/**
	 * Add/Change the group of the index number
	 * @param group
	 */
	public void setGroup(String group)
	{
		this.group = group;
	}
	/**
	 * Get the total number of slot for the index number
	 * @return totalSlot
	 */
	public int getTotalSlot()
	{
		return totalSlot;
	}
	/**
	 * Add/Change the total number of slot for the index number
	 * @param totalSlot
	 */
	public void setTotalSlot(int totalSlot)
	{
		this.totalSlot = totalSlot;
	}
	/**
	 * Get the number of students on the waitinglist
	 * @return
	 */
	public int getWaitList()
	{
		return waitList;
	}
	/**
	 * Change the waitinglist
	 * @param waitList
	 */
	public void setWaitList(int waitList)
	{
		this.waitList = waitList;
	}
	/**
	 * Increment the waitinglist by 1
	 */
	public void addWaitlist() {
		this.waitList ++;
	}
	/**
	 * decrement the waitinglist by 1
	 */
	public void minusWaitlist() {
		this.waitList --;
	}

}
