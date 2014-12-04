package com.example.mykolasomov.mapstest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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


public class EditScreen extends LoginScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_screen);
        findViewById(R.id.regSubmitInfoEdit).setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.regFirstNameEdit);
        TextView textView1 = (TextView) findViewById(R.id.regLastNameEdit);
        TextView textView2 = (TextView) findViewById(R.id.regEmailEdit);

        textView.setText(firstname);
        textView1.setText(lastname);
        textView2.setText(email);
    }
    public void onClick(View arg0) {
        Button b = (Button) findViewById(R.id.regSubmitInfoEdit);
        b.setClickable(false);
        //execute get and post
        new EditIO().execute();
    }


    private class EditIO extends AsyncTask<Void, Void, String> {
        @Override

        protected String doInBackground(Void... params) {


            EditText first_name_edit = (EditText) findViewById(R.id.regFirstNameEdit);
            EditText last_name_edit = (EditText) findViewById(R.id.regLastNameEdit);
            EditText email_edit = (EditText) findViewById(R.id.regEmailEdit);
            //EditText username = (EditText) findViewById(R.id.username);
            EditText password_edit = (EditText) findViewById(R.id.regPasswordEdit);

            String str1 = first_name_edit.getText().toString();
            String str2 = last_name_edit.getText().toString();
            String str3 = email_edit.getText().toString();
            //String str4 = username.getText().toString();
            String str5 = password_edit.getText().toString();

            str1 = Base64.encodeToString(str1.getBytes(), Base64.DEFAULT);
            Log.e("YOLO", str1);
            str1 = new String(Base64.decode(str1, Base64.DEFAULT));
            Log.e("YOLO", str1);


            // replace with your url
            HttpPost httpPost = new HttpPost("http://172.17.82.216/penguin-carpool/public/edit");
//            HttpPost httpPost = new HttpPost("http://172.17.31.169/penguin-carpool/public/save");
            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
            nameValuePair.add(new BasicNameValuePair("id", Integer.toString(id)));
            nameValuePair.add(new BasicNameValuePair("first_name", str1));
            nameValuePair.add(new BasicNameValuePair("last_name", str2));
            nameValuePair.add(new BasicNameValuePair("email", str3));
//                nameValuePair.add(new BasicNameValuePair("username", str4));
            nameValuePair.add(new BasicNameValuePair("password", str5));

            Log.d("id",Integer.toString(id));
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
            Button b = (Button) findViewById(R.id.regSubmitInfoEdit);
            b.setClickable(true);

        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.edit_screen, menu);
//        return true;
//    }
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
