/*
    @desc: Class to represent a Contact's address
    @author: Jacob Vangore
    @project: Contacts App
 */
package com.example.jacob.contactsapp;

import java.io.Serializable;

public class Address implements Serializable{

    private String street;
    private String city;
    private String state;
    private String country;
    private String zip;

    //returns the street of the address
    public String getStreet() {
        return street;
    }
    //sets the street of the address
    public void setStreet(String street) {
        this.street = street;
    }
    //sets the city of the address
    public void setCity(String city){
        this.city = city;
    }
    // returns the city of the address
    public String getCity(){
        return city;
    }
    //sets the state of the address
    public void setState(String state){
        this.state = state;
    }
    //returns the state of the address
    public String getState(){
        return state;
    }
    //returns the country of the address
    public String getCountry() {
        return country;
    }
    //sets the country of the address
    public void setCountry(String country) {
        this.country = country;
    }
    //returns the zipcode of the address
    public String getZip() {
        return zip;
    }
    //sets the zip code for the address
    public void setZip(String zip) {
        this.zip = zip;
    }
}
