package myStars;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Scanner;

public class UserInterface {

    protected int getIntInput(String prompt, int rangeStart, int rangeEnd){
        int temp = 0;
        boolean valid = false;
        Scanner sc = new Scanner(System.in);

        System.out.println(prompt);

        while(!valid){
            temp = sc.nextInt();
            if(temp==-1) return -1;
            if (temp > rangeEnd || temp < rangeStart)
                System.out.println("Invalid range. Please enter from " + rangeStart + " to " + rangeEnd);
            else valid = true;
        }

        return temp;
    }
    protected String getStringInput(String prompt){
        String temp;
        Scanner sc = new Scanner(System.in);

        System.out.println(prompt);

        temp = sc.nextLine();
        if(temp.equals("-1")) return "-1";
        else return temp;
    }
    protected Calendar getDateInput(String prompt){
        boolean validInput = false;
        String temp;
        Scanner sc = new Scanner(System.in);
        Calendar result = null;

        while(!validInput){
            System.out.println(prompt);
            temp = sc.nextLine();

            if(temp.equals("-1")) return null;

            try {
                result = CalendarCtrl.stringToCalendar(temp);
                validInput=true;
            } catch (ParseException e) {
                System.out.println("You should input a good format dd/MM/yyyy HH:mm");
            }
        }
        return result;
    }
    protected void printHeader(String... text){
        int maxLength=0;
        for(String s:text){
            if(s.length()>maxLength) maxLength = s.length();
        }
        for(int i=0; i<maxLength; i++){
            System.out.print("=");
        }
        System.out.println();

        for(String s:text){
            System.out.println(s);
        }

        for(int i=0; i<maxLength; i++){
            System.out.print("=");
        }
        System.out.println();
    }

}
