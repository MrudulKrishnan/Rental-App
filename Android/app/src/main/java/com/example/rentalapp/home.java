package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class home extends AppCompatActivity {
    Button butt1_view_profile, butt2_product_manage, butt3_verify_users,
    butt4_borrowed_product, butt5_view_product, butt6_chat, butt7_product_request,
    butt8_request_status, butt9_borrowed_history, butt10_complaint, butt11_change_pass,
    butt12_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        butt1_view_profile = findViewById(R.id.butt_view_profile);
        butt2_product_manage = findViewById(R.id.butt_product_management);
        butt3_verify_users = findViewById(R.id.butt_verify_users);
        butt4_borrowed_product = findViewById(R.id.butt_borrowed_product);
        butt5_view_product = findViewById(R.id.butt_view_product);
        butt6_chat = findViewById(R.id.butt_chat);
        butt8_request_status = findViewById(R.id.butt_request_status);
//        butt9_borrowed_history = findViewById(R.id.butt_borrowed_history);
        butt10_complaint = findViewById(R.id.butt_send_complaint);
        butt11_change_pass = findViewById(R.id.butt_change_pass);
        butt12_logout = findViewById(R.id.butt_logout);

        butt1_view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(), view_profile.class);
                startActivity(i2);

            }
        });

        butt2_product_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(), manage_product.class);
                startActivity(i2);


            }
        });

        butt3_verify_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(), view_users.class);
                startActivity(i2);


            }
        });

        butt4_borrowed_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(), borrowed_products.class);
                startActivity(i2);


            }
        });

        butt5_view_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(), view_product.class);
                startActivity(i2);


            }
        });

        butt6_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(), chat_with_users.class);
                startActivity(i2);


            }
        });

        butt8_request_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(), view_request_status.class);
                startActivity(i2);


            }
        });

//        butt9_borrowed_history.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i2=new Intent(getApplicationContext(), view_borrowed_history.class);
//                startActivity(i2);
//            }
//        });

        butt10_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(), send_complaints_view_reply.class);
                startActivity(i2);
            }
        });

        butt11_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(), change_password.class);
                startActivity(i2);
            }
        });

        butt12_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(), login.class);
                startActivity(i2);
            }
        });
    }
}