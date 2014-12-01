package com.mcmaster.t202.penguincarpool;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
//encryption
//import android.util.Base64;
//import org.apache.commons.codec.binary.Base64;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


public class RegisterScreen extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        findViewById(R.id.regSubmitInfo).setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    public void onClick(View arg0) {
        Button b = (Button) findViewById(R.id.regSubmitInfo);
        b.setClickable(false);
        //execute get and post
        new RegisterIO().execute();
    }


    private class RegisterIO extends AsyncTask<Void, Void, String> {
        @Override

        protected String doInBackground(Void... params) {


            EditText first_name = (EditText) findViewById(R.id.regFirstName);
            EditText last_name = (EditText) findViewById(R.id.regLastName);
            EditText email = (EditText) findViewById(R.id.regEmail);
            //EditText username = (EditText) findViewById(R.id.username);
            EditText password = (EditText) findViewById(R.id.regPassword);

            String str1 = first_name.getText().toString();
            String str2 = last_name.getText().toString();
            String str3 = email.getText().toString();
            //String str4 = username.getText().toString();
            String str5 = password.getText().toString();

            //byte[] encodedBytes = Base64.encodeBase64(str1.getBytes());

            HttpClient httpClient = new DefaultHttpClient();
            // replace with your url
           // HttpPost httpPost = new HttpPost("http://10.0.2.2/penguin-carpool/public/save");
            HttpPost httpPost = new HttpPost("http://172.17.31.169/penguin-carpool/public/save");
            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(4);
            nameValuePair.add(new BasicNameValuePair("first_name", str1));
            nameValuePair.add(new BasicNameValuePair("last_name", str2));
            nameValuePair.add(new BasicNameValuePair("email", str3));
//                nameValuePair.add(new BasicNameValuePair("username", str4));
            nameValuePair.add(new BasicNameValuePair("password", str5));


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
            return str1;
        }

        protected void onPostExecute(String results) {
            Button b = (Button) findViewById(R.id.regSubmitInfo);
            b.setClickable(true);
        }


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
