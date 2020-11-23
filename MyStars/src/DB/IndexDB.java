package DB;

import java.io.*;
import java.util.*;
import java.text.ParseException;

import Entities.*;

public class IndexDB {
	public static final String SEPARATOR = "|";

	private static final String IndexFileName = "data//Index.txt";
	public static ArrayList<Index> retrieveIndex() throws FileNotFoundException, ParseException{
    	// read String from text file
    	ArrayList<String> stringArray = (ArrayList) IOforDB.read(IndexFileName);
    	ArrayList<Index> indexList = new ArrayList<Index>();
    	for (int i = 0 ; i < stringArray.size() ; i++) {
            String field = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			// pass in the string to the string tokenizer using delimiter "," 
			StringTokenizer tokenizer = new StringTokenizer(field, SEPARATOR);	
			//CourseCode/index/Group/totalSlot/waitList
			String  crsCode = tokenizer.nextToken().trim();	
			int index = Integer. parseInt((tokenizer.nextToken().trim())); 
			String  group = tokenizer.nextToken().trim();
			int totalSlot = Integer. parseInt((tokenizer.nextToken().trim()));
			int waitlist = Integer. parseInt((tokenizer.nextToken().trim()));
			// create Index object and add to course list 
			Index idx = new Index( crsCode, index, group, totalSlot, waitlist);
			// add to Index list
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
				st.append(Integer. toString(idx.getTotalSlot()).trim());
				st.append(SEPARATOR);
				st.append(Integer. toString(idx.getWaitList()).trim());
				newindexList.add(st.toString());
			}
			IOforDB.write(IndexFileName, newindexList);
		}
}
