package Course;

public class CourseRegister {
	
	private Boolean status;
	private Course course;
	private Index index;
	private Student student;
	
	public CourseRegister(Student student, Index index, Course course, Boolean status)
	{
		this.status = status;
		this.student = student;
		this.course = course;
		this.index = index;
	}
	
	public Boolean getStatus() { return status;}
	public void setStatus(Boolean status) { this.status = status;}
	
	public Course getCourse() { return course;}
	public void setCourse(Course course) { this.course = course;}
	
	public Index getIndex() { return index;}
	public void setIndex(Index index) { this.index = index;}
	
	public Student getStudent() { return student;}
	public void setStudent(Student student) { this.student = student;}
}
