package myStars;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

import Entities.ModeType;
import Entities.User;
import myStars.CalendarCtrl;
import myStars.LoginCtrl;

public class MyStarsApp {
	public static void main(String[] args) throws ParseException, FileNotFoundException {
		System.out.println("Welcome to MyStars App!");
		System.out.println("Please enter account type: ");
		CalendarCtrl.GetCurrentDatetime();
		System.out.println("(1) for student, (2) for admin");
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		ModeType mode = ModeType.ADMIN;
		int pass =0;
		while (pass!=1) { 
			switch(input) {
			case 1:
				mode = ModeType.USER;
				pass=1;
				break;
			case 2:
				mode = ModeType.ADMIN;
				pass=1;
				break;
			default:
				System.out.println("please enter (1) for student, (2) for admin");
				input = sc.nextInt();
			}
		}
		LoginCtrl login = new LoginCtrl(mode);
		boolean loggedin = login.login();
		while(loggedin!=true) {
			System.out.println("Please try again.\n");
			loggedin = login.login();
		}
	}
}
