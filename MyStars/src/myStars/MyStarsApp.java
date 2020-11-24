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
		LoginUI loginUi = new LoginUI();
		loginUi.showUI();
	}
}
