package DB;

import java.io.*;
import java.util.*;
import java.text.ParseException;

import Entities.ModeType;
import Entities.CourseRegister;


public class CourseRegDB {
	public static final String SEPARATOR = "|";
	public static ArrayList<CourseRegister> courseList = new ArrayList<CourseRegister>();
	private static final String CourseRegisterFileName = "D:\\Programming\\Java\\MySTARS\\MySTARS\\src\\data\\StudentRegCrs.txt";
	private static boolean status= false;
	public static ArrayList<CourseRegister> retrieveCourseRegister() throws FileNotFoundException, ParseException{
    	// read String from text file
    	ArrayList<String> stringArray = (ArrayList) IOforDB.read(CourseRegisterFileName);

    	for (int i = 0 ; i < stringArray.size() ; i++) {
            String field = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			// pass in the string to the string tokenizer using delimiter "," 
			StringTokenizer tokenizer = new StringTokenizer(field, SEPARATOR);	
			//Student/CourseCode/index/status
			String  stud = tokenizer.nextToken().trim();	
			String  crs = tokenizer.nextToken().trim();
			int idx = Integer.parseInt(tokenizer.nextToken().trim());
			String stt = tokenizer.nextToken().trim(); 
			if (stt.equals("Registered")) {status = true;}
			else if (stt.equals("On Waiting List")) {status = false;}
			// create Course object and add to course list 
			CourseRegister regCrs = new CourseRegister( stud, crs, idx, status);
			// add to Course list
			courseList.add(regCrs);
		}
		return courseList;
    }
	// update new entry into File
	public static void saveCourse(ArrayList<CourseRegister> courseList) throws IOException {
			ArrayList <String> newcourseList = new ArrayList<String>() ;
			
			for (int i = 0; i < courseList.size(); i++) {
				CourseRegister crs = (CourseRegister) courseList.get(i);
				String crsStt = "Unregistered";
				if (crs.getStatus().equals(true)) {crsStt = "Registered";}
				else if (crs.getStatus().equals(false)) {crsStt = "On Waiting List";}
				StringBuilder st = new StringBuilder();
				st.append(crs.getStudent().trim());
				st.append(SEPARATOR);
				st.append(crs.getCourse().trim());
				st.append(SEPARATOR);
				st.append(Integer. toString(crs.getIndex()).trim());
				st.append(SEPARATOR);
				st.append(crsStt.trim());
				newcourseList.add(st.toString());
			}
			IOforDB.write(CourseRegisterFileName, newcourseList);
		}
}
