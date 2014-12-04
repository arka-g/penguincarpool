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


public class LoginScreen extends Activity implements View.OnClickListener {

    public static HttpClient httpClient = new DefaultHttpClient();
    public static String firstname;
    public static String lastname;
    public static String email;
    public static String message;
    public static int id;
    public static String message_result;
    public static int rating;
    public static String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        findViewById(R.id.loginButton).setOnClickListener(this);
       // findViewById(R.id.userRatingAve).setOnClickListener(this);
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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

    public void onClick(View arg0) {
        Button login_btn = (Button) findViewById(R.id.loginButton);
        login_btn.setClickable(false);
        //execute get and post
        new LoginGetIO().execute();
    }


    public class LoginGetIO extends AsyncTask<Void, Void, String> {

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

            EditText username_email = (EditText) findViewById(R.id.loginEmail);
            EditText password_login = (EditText) findViewById(R.id.loginPassword);

            String login_email = username_email.getText().toString();
            String login_pw = password_login.getText().toString();

            //HttpClient httpClient = new DefaultHttpClient();
            // replace with your url
            HttpPost httpPost = new HttpPost("http://172.17.20.216/penguin-carpool/public/login");
            //HttpPost httpPost = new HttpPost("http://172.17.31.169/penguin-carpool/public/login");
            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            nameValuePair.add(new BasicNameValuePair("email", login_email));
            nameValuePair.add(new BasicNameValuePair("password", login_pw));

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
                Log.d("Http Post repsonse IM ALIVE:", response.toString());
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
//            HttpGet httpGet = new HttpGet("http://10.0.2.2/penguin-carpool/public/login");
            //for phone
            HttpGet httpGet = new HttpGet("http://172.17.20.216/penguin-carpool/public/login");
            String text = null;

            try {
                HttpResponse response1 = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response1.getEntity();
                text = getASCIIContentFromEntity(entity);

                JSONObject text_obj = new JSONObject(text);
                String status = text_obj.getString("status");
                //save to use in the rest of the app
                firstname = text_obj.getString("0");
                lastname = text_obj.getString("1");
                email = text_obj.getString("2");
                id = text_obj.getInt("3");
                message = text_obj.getString("4");
                rating = text_obj.getInt("5");
                message_result = text_obj.getString("6");
                password = text_obj.getString("7");

                Log.d("ID",Integer.toString(id));
                //transition to home screen if credentials are valid
                if (status.equals("200")) {
                    startActivity(new Intent(LoginScreen.this, HomeScreen.class));
                }
                else {
                    //login error popup?
                }
                return text;

            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
        }

        protected void onPostExecute(String results) {
            Button login_btn = (Button) findViewById(R.id.loginButton);
            login_btn.setClickable(true);

        }


    }

    // Launch RegisterScreen from LoginScreen
    public void launchRegisterScreen(View v){
        startActivity(new Intent(LoginScreen.this, RegisterScreen.class));
    }

}