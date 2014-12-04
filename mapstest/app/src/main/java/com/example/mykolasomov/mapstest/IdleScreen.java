package com.example.mykolasomov.mapstest;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

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
    public static int qrVal2;
    static long startTime = 0;
    static long finalTime = 0;
    static long elapsedTime = 0;
    private float distance;
    private String destination;
    public static float totalD = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle_screen);
        findViewById(R.id.idleScan).setOnClickListener(this);
        findViewById(R.id.confirmscan).setOnClickListener(this);
        if (startTime == 0) {
            startTime = SystemClock.elapsedRealtime();
            ((Chronometer) findViewById(R.id.chronometer)).start();
        }
        else{
            ((Chronometer) findViewById(R.id.chronometer)).setBase(SystemClock.elapsedRealtime()- (SystemClock.elapsedRealtime() - startTime));
            ((Chronometer) findViewById(R.id.chronometer)).start();
        }

    }


    public void onDestroy(){
        finalTime = (((Chronometer) findViewById(R.id.chronometer)).getBase() - SystemClock.elapsedRealtime())/1000;
        super.onDestroy();
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
        ((Chronometer) findViewById(R.id.chronometer)).stop();
        Button scan_confirm = (Button) findViewById(R.id.confirmscan);
        scan_confirm.setClickable(true);

        if (arg0.equals(scan_confirm)) {
            //execute get and post
            new QRPost2().execute();

        }
        else if (arg0.equals(scan)){
            //execute get and post
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        }

    }


    private class QRPost2 extends AsyncTask<Void, Void, String> {
        @Override

        protected String doInBackground(Void... params) {
            Log.d("im here",Integer.toString(qrVal2));

           // HttpClient httpClient = new DefaultHttpClient();
            // replace with your url
            HttpPost httpPost = new HttpPost("http://172.17.82.216/penguin-carpool/public/updateState");
           // HttpPost httpPost = new HttpPost("http://172.17.31.169/penguin-carpool/public/updateState");
            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(4);
            nameValuePair.add(new BasicNameValuePair("id", Integer.toString(qrVal2)));
//            nameValuePair.add(new BasicNameValuePair("Taxi_loc", "thode test qr scan"));
            nameValuePair.add(new BasicNameValuePair("Active_State", Integer.toString(0)));
            nameValuePair.add(new BasicNameValuePair("Carpool", Integer.toString(0)));
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
                startActivity(new Intent(IdleScreen.this, RateScreen.class));

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
            Log.d("integer returned",Integer.toString(qrVal2));
            return Integer.toString(qrVal2);
        }
        protected void onPostExecute(String results) {
            Button scan = (Button) findViewById(R.id.confirmscan);
            scan.setClickable(true);
        }
}
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK && intent.hasExtra("SCAN_RESULT")) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                qrVal2 = Integer.parseInt(contents);
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

        if (resultCode == RESULT_OK) {
            if (intent.hasExtra("distance")) {
                distance = intent.getFloatExtra("distance",0);
                Log.e("distance",Float.toString(distance));
                destination = intent.getStringExtra("destination");
                Log.e("destination",destination);

                TextView tv = (TextView)findViewById(R.id.idleViewEstimate);
                tv.setText("$" + Float.toString(distance));
                tv.setTextSize(80);

                tv = (TextView)findViewById(R.id.idleDestination);
                tv.setText("Destination: " + destination);
                totalD = intent.getFloatExtra("totalD",0);
            }
        }
    }
    // Launch MapScreen from IdleScreen
    public void launchMapScreen(View v){
        startActivityForResult(new Intent(IdleScreen.this, MapsActivity.class), 0);
    }
}