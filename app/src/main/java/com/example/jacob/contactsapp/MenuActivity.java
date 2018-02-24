/*
    @desc: Class that sets up the list view of all the contacts.
    Tutorial I followed: http://yasirameen.com/2016/04/android-json-parsing-using-volley/
    @author: Jacob Vangore
    @project: Contacts App
*/

package com.example.jacob.contactsapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity{

    List<Contact> contactsList = new ArrayList<Contact>();
    List<Contact> favContactsList = new ArrayList<Contact>();
    ListView lv;
    ListView fv;
    String uri = "https://s3.amazonaws.com/technical-challenge/v3/contacts.json";
    Contact current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Contacts Application");

        requestData(uri);

        //Make all of the Favorite Contacts Appear
        lv = (ListView) findViewById(R.id.listView);
        lv.setClickable(true);

        fv = (ListView) findViewById(R.id.favView);
        fv.setClickable(true);

        TextView textView = new TextView(MenuActivity.this);
        textView.setText("Favorites");
        TextView contactsTextView = new TextView(MenuActivity.this);
        contactsTextView.setText("Contacts");

        fv.addHeaderView(textView);
        lv.addHeaderView(contactsTextView);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                current = contactsList.get(position);
                Intent intent = new Intent(MenuActivity.this, DetailActivity.class);
                intent.putExtra("CURRENT", current);
                //based on item add info to intent
                startActivity(intent);
            }
        });

        fv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                current = favContactsList.get(position);
                Intent intent = new Intent(MenuActivity.this, DetailActivity.class);
                intent.putExtra("CURRENT", current);
                //based on item add info to intent
                startActivity(intent);
            }
        });
    }

    public void requestData(String uri) {
        StringRequest request = new StringRequest(uri,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ContactsJSONParser.parseData(response);
                        contactsList = ContactsJSONParser.getContacts();
                        favContactsList = ContactsJSONParser.getFavoriteContacts();
                        ContactsAdapter favAdapter = new ContactsAdapter(MenuActivity.this, favContactsList);
                        ContactsAdapter adapter = new ContactsAdapter(MenuActivity.this, contactsList);
                        fv.setAdapter(favAdapter);
                        lv.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MenuActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
