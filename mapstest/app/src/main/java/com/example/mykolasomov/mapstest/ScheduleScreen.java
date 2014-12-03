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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class ScheduleScreen extends LoginScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_screen);
        findViewById(R.id.schedSubmitInfo).setOnClickListener(this);
    }

    public void onClick(View arg0) {
        Button sched_btn = (Button) findViewById(R.id.schedSubmitInfo);
        sched_btn.setClickable(false);
        //execute get and post
        new ScheduleGetIO().execute();
    }


    public class ScheduleGetIO extends AsyncTask<Void, Void, String> {

        protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
            InputStream in = entity.getContent();

            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n > 0) {
                byte[] b = new byte[4096];
                n = in.read(b);
                if (n > 0) out.append(new String(b, 0, n));
            }
            return out.toString();
        }


        @Override

        protected String doInBackground(Void... params) {

            EditText date = (EditText) findViewById(R.id.schedDate);
            EditText time = (EditText) findViewById(R.id.schedTime);
            EditText pickup_loc = (EditText) findViewById(R.id.schedPickup);
            EditText dropoff_loc = (EditText) findViewById(R.id.schedDropoff);

            String date_sched = date.getText().toString();
            String time_sched = time.getText().toString();
            String pickup = pickup_loc.getText().toString();
            String dropoff = dropoff_loc.getText().toString();


            HttpPost httpPost = new HttpPost("http://192.168.1.118/penguin-carpool/public/saveschedule");
            //HttpPost httpPost = new HttpPost("http://172.17.31.169/penguin-carpool/public/login");
            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
            nameValuePair.add(new BasicNameValuePair("User_ID", Integer.toString(id)));
            nameValuePair.add(new BasicNameValuePair("Date", date_sched));
            nameValuePair.add(new BasicNameValuePair("Time", time_sched));
            nameValuePair.add(new BasicNameValuePair("Pickup_Location", pickup));
            nameValuePair.add(new BasicNameValuePair("destination", dropoff));

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
//                Log.d("Http Post Response:", nameValuePair.toString());

            } catch (ClientProtocolException e) {
                // Log exception
                Log.d("Client Exception", e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                // Log exception
                Log.d("IO Exception", e.toString());
                e.printStackTrace();
            }

            HttpContext localContext = new BasicHttpContext();
            //connection to localhost?Return error: http://172.17.60.37:80/yo
            HttpGet httpGet = new HttpGet("http://192.168.1.118/penguin-carpool/public/getschedule");
            String text = null;
            try {
                HttpResponse response1 = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response1.getEntity();
                text = getASCIIContentFromEntity(entity);
                //parse the returned text in a nicer format for schedule later
            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
            return text;
        }

        protected void onPostExecute(String results) {
            if (results != null) {
                EditText et = (EditText) findViewById(R.id.my_edit);
                et.setText(results);
            }
            Button sched_btn = (Button) findViewById(R.id.schedSubmitInfo);
            sched_btn.setClickable(true);
        }


    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.schedule_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/


}
