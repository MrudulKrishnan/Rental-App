package com.example.rentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

public class view_users extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView l1_users;
    ArrayList<String> request_id, Name, Address, phone,email, proof,product_name_ary, product_type_ary,
            product_details_ary, price_per_day_ary, product_image_ary;
    String url;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);
//        Toast.makeText(view_users.this, "err", Toast.LENGTH_SHORT).show();

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1_users=findViewById(R.id.L1_Users);
        url = "http://" + sh.getString("ip", "") + ":5000/verify_users";
        RequestQueue queue = Volley.newRequestQueue(view_users.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
//                    Toast.makeText(view_users.this, "err2", Toast.LENGTH_SHORT).show();

                    JSONArray ar = new JSONArray(response);
                    request_id = new ArrayList<>();
                    Name = new ArrayList<>();
                    Address = new ArrayList<>();
                    phone = new ArrayList<>();
                    email = new ArrayList<>();
                    proof = new ArrayList<>();
                    product_name_ary = new ArrayList<>();
                    product_type_ary =new ArrayList<>();
                    product_details_ary = new ArrayList<>();
                    price_per_day_ary = new ArrayList<>();
                    product_image_ary = new ArrayList<>();


                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        request_id.add(jo.getString("rid"));
                        Name.add(jo.getString("Firstname")+""+jo.getString("Lastname"));
                        Address.add(jo.getString("Place")+ "" + jo.getString("Post")+
                                ""+jo.getString("Pin"));
                        phone.add(jo.getString("Phone"));
                        email.add(jo.getString("Email"));
                        proof.add(jo.getString("Proof"));
                        product_name_ary.add(jo.getString("product_name"));
                        product_type_ary.add(jo.getString("product_type"));
                        product_details_ary.add(jo.getString("product_details"));
                        price_per_day_ary.add(jo.getString("price_per_day"));
                        product_image_ary.add(jo.getString("image"));
                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1_users.setAdapter(new Custom_verify_users(view_users.this,Name, Address, phone,
                            email, proof, product_name_ary, product_type_ary, product_details_ary, price_per_day_ary,
                            product_image_ary));
                    l1_users.setOnItemClickListener(view_users.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(view_users.this, "err"+e, Toast.LENGTH_SHORT).show();

                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_users.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("lid", ""));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


        Intent i2=new Intent(getApplicationContext(), verify.class);
//        i2.putExtra("rid",request_id);
        SharedPreferences.Editor edp = sh.edit();
        edp.putString("rid", request_id.get(i));
        edp.commit();
        startActivity(i2);

    }
    public void onBackPressed(){
        Intent ik =new Intent(getApplicationContext(), home.class);
        startActivity(ik);
    }
}