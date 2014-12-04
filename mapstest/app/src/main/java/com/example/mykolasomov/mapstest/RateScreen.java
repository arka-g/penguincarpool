package com.example.mykolasomov.mapstest;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nolan on 30/11/2014.
 */
public class RateScreen extends LoginScreen{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_screen);
        findViewById(R.id.submitRating).setOnClickListener(this);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(rating);
//        findViewById(R.id.ratingBar).seto
    }

    public void onClick(View arg0) {
        Button rate = (Button) findViewById(R.id.submitRating);
        rate.setClickable(false);
        //execute get and post
        new RateIO().execute();
    }


    private class RateIO extends AsyncTask<Void, Void, String> {
        @Override

        protected String doInBackground(Void... params) {
            EditText textrating = (EditText) findViewById(R.id.textrating);
            String ratingSubmit = textrating.getText().toString();

            // replace with your url
            HttpPost httpPost = new HttpPost("http://172.17.82.216/penguin-carpool/public/rate");
//            HttpPost httpPost = new HttpPost("http://172.17.31.169/penguin-carpool/public/save");
            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            nameValuePair.add(new BasicNameValuePair("taxi_id", Integer.toString(IdleScreen.qrVal2)));
            nameValuePair.add(new BasicNameValuePair("rate", ratingSubmit));
//            Log.d("Rate",String.valueOf(rating));
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
                // Return to LoginScreen
                startActivity(new Intent(RateScreen.this, HomeScreen.class));
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
            Button rate = (Button) findViewById(R.id.submitRating);
            rate.setClickable(true);
        }


    }



//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }




}
