package myStars;

import Entities.User;
import Entities.AcademicStaff;
import Entities.ModeType;
import Entities.Student;
import DB.AdminDB;
import DB.StudentDB;
import myStars.StudentUI;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginCtrl {
	private String username;
	private String password;
	private ModeType mode;
	private boolean validated;
	
	public LoginCtrl(ModeType accountType) {
		this.mode = accountType;
		this.validated = false;
	}
	
	public void setUsername() {
		System.out.println("Please enter your username: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		this.username = input;
	}
	
	public void setPassword() {
		String password;
		System.out.println("Please enter your password: ");
		password=pass();
		this.password= HashCtrl.hashPassword(password);
	}
	
	public String pass() {
		java.io.Console c = System.console();
		if (c == null) {
			Scanner sc = new Scanner(System.in);
			String pass = sc.next();
			return pass;
        }
		char[] passString;
		//System.out.println("Please Enter Password");
		passString = c.readPassword();
		String pass = new String(passString);
		return pass;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void makeValid() {
		this.validated=true;
	}
	
	public boolean login() throws ParseException , FileNotFoundException{
		this.setUsername();
		this.setPassword();
		if(this.mode == ModeType.USER) {
			//insert check here
			ArrayList<Student> studentdb = StudentDB.retrieveStudent();
			for (Student student : studentdb) {
//				System.out.println(student.getUsername());
//				System.out.println(student.getPassword());
				if(this.match(student)) {
					System.out.println("Successfully logged in!");
					System.out.println("Checking access time...");
					if(CalendarCtrl.CheckAccessTime(student.getAccessStart(), student.getAccessEnd())==true) {
						this.makeValid();
						StudentUI studentui = new StudentUI();
						studentui.printUI(student);
						return true;
					}
				}
			}
			return false;
		} 
		else {
			ArrayList<User> admindb = AdminDB.retrieveAdmin();
			for (User user : admindb) {
				if(this.match(user)) {
					System.out.println("Successfully logged in!"); 	
					this.makeValid();
					AdminUI admin = new AdminUI();
					admin.printUI();
					return true;
				}
			}
		}
		
		System.out.println("Failed to log in!");
		return false;
		
	}
	
	public boolean match(User user) {
    	if((this.getUsername().toLowerCase()).equals((user.getUsername().toLowerCase())) && this.getPassword().equals(user.getPassword())) {
    		return true;
    	}
    	else return false;
    }
	
	public boolean match(Student student) {
//		System.out.println("lower case input = "+ this.getUsername().toLowerCase());
//		System.out.println("Matched against = " + student.getUsername().toLowerCase());
//		System.out.println("lower case input = "+ this.getPassword());
//		System.out.println("Matched against = " +student.getPassword());
    	if((this.getUsername().toLowerCase()).equals((student.getUsername().toLowerCase())) && this.getPassword().equals(student.getPassword())) {
    		return true;
    	}
    	else return false;
    }
	
	
}
