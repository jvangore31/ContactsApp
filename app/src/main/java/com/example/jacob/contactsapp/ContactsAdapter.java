/*
    @desc: Class to Populate List View with Contacts.
    Tutorial I followed: http://yasirameen.com/2016/04/android-json-parsing-using-volley/
    @author: Jacob Vangore
    @project: Contacts App
 */
package com.example.jacob.contactsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> contactsList = new ArrayList<Contact>();
    private LayoutInflater inflater = null;
    private LruCache<Integer, Bitmap> imageCache;
    private RequestQueue queue;

    //Constructor
    public ContactsAdapter(Context context, List<Contact> list){

        this.context = context;
        this.contactsList = list;
        inflater = LayoutInflater.from(context);

        final int maxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        imageCache = new LruCache<>(cacheSize);

        queue = Volley.newRequestQueue(context);
    }
    //Class to temporarily hold data
    public class ViewHolder{

        TextView _name;
        TextView _company;
        ImageView _contactImage;
        TextView _email;
    }

    // returns size of list
    @Override
    public int getCount(){
        return contactsList.size();
    }
    //returns specified item from list
    @Override
    public Contact getItem(int index){
        return contactsList.get(index);
    }
    //returns the id of a contact in the list
    @Override
    public long getItemId(int index){
        return index;
    }
    //returns one view for contact
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        final Contact contacts = contactsList.get(position);
        final ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contact_item_templates, null);
            holder = new ViewHolder();

            holder._name = (TextView) convertView.findViewById(R.id.name);
            holder._company = (TextView) convertView.findViewById(R.id.company);
            holder._email = (TextView) convertView.findViewById(R.id.email);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder._name.setText(contacts.getName());
        holder._email.setText(contacts.getEmail());
        holder._company.setText(contacts.getCompany());
        if(contacts.getFavorite()) {
            holder._company.setText(contacts.getCompany());
        }

        Bitmap bitmap = imageCache.get(contacts.getId());

        holder._contactImage = (ImageView)convertView.findViewById(R.id.contactImage);

        if (bitmap != null) {

            holder._contactImage.setImageBitmap(bitmap);

        }else {
            String imageURL = contacts.getSmallImageURL();
            ImageRequest request = new ImageRequest(imageURL,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            holder._contactImage.setImageBitmap(response);
                            imageCache.put(contacts.getId(), response);
                        }
                    },
                    90, 90,
                    Bitmap.Config.ARGB_8888,
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            NetworkResponse response = error.networkResponse;
                            if (error instanceof ServerError && response != null) {
                                try {
                                    String res = new String(response.data,
                                            HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                    // Now you can use any deserializer to make sense of data
                                    JSONObject obj = new JSONObject(res);
                                } catch (UnsupportedEncodingException e1) {
                                    // Couldn't properly decode data to string
                                    e1.printStackTrace();
                                } catch (JSONException e2) {
                                    // returned data is not JSONObject?
                                    e2.printStackTrace();
                                }
                            }
                        }
                    });
            queue.add(request);
        }
        return convertView;
    }



}