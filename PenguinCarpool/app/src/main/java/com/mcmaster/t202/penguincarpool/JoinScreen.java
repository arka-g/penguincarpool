package com.mcmaster.t202.penguincarpool;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mcmaster.t202.penguincarpool.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arka on 2014-11-29.
 */
public class JoinScreen extends LoginScreen implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_screen);
       findViewById(R.id.randBtn).setOnClickListener(this);

    }

    public void onClick(View arg0) {
        Button rand_btn = (Button) findViewById(R.id.randBtn);
        rand_btn.setClickable(false);
        //execute get and post
        new JoinGetIO().execute();
    }


    public class JoinGetIO extends AsyncTask<Void, Void, String> {

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


        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override

        protected String doInBackground(Void... params) {

            HttpContext localContext = new BasicHttpContext();
            //get available taxis
            HttpGet httpGet = new HttpGet("http://10.0.2.2/penguin-carpool/public/activeTaxi");
            String text = null;
            try {
                HttpResponse response2 = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response2.getEntity();
                text = getASCIIContentFromEntity(entity);

                JSONObject text_obj = new JSONObject(text);
                JSONArray jsonArray = text_obj.getJSONArray("taxi");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String taxi_loc = jsonObject.getString("taxi_location");
                    String taxi_id = jsonObject.getString("id");
                    Log.d("wtf", jsonObject.toString());
                }
//                String sigh = text_obj.getString("taxi");
//                JSONArray arr = text_obj.toJSONArray();
//
//
//                JSONArray taxis = new JSONArray(text_obj.get("taxi"));
//                Log.d("Array", arr);


//                JSONArray arr = new JSONArray(text);
//                JSONArray taxis = new JSONArray(text_obj.get("taxi"));
//                for (int i = 0; i < taxis.length(); i++) {
//
//                    JSONObject jsonobject = taxis.getJSONObject(i);
//                    Log.d("Array", jsonobject.toString());
//                    String name=jsonobject.getString("name");
//                    String Url=jsonobject.getString("url");

//                }
//                firstname = text_obj.getString("0");
//                lastname = text_obj.getString("1");
//                email = text_obj.getString("2");
//                id = text_obj.getInt("3");
//                Log.d("ID",Integer.toString(id));
                //transition to home screen if credentials are valid
//                if (status.equals("200")) {
//                    startActivity(new Intent(LoginScreen.this, HomeScreen.class));
//                }
//                else {
//                    //login error popup?
//                }
                return text;

            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
        }

        protected void onPostExecute(String results) {
            if (results != null) {
                EditText et = (EditText) findViewById(R.id.my_edit);
                et.setText(results);
            }
            Button rand_btn = (Button) findViewById(R.id.randBtn);
            rand_btn.setClickable(true);
        }


    }
}