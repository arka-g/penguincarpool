package com.mcmaster.t202.penguincarpool;

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


public class RequestScreen extends LoginScreen implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_screen);
        findViewById(R.id.reqOrder).setOnClickListener(this);
        findViewById(R.id.reqJoin).setOnClickListener(this);
    }
    public void onClick(View arg0) {
        Button order_btn = (Button) findViewById(R.id.reqOrder);
        order_btn.setClickable(false);
        Button join_btn = (Button) findViewById(R.id.reqJoin);
        join_btn.setClickable(false);
        //execute get and post
        new OrderIO().execute();
    }
    private class OrderIO extends AsyncTask<Void, Void, String> {
//        protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
//            InputStream in = entity.getContent();
//
//            StringBuffer out = new StringBuffer();
//            int n = 1;
//            while (n > 0) {
//                byte[] b = new byte[4096];
//                n = in.read(b);
//                if (n > 0) out.append(new String(b, 0, n));
//            }
//            return out.toString();
//        }

        @Override

        protected String doInBackground(Void... params) {

            EditText user_location = (EditText) findViewById(R.id.reqPickup);
            EditText user_destination = (EditText) findViewById(R.id.reqDropoff);

            String str_loc = user_location.getText().toString();
            String str_dec = user_destination.getText().toString();

            //HttpClient httpClient = new DefaultHttpClient();
            // replace with your url
            HttpPost httpPost = new HttpPost("http://10.0.2.2/penguin-carpool/public/neworder");

            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(3);
            nameValuePair.add(new BasicNameValuePair("User_Location", str_loc));
            nameValuePair.add(new BasicNameValuePair("User_destination", str_dec));
            Log.d("ID",Integer.toString(id));
            nameValuePair.add(new BasicNameValuePair("user_id", Integer.toString(id)));

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
                startActivity(new Intent(RequestScreen.this, IdleScreen.class));
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
//            //verify login
//            HttpContext localContext = new BasicHttpContext();
//            HttpGet httpGet = new HttpGet("http://10.0.2.2/penguin-carpool/public/login");
//            String text = null;
//            try {
//                HttpResponse response1 = httpClient.execute(httpGet, localContext);
//                HttpEntity entity = response1.getEntity();
//                text = getASCIIContentFromEntity(entity);
//
//                JSONObject text_obj = new JSONObject(text);
//                String status = text_obj.getString("status");
//                //int id = text_obj.getInt("3");
//                //transition to home screen if credentials are valid
//                if (status.equals("200")) {
//                    startActivity(new Intent(LoginScreen.this, HomeScreen.class));
//                }
//                else {
//                    //login error popup?
//                }
//                return text;
//
//            } catch (Exception e) {
//                return e.getLocalizedMessage();
//            }
            return str_dec;
        }

        protected void onPostExecute(String results) {
            Button b = (Button) findViewById(R.id.reqOrder);
            b.setClickable(true);
            Button join_btn = (Button) findViewById(R.id.reqJoin);
            join_btn.setClickable(true);
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.request_screen, menu);
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
    }


}
