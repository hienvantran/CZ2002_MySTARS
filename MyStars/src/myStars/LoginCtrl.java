package myStars;

import Entities.User;
import Entities.AcademicStaff;
import Entities.ModeType;
import Entities.Student;
import myStars.CalendarCtrl;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

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
	
	public boolean login() throws ParseException {
		this.setUsername();
		this.setPassword();
		if(this.mode == ModeType.USER) {
			//insert check here
			ArrayList<Student> studentdb = this.retrieveStudent();
			for (Student student : studentdb) {
				if(this.match(student)) {
					System.out.println("Successfully logged in!");
					System.out.println("Checking access time...");
					if(CalendarCtrl.CheckAccessTime(student.getAccessStart(), student.getAccessEnd())==true) {
						this.makeValid();
						return true;
					}
					return false;
				}
			}
		} 
		else {
			ArrayList<User> admindb = this.retrieveAdmin();
			for (User user : admindb) {
				if(this.match(user)) {
					System.out.println("Successfully logged in!");
					this.makeValid();
					return true;
				}
			}
		}
		
		System.out.println("Failed to log in!");
		return false;
		
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
	
	
	public ArrayList<User> retrieveAdmin(){
    	ArrayList<User> admin = new ArrayList<User>();
    	try  {  
	    	//the file to be opened for reading  
	    	FileInputStream fis=new FileInputStream("data/AdminAcc.txt");       
	    	Scanner sc=new Scanner(fis);    //file to be scanned  
	    	//returns true if there is another line to read
	    	while(sc.hasNextLine())  
	    	{	
	    		String[] line;
	    		String username;
	    		String pass;
	    		line = sc.nextLine().split("\\|");
	    		username = line[0];
	    		pass = line[1];
	    		admin.add(new User(username, pass, ModeType.ADMIN));
//	    		System.out.println(sc.nextLine());      //returns the line that was skipped  
	    	}  
	    	sc.close();     //closes the scanner  
	    	}  
	    	catch(IOException e)  {  e.printStackTrace();  }  
		return admin;
    }
	
	public ArrayList<Student> retrieveStudent() throws ParseException{
    	ArrayList<Student> student = new ArrayList<Student>();
    	try  {  
	    	//the file to be opened for reading  
	    	FileInputStream fis=new FileInputStream("data/StudentAcc.txt");       
	    	Scanner sc=new Scanner(fis);    //file to be scanned  
	    	//returns true if there is another line to read
	    	while(sc.hasNextLine())  
	    	{	
	    		String[] line;
	    		String username;
	    		String pass;
	    		String yos;
	    		String accessStart;
	    		String accessEnd;
	    		String nationality;
	    		line = sc.nextLine().split("\\|");
	    		username = line[0];
	    		pass = line[1];
	    		yos = line[2];
	    		accessStart = line[3];
	    		accessEnd = line[4];
	    		nationality = line[5];
	    		Calendar startCal = CalendarCtrl.stringToCalendar(accessStart);
	    		Calendar endCal = CalendarCtrl.stringToCalendar(accessEnd);
	    		student.add(new Student(username, pass, ModeType.USER, pass, Integer.parseInt(yos), startCal, endCal, nationality));
	    	}  
	    	sc.close();   
	    	}  
	    	catch(IOException e)  {  e.printStackTrace();  }  
		return student;
    }
}
