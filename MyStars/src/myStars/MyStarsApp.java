package myStars;

import java.util.Scanner;

public class MyStarsApp {
	public static void main(String[] args) {
		System.out.println("Welcome to MyStars App!");
		System.out.println("Please enter account type: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		LoginCtrl login = new LoginCtrl(input);
		login.login();
		login.showUI();
	}
}
