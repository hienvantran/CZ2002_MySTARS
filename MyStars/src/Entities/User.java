package Entities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * an abstract User class
 * @author Jia Kai
 * @version 1.0
 * @since 2020-11-21
 */
public class User{
    private String username;
    private String password;
    private ModeType mode;
    public User(String username, String password, ModeType mode){
        this.username = username;
        this.password = password;
        this.mode = mode;
    }

    /**
     * Gets the password of this user
     * @return this user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Changes the password of this user
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Gets the modetype of this user
     * @return this user's modetype
     */
    public ModeType getMode() {
        return mode;
    }
    
    /**
     * Changes the modetype of this user
     * @param mode
     */
    public void setMode(ModeType mode) {
        this.mode = mode;
    }

    /**
     * Gets the username of this user
     * @return this user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Changes the username of this user
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    

}