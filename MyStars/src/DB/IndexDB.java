package DB;

import java.io.*;
import java.util.*;
import java.text.ParseException;

import Entities.*;
import myStars.CalendarCtrl;
public class IndexDB {
	public static final String SEPARATOR = "|";
	public static ArrayList<Index> indexList = new ArrayList<Index>();
	private static final String IndexFileName = "D:\\Programming\\Java\\MySTARS\\MySTARS\\src\\data\\Index.txt";
	public static ArrayList<Index> retrieveIndex() throws FileNotFoundException, ParseException{
    	// read String from text file
    	ArrayList<String> stringArray = (ArrayList) IOforDB.read(IndexFileName);
    	
    	for (int i = 0 ; i < stringArray.size() ; i++) {
            String field = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			// pass in the string to the string tokenizer using delimiter "," 
			StringTokenizer tokenizer = new StringTokenizer(field, SEPARATOR);	
			//CourseCode/index/Group/vacancy/waitList
			String  crsCode = tokenizer.nextToken().trim();	
			int index = Integer. parseInt((tokenizer.nextToken().trim())); 
			String  group = tokenizer.nextToken().trim();
			int vacancy = Integer. parseInt((tokenizer.nextToken().trim()));
			int waitlist = Integer. parseInt((tokenizer.nextToken().trim()));
			
			
			
			// create Course object and add to course list 
			Index idx = new Index( crsCode, index, group, vacancy, waitlist);
			// add to Course list
			indexList.add(idx);
		}
		return indexList;
    }
	// update new entry into File
	public static void saveIndex(ArrayList<Index> indexList) throws IOException {
			ArrayList <String> newindexList = new ArrayList<String>() ;

			for (int i = 0; i < indexList.size(); i++) {
				Index idx = (Index) indexList.get(i);
				StringBuilder st = new StringBuilder();
				st.append(idx.getCourseCode().trim());
				st.append(SEPARATOR);
				st.append(Integer. toString(idx.getIndex()).trim());
				st.append(SEPARATOR);
				st.append(idx.getGroup().trim());
				st.append(SEPARATOR);
				st.append(Integer. toString(idx.getVacancy()).trim());
				st.append(SEPARATOR);
				st.append(Integer. toString(idx.getWaitList()).trim());
				newindexList.add(st.toString());
			}
			IOforDB.write(IndexFileName, newindexList);
		}
}
