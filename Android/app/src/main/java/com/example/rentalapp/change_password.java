package com.example.rentalapp;

import static com.google.android.material.color.utilities.MaterialDynamicColors.error;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class  change_password extends AppCompatActivity
{
    EditText current_password, new_password, confirm_password;
    Button butt_change_password;
    String current_password_str, new_password_str, confirm_password_str, url, url1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        current_password = findViewById(R.id.E1_CurrentPassword);
        new_password = findViewById(R.id.E2_NewPassword);
        confirm_password = findViewById(R.id.E3_ConfirmPassword);
        butt_change_password = findViewById(R.id.butt_ChangePassword);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        RequestQueue queue = Volley.newRequestQueue(change_password.this);

//        String url = "http://" + sh.getString("ip", "") + ":5000/change_password";
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>()
//        {
//            @Override
//            public void onResponse(String response) {
//                // Display the response string.
//                Log.d("+++++++++++++++++",response);
//                try {
//
//                    JSONArray ar=new JSONArray(response);
//
//                    {
//                        JSONObject jo=ar.getJSONObject(0);
//                        current_password.setText(jo.getString("password"));
//
//                        if(android.os.Build.VERSION.SDK_INT>9)
//                        {
//                            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                            StrictMode.setThreadPolicy(policy);
//                        }
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(getApplicationContext(),"Error_##",Toast.LENGTH_LONG).show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String>  params = new HashMap<String, String>();
//                params.put("lid", sh.getString("lid", ""));
//                return params;
//            }
//        };
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//
        butt_change_password.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                current_password_str = current_password.getText().toString();
                new_password_str = new_password.getText().toString();
                confirm_password_str = confirm_password.getText().toString();

                if (current_password_str.equalsIgnoreCase("")) {
                    current_password.setError("Enter your current password");
                } else if (new_password_str.equalsIgnoreCase("")) {
                    new_password.setError("Enter your new password");
                } else if (confirm_password_str.equalsIgnoreCase("")) {
                    confirm_password.setError("Enter your new password again");
                }
                else
                {

                if (new_password_str.equals(confirm_password_str)) {

                    RequestQueue queue = Volley.newRequestQueue(change_password.this);
                    url1 = "http://" + sh.getString("ip", "") + ":5000/changing_password";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");

                                if (res.equalsIgnoreCase("success")) {
                                    Toast.makeText(change_password.this, "change password successfully ", Toast.LENGTH_SHORT).show();

                                    Intent ik = new Intent(getApplicationContext(), home.class);
                                    startActivity(ik);
                                } else {

                                    Toast.makeText(change_password.this, "invalid current password", Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("current_password", current_password_str);
                            params.put("new_password", new_password_str);
                            params.put("confirm_password", confirm_password_str);
                            params.put("cid", sh.getString("lid", ""));

                            return params;
                        }
                    };
                    queue.add(stringRequest);
                } else {
                    Toast.makeText(change_password.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
            }
            }
        });

    }
    public void onBackPressed(){
        Intent ik =new Intent(getApplicationContext(), home.class);
        startActivity(ik);
    }
}