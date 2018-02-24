/*
    @desc: Class to represent a Contact
    @author: Jacob Vangore
    @project: Contacts App
 */
package com.example.jacob.contactsapp;

import java.io.Serializable;

public class Contact implements Serializable{

    private String name;
    private int id;
    private String company;
    private Boolean favorite;
    private String smallImageURL;
    private String largeImageURL;
    private String email;
    private String birthdate;
    private Phone phone = new Phone();
    private Address address = new Address();

    //sets the id of a contact
    public void setId(int id){
        this.id = id;
    }
    //returns the id of a contact
    public int getId(){
        return id;
    }
    //sets the name of a contact
    public void setName(String name){
        this.name = name;
    }
    //returns the name of a contact
    public String getName() {
        return name;
    }
    //sets the company of a contact
    public void setCompany(String company){
        this.company = company;
    }
    //returns the company of a contact
    public String getCompany(){
        return company;
    }
    //sets the favorite status of a contact
    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
    //returns whether a contact is a favorite or not
    public Boolean getFavorite(){
        return favorite;
    }
    //sets the smallImg string for a contact
    public void setSmallImg(String img){
        this.smallImageURL = img;
    }
    //returns a small image sting for a contact
    public String getSmallImageURL(){
        return smallImageURL;
    }
    //sets a large image string for a contact
    public void setLargeImageURL(String img){
        this.largeImageURL = img;
    }
    // returns a large image string for a contact
    public String getLargeImageURL(){
        return largeImageURL;
    }
    //sets an email for the contact
    public void setEmail(String email){
        this.email = email;
    }
    //returns an email for the contact
    public String getEmail(){
        return email;
    }
    //sets the birthday of a contact
    public void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }
    //returns the birthday of a contact
    public String getBirthdate(){
        return birthdate;
    }
    //sets the work phone number of a contact
    public void setWork(String w) {
        phone.setWork(w);
    }
    //sets the home phone number of a contact
    public void setHome(String h){
        phone.setHome(h);
    }
    // sets the mobile home number of a contact
    public void setMobile(String m){
        phone.setMobile(m);
    }
    // sets the address of a contact
    public void setAddress(String street, String city, String state, String country, String zip){
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setZip(zip);
    }
    // returns the mobile phone number of a contact
    public String getMobile(){
        return phone.getMobile();
    }
    // returns the home phone number of a contact
    public String getHome(){
        return phone.getHome();
    }
    // returns the work phone number of contact
    public String getWork(){return phone.getWork();}
    //returns the street of a contact
    public String getStreet(){
        return address.getStreet();
    }
    //returns the city of a contact
    public String getCity(){
        return address.getCity();
    }
    // returns the state of a contact
    public String getState(){
        return address.getState();
    }
    //returns the zipcode of a contact
    public String getZip(){
        return address.getZip();
    }
    //returns the country of a contact
    public String getCountry(){
        return address.getCountry();
    }
}
