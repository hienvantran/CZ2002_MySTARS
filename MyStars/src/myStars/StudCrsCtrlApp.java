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
		
		// select choice
		System.out.print("The app will perform: \n"
				+ "1: register course \n"
				+ "2: drop course \n"
				+ "3: print reg course \n"
				+ "4: check vacancy for course \n"
				+ "5: chang index \n"
				+ "6: swap index\n"
				+ "7: exit\n");
		

			// studentID
			System.out.print("Enter student ID- ");
			String studentID= sc.next();
			System.out.print("Your entered student ID- " + studentID +"\n");
			System.out.print("which is your selection? ");
			int sel= sc.nextInt();
			switch(sel) {
		case 1:
			// courseCode
			System.out.print("Enter courseCode- ");
			String courseCode= sc.next();
			System.out.print("Your entered courseCode- " + courseCode+"\n");
			// indexNo
			System.out.print("Enter index- ");
			int indexNo= sc.nextInt();
			System.out.print("Your entered index- " + indexNo+"\n");
			mystudent.registerCourse(studentID, courseCode, indexNo);
			break;
		case 2:
			System.out.print("Enter courseCode- ");
			String alrcourseCode= sc.next();
			System.out.print("Your entered courseCode- " + alrcourseCode+"\n");
			// indexNo
			System.out.print("Enter index- ");
			int alrindexNo= sc.nextInt();
			System.out.print("Your entered index- " + alrindexNo+"\n");
			mystudent.dropCourse(studentID, alrcourseCode, alrindexNo);
			break;
		case 3:
			mystudent.printRegCourse(studentID);
			break;
		case 4:
			System.out.print("Enter courseCode to check ");
			String crsCode= sc.next();
			System.out.print("Your entered courseCode- " + crsCode+"\n");
			mystudent.checkVacancy(crsCode);
			break;
		case 5:
			System.out.print("Enter courseCode- ");
			String curcourseCode= sc.next();
			System.out.print("Your entered courseCode- " + curcourseCode+"\n");
			// indexNo
			System.out.print("Enter index- ");
			int curindexNo= sc.nextInt();
			System.out.print("Your entered index- " + curindexNo+"\n");
			System.out.print("Enter your new index- ");
			int newindexNo= sc.nextInt();
			System.out.print("Your entered index- " + newindexNo+"\n");
			mystudent.changeIndex(studentID, curcourseCode, curindexNo, newindexNo);
			break;
		case 6:
			System.out.print("Enter courseCode- ");
			String curCrsCode= sc.next();
			System.out.print("Your entered courseCode- " + curCrsCode+"\n");
			// indexNo
			System.out.print("Enter index- ");
			int curIdxNo= sc.nextInt();
			System.out.print("Your entered index- " + curIdxNo+"\n");
			System.out.print("Enter peerStudId-");
			String peerStudId= sc.next();
			System.out.print("Your entered peerStudId " + peerStudId+ "\n");
			System.out.print("Enter peer index- ");
			int peerIdx= sc.nextInt();
			System.out.print("Your entered peer index- " + peerIdx+"\n");
			mystudent.swapIdx(studentID, peerStudId, curCrsCode, curIdxNo, peerIdx);
			break;
		case 7:
			break;
		default:
			System.out.print("Invalid!");
			
		
			
		}
		// stud2|CZ3001|10005|Registered
		// stud3|CZ3001|10006|Registered
	}

}
