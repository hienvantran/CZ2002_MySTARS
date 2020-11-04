package Entities;

import java.util.ArrayList;

public class Index {
	private String courseCode;
	private int indexNo;
	private String group;
	private int vacancy;
	private int waitList;
	private ArrayList<Lesson> lessonList;
	
	public Index(String courseCode, int indexNo, String group, int vacancy, int waitList) {
		this.courseCode = courseCode;
		this.indexNo = indexNo;
		this.group = group;
		this.vacancy = vacancy;
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
	//Vacancy
	public int getVacancy()
	{
		return vacancy;
	}
	public void setVacancy(int vacancy)
	{
		this.vacancy = vacancy;
	}
	public boolean decrementVacancy()
	{
		if (vacancy > 0)
		{
			System.out.println("Student successfully registered.");
			vacancy--;
			return true;
		} else
		{
			System.out.println("Lesson is full!");
			return false;
		}
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
