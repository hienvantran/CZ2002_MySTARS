package myStars;
import java.io.*;
import java.text.*;
import java.util.*;

public class StudCrsCtrlApp {

	public static void main(String[] args) throws FileNotFoundException, ParseException, 
	 IOException {
		// TODO Auto-generated method stub
		CourseCtrl mycourse = new CourseCtrl();
		StudentCtrl mystudent = new StudentCtrl();
		Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
		// studentID
		System.out.print("Enter student ID- ");
		String studentID= sc.nextLine();
		System.out.print("Your entered student ID- " + studentID +"\n");
		// courseCode
		System.out.print("Enter courseCode- ");
		String courseCode= sc.nextLine();
		System.out.print("Your entered courseCode- " + courseCode+"\n");
		// indexNo
		System.out.print("Enter index- ");
		int indexNo= sc.nextInt();
		System.out.print("Your entered index- " + indexNo+"\n");
		if (mystudent.checkCourseRegistrationExists(studentID, courseCode) == false)
			mystudent.registerCourse(studentID, courseCode, indexNo);
		else {
			mystudent.dropCourse(studentID, courseCode, indexNo);
		}
		
	}

}
