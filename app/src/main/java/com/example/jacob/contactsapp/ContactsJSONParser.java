/*
    @desc: Class to process the JSON into an Object List
    Tutorial I followed: http://yasirameen.com/2016/04/android-json-parsing-using-volley/
    @author: Jacob Vangore
    @project: Contacts App
 */
package com.example.jacob.contactsapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ContactsJSONParser {

    static List<Contact> contactsList;
    static List<Contact> favoriteContacts;


    //reads json into both favorites and regular contacts
    public static void parseData(String content){

        JSONArray contacts_array = null;
        Contact contacts = null;

        try {
            contacts_array = new JSONArray(content);
            contactsList = new ArrayList<Contact>();
            favoriteContacts = new ArrayList<Contact>();

            for (int i = 0; i < contacts_array.length(); i++) {

                JSONObject contact = contacts_array.getJSONObject(i);
                JSONObject phone = contact.getJSONObject("phone");
                JSONObject address = contact.getJSONObject("address");
                contacts = new Contact();

                contacts.setId(i+1);
                contacts.setName(contact.getString("name"));
                if(contact.has("companyName")){
                contacts.setCompany(contact.getString("companyName"));}
                contacts.setFavorite(contact.optBoolean("isFavorite", false));
                contacts.setSmallImg(contact.getString("smallImageURL"));
                contacts.setLargeImageURL(contact.getString("largeImageURL"));
                contacts.setEmail(contact.getString("emailAddress"));
                //contacts.setWebsite(contact.getString("website"));
                contacts.setBirthdate(contact.getString("birthdate"));
                if(phone.has("mobile")){
                    contacts.setMobile(phone.getString("mobile"));}
                if(phone.has("work")){
                contacts.setWork(phone.getString("work"));}
                if(phone.has("home")) {
                contacts.setHome(phone.getString("home"));}
                contacts.setAddress(address.getString("street"), address.getString("city"), address.getString("state"),
                address.getString("country"), address.getString("zipCode"));
                if(!contacts.getFavorite() || contacts.getFavorite() == null) {
                    contactsList.add(contacts);
                }
                else if(contacts.getFavorite()){
                    favoriteContacts.add(contacts);
                }
            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }

    }

    //returns all of the non favorite contacts
    public static List<Contact> getContacts(){
        return contactsList;
    }

    //returns all of the favorite contacts
    public static List<Contact> getFavoriteContacts(){
        return favoriteContacts;
    }

}








