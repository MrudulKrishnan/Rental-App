package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class verify extends AppCompatActivity {
    EditText return_date;
    Button bt_return_date;
    String return_date_str, url,req_id;
//    CalendarView return_date;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        return_date = findViewById(R.id.idEdtDate);
        bt_return_date = findViewById(R.id.BT_ReturnDate);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        return_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        verify.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                return_date.setText( year+ "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

        bt_return_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return_date_str = return_date.getText().toString();

                if (return_date_str.equalsIgnoreCase("")){
                    return_date.setError("Enter Your password");
                }
                else {

                    RequestQueue queue = Volley.newRequestQueue(verify.this);
                    url = "http://"+sh.getString("ip","")+":5000/add_verify_slot";
                    Toast.makeText(verify.this,url , Toast.LENGTH_SHORT).show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response)
                        {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try
                            {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");

                                if (res.equalsIgnoreCase("success"))
                                {
//                                    String lid = json.getString("id");     // getting login id

                                    Intent ik = new Intent(getApplicationContext(), home.class);
                                    startActivity(ik);
                                }
                                else
                                {
                                    Toast.makeText(verify.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    },new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams()
                        {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("ReturnDate", return_date_str);
                            params.put("rid",sh.getString("rid","") );
                            return params;
                        }
                    };
                    queue.add(stringRequest);

                }

            }


        });
//        register_butt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i2 = new Intent(getApplicationContext(), registration.class);
//                startActivity(i2);
//            }
//        });
    }


    @Override
    public void onBackPressed() {
        Intent i2 = new Intent(getApplicationContext(), registration.class);
                startActivity(i2);

    }
}