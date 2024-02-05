package com.example.rentalapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class chat_with_users extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView l1_chat_workers;
    SharedPreferences sh;
    ArrayList<String> name, user_id;
    String url;
    String user_id_str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_users);
        l1_chat_workers=findViewById(R.id.L1_ChatWorker);
        l1_chat_workers.setOnItemClickListener(chat_with_users.this);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/view_users_for_chat";
        RequestQueue queue = Volley.newRequestQueue(chat_with_users.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    user_id= new ArrayList<>();
                    name= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        user_id.add(jo.getString("user_id"));
                        name.add(jo.getString("first_name")+ "" + jo.getString("last_name"));


                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<>(chat_with_users.this,android.R.layout.simple_list_item_1,name);
                    l1_chat_workers.setAdapter(ad);

//                    L1.setAdapter(new custom2(chatwithfriends.this,name));


                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(chat_with_users.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("uid",sh.getString("lid",""));

                return params;
            }
        };
        queue.add(stringRequest);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        user_id_str=user_id.get(position);
        SharedPreferences.Editor edp = sh.edit();
        edp.putString("uid", user_id_str);
        edp.commit();

        Intent i=new Intent(getApplicationContext(), Test.class);
        i.putExtra("toid",user_id.get(position));
        i.putExtra("name",name.get(position));

        startActivity(i);

    }
    public void onBackPressed(){
        Intent ik =new Intent(getApplicationContext(), home.class);
        startActivity(ik);
    }
}