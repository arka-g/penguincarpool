package com.example.mykolasomov.mapstest;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class ProfileScreen extends LoginScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        findViewById(R.id.deletebtn).setOnClickListener(this);

        TextView textView = (TextView) findViewById(R.id.userFirstName);
        TextView textView1 = (TextView) findViewById(R.id.userLastName);
        TextView textView2 = (TextView) findViewById(R.id.userEmail);

        RatingBar ratinguser = (RatingBar) findViewById(R.id.userRatingAve);

        textView.setText(firstname);
        textView1.setText(lastname);
        textView2.setText(email);
        ratinguser.setRating(rating);
    }
    public void onClick(View arg0) {
        Button b = (Button) findViewById(R.id.deletebtn);
        b.setClickable(false);
        //execute get and post
        new DeleteIO().execute();
    }


    private class DeleteIO extends AsyncTask<Void, Void, String> {
        @Override

        protected String doInBackground(Void... params) {
            // replace with your url
            HttpPost httpPost = new HttpPost("http://172.17.20.216/penguin-carpool/public/delete");
//            HttpPost httpPost = new HttpPost("http://172.17.31.169/penguin-carpool/public/save");
            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
            nameValuePair.add(new BasicNameValuePair("id", Integer.toString(id)));

            //Encoding POST data
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            } catch (UnsupportedEncodingException e) {
                // log exception
                e.printStackTrace();
            }

            //making POST request.
            try {
                HttpResponse response = httpClient.execute(httpPost);
                // write response to log
                Log.d("Http Post Response:", response.toString());
            } catch (ClientProtocolException e) {
                // Log exception
                Log.d("Client Exception", e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                // Log exception
                Log.d("IO Exception", e.toString());
                e.printStackTrace();
            }
            //this is useless
            return "nothing";
        }

        protected void onPostExecute(String results) {
            Button b = (Button) findViewById(R.id.deletebtn);
            b.setClickable(true);
            // Return to LoginScreen
            startActivity(new Intent(ProfileScreen.this, LoginScreen.class));
        }


    }

    public void goToEdit(View v){
        startActivity(new Intent(ProfileScreen.this, EditScreen.class));
    }
}
