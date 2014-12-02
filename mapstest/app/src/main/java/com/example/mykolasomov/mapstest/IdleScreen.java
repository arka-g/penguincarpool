package com.example.mykolasomov.mapstest;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class IdleScreen extends com.example.mykolasomov.mapstest.LoginScreen {
    public static int qrVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle_screen);
        findViewById(R.id.idleScan).setOnClickListener(this);
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.idle_screen, menu);
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
//
//    public void launchQRScan(View view) {
//        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
//        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
//        startActivityForResult(intent, 0);
//
//    }
    public void onClick(View arg0) {
        Button scan = (Button) findViewById(R.id.idleScan);
        scan.setClickable(false);
        //execute get and post
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);

        new QRPost().execute();
    }


    private class QRPost extends AsyncTask<Void, Void, String> {
        @Override

        protected String doInBackground(Void... params) {
            Log.d("im here",Integer.toString(qrVal));

           // HttpClient httpClient = new DefaultHttpClient();
            // replace with your url
            HttpPost httpPost = new HttpPost("http://192.168.0.16/penguin-carpool/public/updateState");
           // HttpPost httpPost = new HttpPost("http://172.17.31.169/penguin-carpool/public/updateState");
            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
            nameValuePair.add(new BasicNameValuePair("id", Integer.toString(qrVal)));
            nameValuePair.add(new BasicNameValuePair("Taxi_loc", "thode test qr scan"));
            nameValuePair.add(new BasicNameValuePair("Active_State", Integer.toString(1)));
            nameValuePair.add(new BasicNameValuePair("Carpool", Integer.toString(1)));
            nameValuePair.add(new BasicNameValuePair("User_id", Integer.toString(id)));

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
            Log.d("integer returned",Integer.toString(qrVal));
            return Integer.toString(qrVal);
        }
        protected void onPostExecute(String results) {
            Button scan = (Button) findViewById(R.id.idleScan);
            scan.setClickable(true);
        }
}
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                qrVal = Integer.parseInt(contents);
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                // Handle successful scan
                Toast toast = Toast.makeText(this, "Content:" + contents + " Format:" + format , Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();
            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
                Toast toast = Toast.makeText(this, "Scan was Cancelled!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();

            }
        }
    }
    // Launch MapScreen from IdleScreen
    public void launchMapScreen(View v){
        startActivity(new Intent(IdleScreen.this, MapsActivity.class));
    }
}