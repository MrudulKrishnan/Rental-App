package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

public class view_request_status extends AppCompatActivity {
    ListView request_status;
    SharedPreferences sh;
    String url;
    ArrayList<String>
            date_ary, status_ary, return_date_ary, product_name_ary, product_type_ary,
            product_details_ary, price_per_day_ary, image_ary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request_status);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        request_status=findViewById(R.id.L1_ViewRequestStatus);
        url = "http://" + sh.getString("ip", "") + ":5000/view_product_request";
        RequestQueue queue = Volley.newRequestQueue(view_request_status.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {
//                    Toast.makeText(send_complaints_view_reply.this, "=="+response, Toast.LENGTH_SHORT).show();

                    JSONArray ar=new JSONArray(response);
                    date_ary = new ArrayList<>();
                    status_ary = new ArrayList<>();
                    return_date_ary= new ArrayList<>();
                    product_name_ary = new ArrayList<>();
                    product_type_ary = new ArrayList<>();
                    product_details_ary = new ArrayList<>();
                    price_per_day_ary = new ArrayList<>();
                    image_ary = new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        date_ary.add(jo.getString("date"));
                        status_ary.add(jo.getString("status"));
                        return_date_ary.add(jo.getString("return_date"));
                        product_name_ary.add(jo.getString("product_name"));
                        product_type_ary.add(jo.getString("product_type"));
                        product_details_ary.add(jo.getString("product_details"));
                        price_per_day_ary.add(jo.getString("price_per_day"));
                        image_ary.add(jo.getString("image"));
                    }

//                     ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
//                    lv.setAdapter(ad);

                    request_status.setAdapter(new custom_view_request(view_request_status.this,date_ary
                            , status_ary, return_date_ary, product_name_ary, product_details_ary,
                            product_type_ary, price_per_day_ary, image_ary));
//                    l1.setOnItemClickListener(viewuser.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_request_status.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("lid", ""));
                return params;
            }
        };
        queue.add(stringRequest);
    }
    public void onBackPressed(){
        Intent ik =new Intent(getApplicationContext(), home.class);
        startActivity(ik);
    }
}