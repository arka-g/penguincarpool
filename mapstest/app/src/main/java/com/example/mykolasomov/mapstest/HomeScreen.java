package com.example.mykolasomov.mapstest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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


public class HomeScreen extends LoginScreen {
    public static int qrVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        findViewById(R.id.viewIdleScreen).setOnClickListener(this);
        findViewById(R.id.confirmscan1).setOnClickListener(this);
        /*EditText et = (EditText) findViewById(R.id.my_edit);
        et.setText("You are logged in!");*/
    }


    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    //    getMenuInflater().inflate(R.menu.home_screen, menu);
    //    return true;
    //}

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    //    int id = item.getItemId();
    //    if (id == R.id.action_settings) {
    //        return true;
    //    }
    //    return super.onOptionsItemSelected(item);
    //}

    // Launch ProfileScreen from HomeScreen
    public void launchViewProfile(View v){

        startActivity(new Intent(HomeScreen.this, ProfileScreen.class));
    }

    // Launch ScheduleScreen from HomeScreen
    public void launchScheduleScreen(View v){
        startActivity(new Intent(HomeScreen.this, ScheduleScreen.class));
    }

    // Launch RequestScreen from HomeScreen
    public void launchRequestScreen(View v){
        startActivity(new Intent(HomeScreen.this, RequestScreen.class));
    }

    // Launch IdleScreen from HomeScreen
//    public void launchIdleScreen(View v){
//        startActivity(new Intent(HomeScreen.this, IdleScreen.class));
//    }

    public void onClick(View arg0) {
        Button scan1 = (Button) findViewById(R.id.viewIdleScreen);
        scan1.setClickable(false);
        Button scan_confirm1 = (Button) findViewById(R.id.confirmscan1);
        scan_confirm1.setClickable(true);

        if (arg0.equals(scan_confirm1)) {
            new QRPost().execute();
        }
        else if (arg0.equals(scan1)){
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);
        }
    }

    private class QRPost extends AsyncTask<Void, Void, String> {
        @Override

        protected String doInBackground(Void... params) {

//            Log.d("im here", Integer.toString(qrVal));

            // HttpClient httpClient = new DefaultHttpClient();
            // replace with your url
            HttpPost httpPost = new HttpPost("http://172.17.82.216/penguin-carpool/public/updateState");
            // HttpPost httpPost = new HttpPost("http://172.17.31.169/penguin-carpool/public/updateState");
            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(5);
            nameValuePair.add(new BasicNameValuePair("id", Integer.toString(qrVal)));
//            nameValuePair.add(new BasicNameValuePair("Taxi_loc", "thode test qr scan"));
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
                startActivity(new Intent(HomeScreen.this, IdleScreen.class));
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
            Button scan = (Button) findViewById(R.id.viewIdleScreen);
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
//                startActivity(new Intent(HomeScreen.this, IdleScreen.class));

            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
                Toast toast = Toast.makeText(this, "Scan was Cancelled!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 25, 400);
                toast.show();

            }
        }
    }
    public void gotoMessage(View v){
        startActivity(new Intent(HomeScreen.this, MessageScreen.class));
    }
}
