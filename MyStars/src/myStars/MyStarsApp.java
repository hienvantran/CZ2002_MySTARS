package myStars;

import java.text.ParseException;
import java.util.Scanner;

import Entities.ModeType;
import Entities.User;

public class MyStarsApp {
	public static void main(String[] args) throws ParseException {
		System.out.println("Welcome to MyStars App!");
		System.out.println("Please enter account type: ");
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
		login.login();
		login.showUI();
	}
}
