package myStars;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import Entities.ModeType;

/**
 * Login user interface
 * @author Hoo Jia Kai
 */
public class LoginUI extends UserInterface {

	/**
	 * constructor for LoginUI
	 */
	public LoginUI() {
		
	}
	
	/**
	 * display the user interface for logging in
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public void showUI() throws FileNotFoundException, ParseException {
		printHeader("Welcome to MyStars App!");
		CalendarCtrl.GetCurrentDatetime();		
		int input = getIntInput("Please enter account type: \n(1) for student, (2) for admin",1,2);
		ModeType mode;
		if(input == 1) {
			mode = ModeType.USER;
			printHeader("Now logging in through student portal");
		}
		else {
			mode = ModeType.ADMIN;
			printHeader("Now logging in through admin portal");
		}
		LoginCtrl login = new LoginCtrl(mode);
		boolean loggedin = login.login();
		while(loggedin!=true) {
			System.out.println("Please try again.\n");
			loggedin = login.login();
		}
	}
	
}
