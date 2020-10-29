package myStars;

import java.util.Scanner;

public class LoginCtrl {
	private String username;
	private String password;
	private String accountType;
	private boolean validated;
	
	public LoginCtrl(String accountType) {
		this.accountType = accountType;
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
	
	public String getUsername(String username) {
		return this.username;
	}
	
	public String getPassword(String password) {
		return this.password;
	}
	
	public void makeValid() {
		this.validated=true;
	}
	
	public void login() {
		this.setUsername();
		this.setPassword();
//		insert check here
		System.out.println("Successfully logged in!");
		this.makeValid();
//		System.out.println("Failed to log in!");
	}
	
	public void showUI() {
		if (validated== true && accountType.equals("Student")) {
				StudentUI s1 = new StudentUI();
				s1.printUI();
			}
		if (validated== true && accountType.equals("Admin")) {
				AdminUI a1 = new AdminUI();
				a1.printUI();
		}
	}
}
