package com.example.rentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class borrowed_products extends AppCompatActivity
{
    ListView view_borrowed_product_list;
    SharedPreferences sh;
    String url,product_id_str;
    ArrayList<String> borrowed_date, status, return_date, products, price_per_day, details, type, image,
            product_id_ArrayStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrowed_products);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        view_borrowed_product_list=findViewById(R.id.L1_BorrowedProduct);
        url = "http://" + sh.getString("ip", "") + ":5000/view_borrowed_products";
        RequestQueue queue = Volley.newRequestQueue(borrowed_products.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
                    Toast.makeText(borrowed_products.this, ""+response, Toast.LENGTH_SHORT).show();

                    JSONArray ar = new JSONArray(response);
                    borrowed_date = new ArrayList<>();
                    status = new ArrayList<>();
                    return_date = new ArrayList<>();
                    products = new ArrayList<>();
                    type = new ArrayList<>();
                    details = new ArrayList<>();
                    price_per_day = new ArrayList<>();
                    image = new ArrayList<>();
                    product_id_ArrayStr = new ArrayList<>();

                    for (int i = 0; i < ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        borrowed_date.add(jo.getString("Borrowed_date"));
                        status.add(jo.getString("Status"));
                        return_date.add(jo.getString("Return_date"));
                        products.add(jo.getString("Product"));
                        type.add(jo.getString("Type"));
                        details.add(jo.getString("Details"));
                        price_per_day.add(jo.getString("Price_per_day"));
                        image.add(jo.getString("Image"));
//                        product_id_ArrayStr.add(jo.getString("product_id"));

                    }

//                     ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
//                    lv.setAdapter(ad);

                    view_borrowed_product_list.setAdapter(new custom_view_request(borrowed_products.this,borrowed_date,status,return_date,products, type, details, price_per_day, image));
//                    view_product_list.setOnItemClickListener(view_product.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(borrowed_products.this, "err" + error, Toast.LENGTH_SHORT).show();
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
    public void onBackPressed(){
        Intent ik =new Intent(getApplicationContext(), home.class);
        startActivity(ik);
    }

}