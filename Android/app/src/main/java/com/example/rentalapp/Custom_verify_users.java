package com.example.rentalapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.util.ArrayList;

class Custom_verify_users extends BaseAdapter {
    private Context context;
    ArrayList<String> name_ary, address_ary, phone_ary,email_ary, proof_ary, product_name_ary,
            product_details_ary, price_per_day_ary,product_type_ary, product_image_ary,
            product_id_Array;
    String title, url;
    SharedPreferences sh;
    public Custom_verify_users(Context applicationContext, ArrayList<String> name_arg,
                               ArrayList<String> address_arg, ArrayList<String> phone_arg,
                               ArrayList<String> email_arg, ArrayList<String> proof_arg,
                               ArrayList<String> product_name_arg, ArrayList<String> product_type_arg,
                               ArrayList<String> product_details_arg, ArrayList<String> price_per_day_arg,
                               ArrayList<String> product_image_arg)
    {
        // TODO Auto-generated constructor stub
        this.context = applicationContext;
        this.name_ary = name_arg;
        this.address_ary = address_arg;
        this.phone_ary = phone_arg;
        this.email_ary = email_arg;
        this.proof_ary = proof_arg;
        this.product_name_ary = product_name_arg;
        this.product_type_ary = product_type_arg;
        this.product_details_ary = product_details_arg;
        this.price_per_day_ary = price_per_day_arg;
        this.product_image_ary = product_image_arg;

        sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return product_name_ary.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int getItemViewType(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_custom_verify_users,null);

        }
        else
        {
            gridView=(View)convertview;

        }
        ///////////////////////
        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        /////////////////////////////////
        TextView tv1_username = (TextView)gridView.findViewById(R.id.TV1_Username);
        TextView tv2_address = (TextView)gridView.findViewById(R.id.TV2_UserAddress);
        TextView tv3_phone = (TextView)gridView.findViewById(R.id.TV3_UserPhone);
        TextView tv4_email = (TextView)gridView.findViewById(R.id.TV4_UserEmail);
        ImageView im_proof = (ImageView) gridView.findViewById(R.id.IV1_Proof);
        TextView tv1_product_name = (TextView)gridView.findViewById(R.id.Name);
        TextView tv2_product_type = (TextView)gridView.findViewById(R.id.Type);
        TextView tv3_product_details = (TextView)gridView.findViewById(R.id.Details);
        TextView tv4_product_price = (TextView)gridView.findViewById(R.id.Price);
        ImageView im_product_image = (ImageView) gridView.findViewById(R.id.Product_image);

        java.net.URL thumb_u;
        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000"+proof_ary.get(position));
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            im_product_image.setImageDrawable(thumb_d);

        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }

        java.net.URL thumb_v;
        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_v = new java.net.URL("http://"+sh.getString("ip","")+":5000"+product_image_ary.get(position));
            Drawable thumb_d = Drawable.createFromStream(thumb_v.openStream(), "src");
            im_proof.setImageDrawable(thumb_d);

        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }
        tv1_username.setText(name_ary.get(position));
        tv2_address.setText(address_ary.get(position));
        tv3_phone.setText(phone_ary.get(position));
        tv4_email.setText(email_ary.get(position));
        tv1_product_name.setText(product_name_ary.get(position));
        tv2_product_type.setText(product_type_ary.get(position));
        tv3_product_details.setText(product_details_ary.get(position));
        tv4_product_price.setText(price_per_day_ary.get(position));

        tv1_product_name.setTextColor(Color.BLACK);
        tv2_product_type.setTextColor(Color.BLACK);
        tv3_product_details.setTextColor(Color.BLACK);
        tv4_product_price.setTextColor(Color.BLACK);

        return gridView;

    }

}
