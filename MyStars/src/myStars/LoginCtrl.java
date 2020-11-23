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
/**
 * Controller class for login
 * @author Jia Kai
 * @version 1.0
 * @since 2020-11-23
 */
public class LoginCtrl {
	/**
	 * the username input of the login attempt
	 */
	private String username;
	/**
	 * the password input of the login attempt
	 */
	private String password;
	/**
	 * the domain of the login attempt
	 */
	private ModeType mode;
	/**
	 * the validity of the login attempt
	 * default value = false
	 */
	private boolean validated= false;
	/**
	 * constructor for Login Ctrl
	 * @param accountType to define the logjn attempt
	 */
	public LoginCtrl(ModeType accountType) {
		this.mode = accountType;
		this.validated = false;
	}
	
	/**
	 * to input the username for the login attempt
	 */
	public void setUsername() {
		System.out.println("Please enter your username: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		this.username = input;
	}
	
	/**
	 * to input the password for the login attempt
	 * password will be masked during input (except for when using eclipse)
	 * password input will also be hashed to compare with the hashed password in DB
	 */
	public void setPassword() {
		String password;
		System.out.println("Please enter your password: ");
		password=pass();
		this.password= HashCtrl.hashPassword(password);
	}
	
	/**
	 * function to mask the input during password
	 * @return input for password
	 */
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
	
	/**
	 * gets the username of the login attempt
	 * @return username input
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * gets the password of the login attempt
	 * @return password input
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * sets the validity of the login attempt to true
	 */
	public void makeValid() {
		this.validated=true;
	}
	
	/**
	 * function to initiate a login attempt
	 * @return true or false depending on whether attempt is successful
	 * @throws ParseException
	 * @throws FileNotFoundException
	 */
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
						student.getMatricNum();
						studentui.printUI(student.getMatricNum());
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
	
	/**
	 * function call to verify that admin username input and password input matches the one in adminDB
	 * @param user admin account 
	 * @return true or false depending on validity of login attempt
	 */
	public boolean match(User user) {
    	if((this.getUsername().toLowerCase()).equals((user.getUsername().toLowerCase())) && this.getPassword().equals(user.getPassword())) {
    		return true;
    	}
    	else return false;
    }
	
	/**
	 * function call to verify that student username input and password input matches the one in studentDB
	 * @param student student account 
	 * @return true or false depending on validity of login attempt
	 */
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
