package myStars;

import Entities.User;
import Entities.AcademicStaff;
import Entities.ModeType;
import Entities.Student;
import DB.AdminDB;
import DB.StudentDB;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
		String input = sc.next();
		this.username = input;
	}
	
	public void setPassword() {
		System.out.println("Please enter your password: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		this.password=input;
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
	
	public void login() throws ParseException , FileNotFoundException{
		this.setUsername();
		this.setPassword();
		if(this.mode == ModeType.USER) {
			//insert check here
			ArrayList<Student> studentdb = StudentDB.retrieveStudent();
			for (Student student : studentdb) {
				if(this.match(student)) {
					System.out.println("Successfully logged in!");
					this.makeValid();
					break;
				}
			}
		} 
		else {
			ArrayList<User> admindb = AdminDB.retrieveAdmin();
			for (User user : admindb) {
				if(this.match(user)) {
					System.out.println("Successfully logged in!");
					System.out.println("Checking access time...");
					this.makeValid();
					break;
				}
			}
		}
		
		if(this.validated!=true) {
			System.out.println("Failed to log in!");
		}
		
	}
	
	public void showUI() {
		if (validated== true && this.mode == ModeType.USER) {
				StudentUI s1 = new StudentUI();
				s1.printUI();
			}
		if (validated== true && this.mode == ModeType.ADMIN) {
				AdminUI a1 = new AdminUI();
				a1.printUI();
		}
	}
	
	public boolean match(User user) {
    	if(this.getUsername().equals(user.getUsername()) && this.getPassword().equals(user.getPassword())) {
    		return true;
    	}
    	else return false;
    }
	
	public boolean match(Student student) {
    	if(this.getUsername().equals(student.getUsername()) && this.getPassword().equals(student.getPassword())) {
    		return true;
    	}
    	else return false;
    }
}
