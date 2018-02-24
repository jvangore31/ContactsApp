/*
    @desc: Class to represent a Contact's Phone Numbers
    @author: Jacob Vangore
    @project: Contacts App
 */
package com.example.jacob.contactsapp;

import java.io.Serializable;

public class Phone implements Serializable{

    private String work;
    private String home;
    private String mobile;
    //sets returns the work phone number
    public String getWork() {
        return work;
    }
    // sets the work phone number
    public void setWork(String work) {
        this.work = work;
    }
    //gets the home number
    public String getHome() {
        return home;
    }
    // sets the home number
    public void setHome(String home) {
        this.home = home;
    }
    // gets the mobile number
    public String getMobile() {
        return mobile;
    }
    // sets the mobile number
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}