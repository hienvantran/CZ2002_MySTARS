package courses;

enum ClassType{
	lecture,
	tutorial,
	lab
}

public class Lesson {
	private int indexNo; 
	private String lessonType;
	private String lessonDay;
	private String lessonVenue;
	private String lessonTime;
	

	
	public Lesson (int indexNo, String lessonType, String lessonDay, String lessonTime, String lessonVenue){
		this.indexNo = indexNo;
		this.lessonType = lessonType;
		this.lessonDay = lessonDay;
		this.lessonTime = lessonTime;
		this.lessonVenue = lessonVenue;
	}

	public int getindexNo() {
		return indexNo;
	}

	public void setindexNo(int indexNo) {
		this.indexNo = indexNo;
	}

	public String getLessonType() {
		return lessonType;
	}

	public void setLessonType(String lessonType) {
		this.lessonType = lessonType;
	}

	public String getLessonDay() {
		return lessonDay;
	}

	public void setLessonDay(String lessonDay) {
		this.lessonDay = lessonDay;
	}

	public String getLessonVenue() {
		return lessonVenue;
	}

	public void setLessonVenue(String lessonVenue) {
		this.lessonVenue = lessonVenue;
	}

	public String getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}
	
	
}
