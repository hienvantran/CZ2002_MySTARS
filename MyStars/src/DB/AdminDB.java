package DB;

import java.io.*;
import java.text.*;
import java.util.*;

import Entities.AcademicStaff;
import Entities.ModeType;
import Entities.User;



public class AdminDB {
	public static final String SEPARATOR = "|";
	
	private static final String AdminAccFileName = "data//AdminAcc.txt";
	public static ArrayList<AcademicStaff> retrieveAdmin() throws FileNotFoundException{
    	// read String from text file
    	ArrayList<String> stringArray = (ArrayList) IOforDB.read(AdminAccFileName);
    	ArrayList<AcademicStaff> admin = new ArrayList<AcademicStaff>();
    	for (int i = 0 ; i < stringArray.size() ; i++) {
            String field = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			// pass in the string to the string tokenizer using delimiter "," 
			StringTokenizer tokenizer = new StringTokenizer(field, SEPARATOR);	
			//first to fifth tokens
			String  username = tokenizer.nextToken().trim();	
			String  pass = tokenizer.nextToken().trim();	
			
			// create User object and add to admin list 
			admin.add(new AcademicStaff(username, pass, ModeType.ADMIN));
		}
		return admin;
    }
}
	// update new entry into File
//	public static void saveAdmin(ArrayList<AcademicStaff> admin) throws IOException {
//			ArrayList <String> newAdmin = new ArrayList<String>() ;
//
//			for (int i = 0; i < admin.size(); i++) {
//				User acc = (User) admin.get(i);
//				StringBuilder st = new StringBuilder();
//				st.append(acc.getUsername().trim());
//				st.append(SEPARATOR);
//				st.append(acc.getPassword().trim());
//				st.append(SEPARATOR);
//				st.append(acc.getMode().toString().trim());
//				st.append(SEPARATOR);
//				st.append(acc.getFirstName().trim());
//				st.append(SEPARATOR);
//				st.append(acc.getLastName().trim());
//				st.append(SEPARATOR);
//				st.append(acc.getGender().trim());
//				st.append(SEPARATOR);
//				st.append(acc.getEmail().trim());
//				newAdmin.add(st.toString());
//			}
//			IOforDB.write(AdminAccFileName, newAdmin);
//		}
//}


