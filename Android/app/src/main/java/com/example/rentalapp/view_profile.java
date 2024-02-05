package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class view_profile extends AppCompatActivity {
    TextView firstname, lastname, place, post, pin, phone, email,
            username, password;
//    String  firstname_str, lastname_str, place_str, post_str, pin_str,
//            phone_str, email_str, proof_str, username_str, password_str,url;
    String proof_str;
    ImageView iv1_photo;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        firstname = findViewById(R.id.tv1_firstname);
        lastname = findViewById(R.id.tv2_lastname);
        place = findViewById(R.id.tv3_place);
        post = findViewById(R.id.tv4_post);
        pin = findViewById(R.id.tv5_pin);
        phone = findViewById(R.id.tv6_phone);
        email = findViewById(R.id.tv7_email);
        iv1_photo = findViewById(R.id.I1_imageView);
//        username = findViewById(R.id.tv9_username);
//        password = findViewById(R.id.tv10_password);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        RequestQueue queue = Volley.newRequestQueue(view_profile.this);

        String url = "http://" + sh.getString("ip", "") + ":5000/view_profile";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);

                    {
                        JSONObject jo=ar.getJSONObject(0);
                        firstname.setText(jo.getString("Firstname"));
                        lastname.setText(jo.getString("Lastname"));
                        place.setText(jo.getString("Place"));
                        post.setText(jo.getString("Post"));
                        pin.setText(jo.getString("Pin"));
                        phone.setText(jo.getString("Phone"));
                        email.setText(jo.getString("Email"));


//                        Picasso.get().load(imageUrl).into(iv1_photo);
//                        iv1_photo.setText(jo.getString("Proof"));
                        if(android.os.Build.VERSION.SDK_INT>9)
                        {
                            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                        }
//                        iv1_photo.setImageDrawable(Drawable.createFromPath(getIntent().getStringExtra("proof_str")));
                        java.net.URL thumb_u;
                        try {
                            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");
                            Toast.makeText(view_profile.this, "err" + jo.getString("Proof"), Toast.LENGTH_SHORT).show();

                            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000"+jo.getString("Proof"));
                            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
                            iv1_photo.setImageDrawable(thumb_d);
                        }
                        catch (Exception e)
                        {
                            Log.d("errsssssssssssss",""+e);
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"Error_##",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("uid", sh.getString("lid", ""));
                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    public void onBackPressed(){
        Intent ik =new Intent(getApplicationContext(), home.class);
        startActivity(ik);
    }

}