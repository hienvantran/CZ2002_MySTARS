package DB;

import java.io.*;
import java.util.*;
import java.text.ParseException;

import Entities.ModeType;
import Entities.Student;
import myStars.CalendarCtrl;
public class StudentDB {
	public static final String SEPARATOR = "|";
	public static ArrayList<Student> studentList = new ArrayList<Student>();
	private static final String StudentFileName = "data\\Student.txt";
	public static ArrayList<Student> retrieveStudent() throws FileNotFoundException, ParseException{
    	// read String from text file
    	ArrayList<String> stringArray = (ArrayList) IOforDB.read(StudentFileName);

    	for (int i = 0 ; i < stringArray.size() ; i++) {
            String field = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			// pass in the string to the string tokenizer using delimiter "," 
			StringTokenizer tokenizer = new StringTokenizer(field, SEPARATOR);	
			//username/password/matricNo/year/nationality/email/accessStart/accessEnd
			String  username = tokenizer.nextToken();	
			String  pass = tokenizer.nextToken().trim();	
			String matricNum = tokenizer.nextToken().trim(); 
			String nationality = tokenizer.nextToken().trim(); 
			int studyY = Integer. parseInt((tokenizer.nextToken().trim())); 
			String email = tokenizer.nextToken().trim(); 
			Calendar accessStart = CalendarCtrl.stringToCalendar(tokenizer.nextToken().trim()); 
			Calendar accessEnd = CalendarCtrl.stringToCalendar(tokenizer.nextToken().trim()); 
			// create Student object and add to student list 
			Student std = new Student( username, pass, ModeType.USER, matricNum, nationality, studyY, email, accessStart, accessEnd);
			
			// add to Students list
			studentList.add(std);
		}
		return studentList;
    }
	// update new entry into File
	public static void saveStudent(ArrayList<Student> studentList) throws IOException {
			ArrayList <String> newstudentList = new ArrayList<String>() ;

			for (int i = 0; i < studentList.size(); i++) {
				Student stud = (Student) studentList.get(i);
				StringBuilder st = new StringBuilder();
				st.append(stud.getUsername().trim());
				st.append(SEPARATOR);
				st.append(stud.getPassword().trim());
				st.append(SEPARATOR);
				st.append(stud.getMode().toString().trim());
				st.append(SEPARATOR);
				st.append(stud.getMatricNum().trim());
				st.append(SEPARATOR);
				st.append(stud.getNationality().trim());
				st.append(SEPARATOR);
				st.append(Integer. toString(stud.getYearOfStudy()).trim());
				st.append(SEPARATOR);
				st.append(stud.getEmail().trim());
				st.append(SEPARATOR);
				st.append(CalendarCtrl.calendarToString(stud.getAccessStart()));
				st.append(SEPARATOR);
				st.append(CalendarCtrl.calendarToString(stud.getAccessEnd()));
				newstudentList.add(st.toString());
			}
			IOforDB.write(StudentFileName, newstudentList);
		}
}
