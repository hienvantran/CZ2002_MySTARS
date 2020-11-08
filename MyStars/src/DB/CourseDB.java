package DB;

import java.io.*;
import java.util.*;
import java.text.ParseException;

import Entities.*;
import myStars.CalendarCtrl;
public class CourseDB {
	public static final String SEPARATOR = "|";
	public static ArrayList<Course> courseList = new ArrayList<Course>();
	private static final String CourseFileName = "D:\\Programming\\Java\\MySTARS\\MySTARS\\src\\data\\Course.txt";
	public ArrayList<Course> retrieveCourse() throws FileNotFoundException, ParseException{
    	// read String from text file
    	ArrayList<String> stringArray = (ArrayList) IOforDB.read(CourseFileName);

    	for (int i = 0 ; i < stringArray.size() ; i++) {
            String field = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			// pass in the string to the string tokenizer using delimiter "," 
			StringTokenizer tokenizer = new StringTokenizer(field, SEPARATOR);	
			//CourseCode/CourseName/AU/School/Type
			String  crsCode = tokenizer.nextToken().trim();	
			String  crsName = tokenizer.nextToken().trim();
			int AU = Integer. parseInt((tokenizer.nextToken().trim())); 
			String school = tokenizer.nextToken().trim(); 
			String type = tokenizer.nextToken().trim(); 
			
			// create Course object and add to course list 
			Course crs = new Course( crsCode, crsName, AU, school, type);
			// add to Course list
			courseList.add(crs);
		}
		return courseList;
    }
	// update new entry into File
	public static void saveCourse(ArrayList<Course> courseList) throws IOException {
			ArrayList <String> newcourseList = new ArrayList<String>() ;

			for (int i = 0; i < courseList.size(); i++) {
				Course crs = (Course) courseList.get(i);
				StringBuilder st = new StringBuilder();
				st.append(crs.getCourseCode().trim());
				st.append(SEPARATOR);
				st.append(crs.getCourseName().trim());
				st.append(SEPARATOR);
				st.append(Integer. toString(crs.getCourseAU()).trim());
				st.append(SEPARATOR);
				st.append(crs.getSchool().trim());
				st.append(SEPARATOR);
				st.append(crs.getCourseType().trim());
				newcourseList.add(st.toString());
			}
			IOforDB.write(CourseFileName, newcourseList);
		}
}
