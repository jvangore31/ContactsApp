/*
    @desc: Class that sets up the detailed view of a specific Contact.
    Tutorial I followed: http://yasirameen.com/2016/04/android-json-parsing-using-volley/
    @author: Jacob Vangore
    @project: Contacts App
 */

package com.example.jacob.contactsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.LayoutInflater;
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

public class DetailActivity extends AppCompatActivity {

    Context mContext;
    Contact current;
    private LayoutInflater inflater = null;
    private LruCache<Integer, Bitmap> imageCache;
    private RequestQueue queue;

   // class to temporarily hold the detailed data of contact
    public class Holder{

        ImageView _image;
        TextView _company;
        TextView _phone;
        TextView _email;
        TextView _address;
        TextView _birthday;
        TextView _favorite;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_item_detail);
        current =(Contact) getIntent().getSerializableExtra("CURRENT");

        setTitle(current.getName());
        mContext = this;
        inflater = LayoutInflater.from(mContext);

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        imageCache = new LruCache<>(cacheSize);

        queue = Volley.newRequestQueue(mContext);


        final Holder  holder;
        holder = new Holder();


        holder._company = (TextView)findViewById(R.id.company);
        holder._phone = (TextView)findViewById(R.id.contactPhone);
        holder._email = (TextView)findViewById(R.id.contactEmail);
        holder._address = (TextView)findViewById(R.id.contactAddress);
        holder._birthday = (TextView)findViewById(R.id.contactBirthday);
        holder._favorite = (TextView)findViewById(R.id.contactFavorite);


        holder._company.setText(current.getCompany());

        holder._phone.setText("Work: " + current.getWork()+ "\n"
        + "Home: " + current.getHome() + "\n" + "Mobile: " + current.getMobile());
        holder._email.setText("Email: " + current.getEmail());
        holder._address.setText(current.getStreet() + "\n" +
        current.getCity() + ", " + current.getState() + " " + current.getZip() + "\n" +
        current.getCountry());
        holder._birthday.setText("Birthday: " + current.getBirthdate());

        if(!current.getFavorite()){
            holder._favorite.setText("");
        }else {
            holder._favorite.setText("Favorite: Yes");
        }

        Bitmap bitmap = imageCache.get(current.getId());

        holder._image = (ImageView)findViewById(R.id.largeContactImage);

        if (bitmap != null) {
            holder._image.setImageBitmap(bitmap);

        }else{
            String imageURL = current.getLargeImageURL();
            ImageRequest request = new ImageRequest(imageURL,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            holder._image.setImageBitmap(response);
                            imageCache.put(current.getId(), response);
                        }
                    },
                    90, 90,
                    Bitmap.Config.ARGB_8888,
                    new Response.ErrorListener() {
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
    }

}




