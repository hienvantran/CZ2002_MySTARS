package DB;

import java.io.*;
import java.util.*;
import java.text.ParseException;

import Entities.*;
import myStars.CalendarCtrl;
public class LessonDB {
	public static final String SEPARATOR = "|";
	
	private static final String LessonFileName = "src//data//Lesson.txt";
	public static ArrayList<Lesson> retrieveLesson() throws FileNotFoundException, ParseException{
    	// read String from text file
    	ArrayList<String> stringArray = (ArrayList) IOforDB.read(LessonFileName);
    	ArrayList<Lesson> lessonList = new ArrayList<Lesson>();
    	for (int i = 0 ; i < stringArray.size() ; i++) {
            String field = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			// pass in the string to the string tokenizer using delimiter "," 
			StringTokenizer tokenizer = new StringTokenizer(field, SEPARATOR);	
			//CourseCode/idx/type/day/time/venue
			String  crsCode = tokenizer.nextToken().trim();	
			int idx = Integer. parseInt((tokenizer.nextToken().trim())); 
			String type = tokenizer.nextToken().trim(); 
			String day = tokenizer.nextToken().trim(); 
			String time = tokenizer.nextToken().trim(); 
			String  venue = tokenizer.nextToken().trim();
			// create Course object and add to course list 
			Lesson crs = new Lesson( crsCode, idx, type, day, time, venue);
			// add to Course list
			lessonList .add(crs);
		}
		return lessonList ;
    }
	// update new entry into File
	public static void saveCourse(ArrayList<Lesson> lessonList ) throws IOException {
			ArrayList <String> newlessonList  = new ArrayList<String>() ;
			for (int i = 0; i < lessonList .size(); i++) {
				Lesson lesson = (Lesson) lessonList .get(i);
				StringBuilder st = new StringBuilder();
				st.append(lesson.getCrsCode().trim());
				st.append(SEPARATOR);
				st.append(Integer. toString(lesson.getindexNo()).trim());
				st.append(SEPARATOR);
				st.append(lesson.getLessonType().trim());
				st.append(SEPARATOR);
				st.append(lesson.getLessonDay().trim());
				st.append(SEPARATOR);
				st.append(lesson.getLessonTime().trim());
				st.append(SEPARATOR);
				st.append(lesson.getLessonVenue().trim());
				newlessonList .add(st.toString());
			}
			IOforDB.write(LessonFileName, newlessonList );
		}
}
