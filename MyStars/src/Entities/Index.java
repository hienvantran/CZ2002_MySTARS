package Entities;

import java.util.ArrayList;

public class Index {
	private String courseCode;
	private int indexNo;
	private String group;
	private int totalSlot;
	private int waitList;
	private ArrayList<Lesson> lessonList;
	
	public Index(String courseCode, int indexNo, String group, int totalSlot, int waitList) {
		this.courseCode = courseCode;
		this.indexNo = indexNo;
		this.group = group;
		this.totalSlot = totalSlot;
		this.waitList = waitList;
		lessonList = new ArrayList<Lesson>();
	}
	
	//Course Code
	public String getCourseCode()
	{
		return courseCode;
	}
	public void setCourseCode(String courseCode)
	{
		this.courseCode = courseCode;
	}
	//Index Number
	public int getIndex()
	{
		return indexNo;
	}
	public void setIndexNumber(int indexNo)
	{
		this.indexNo = indexNo;
	}
	// Tutorial Group
	public String getGroup()
	{
		return group;
	}
	public void setGroup(String group)
	{
		this.group = group;
	}
	// TotalSlot
	public int getTotalSlot()
	{
		return totalSlot;
	}
	public void setTotalSlot(int totalSlot)
	{
		this.totalSlot = totalSlot;
	}
	
	//Waiting List
	public int getWaitList()
	{
		return waitList;
	}
	public void setWaitList(int waitList)
	{
		this.waitList = waitList;
	}
	public void addWaitlist() {
		this.waitList ++;
	}
	public void minusWaitlist() {
		this.waitList --;
	}

}
