package com.example.mykolasomov.mapstest;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class MessageScreen extends LoginScreen {
    public String nametopass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_screen);
        TextView textView = (TextView) findViewById(R.id.userMessage);
        textView.setText(LoginScreen.message);
        String wtftest = LoginScreen.message;
        TextView textViewrand = (TextView) findViewById(R.id.totald);
        textViewrand.setText(Float.toString(IdleScreen.totalD) + " km");
        String[] split = wtftest.split(" ");
        nametopass = split[1];
        Log.d("name",split[1]);
        TextView textView1 = (TextView) findViewById(R.id.result_msg);
        textView1.setText(LoginScreen.message_result);
        findViewById(R.id.btnYes).setOnClickListener(this);
        findViewById(R.id.btnNo).setOnClickListener(this);
    }

    public void onClick(View arg0) {
        Button btnYes = (Button) findViewById(R.id.btnYes);
        btnYes.setClickable(false);
        Button btnNo = (Button) findViewById(R.id.btnNo);
        btnNo.setClickable(false);

        if (arg0.equals(btnYes)) {
            //execute get and post
            new YesIO().execute();
        }
        else if (arg0.equals(btnNo)){
            new NoIO().execute();
        }
    }

    private class YesIO extends AsyncTask<Void, Void, String> {

        @Override

        protected String doInBackground(Void... params) {

            //HttpClient httpClient = new DefaultHttpClient();
            // replace with your url
            HttpPost httpPost = new HttpPost("http://172.17.82.216/penguin-carpool/public/msgaccept");
//for phone:
//            HttpPost httpPost = new HttpPost("http://172.17.31.169/penguin-carpool/public/neworder");

            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            nameValuePair.add(new BasicNameValuePair("id",Integer.toString(id)));
            nameValuePair.add(new BasicNameValuePair("User_send_ID", nametopass));
            Log.d("id",Integer.toString(id));
            Log.d("User_send_ID",nametopass);

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
//                startActivity(new Intent(MessageScreen.this, HomeScreen.class));
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
            return "no";
        }

        protected void onPostExecute(String results) {
            Button yesBtn = (Button) findViewById(R.id.btnYes);
            yesBtn.setClickable(true);
        }


    }
    private class NoIO extends AsyncTask<Void, Void, String> {

        @Override

        protected String doInBackground(Void... params) {

            HttpPost httpPost = new HttpPost("http://172.17.82.216/penguin-carpool/public/msgdecline");
//for phone:
//            HttpPost httpPost = new HttpPost("http://172.17.31.169/penguin-carpool/public/neworder");

            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            nameValuePair.add(new BasicNameValuePair("id",Integer.toString(id)));
            nameValuePair.add(new BasicNameValuePair("User_send_ID", nametopass));

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
                Log.d("YOU CLICKED ME", response.toString());

//                startActivity(new Intent(RequestScreen.this, JoinScreen.class));
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


            return "no";
        }

        protected void onPostExecute(String results) {
            Button noBtn = (Button) findViewById(R.id.btnNo);
            noBtn.setClickable(true);
        }


    }
}
