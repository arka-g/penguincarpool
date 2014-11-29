package com.mcmaster.t202.penguincarpool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class LoginScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }


    @Override
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
    }

        @Override

        protected String doInBackground(Void... params) {

            EditText username_email = (EditText) findViewById(R.id.loginEmail);
            EditText password_login = (EditText) findViewById(R.id.loginPassword);

            String login_email = username_email.getText().toString();
            String login_pw = password_login.getText().toString();

            HttpClient httpClient = new DefaultHttpClient();
            // replace with your url
            HttpPost httpPost = new HttpPost("http://10.0.2.2/penguin-carpool/public/login");

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
            //verify login
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet("http://10.0.2.2/penguin-carpool/public/login");
            String text = null;
            try {
                HttpResponse response1 = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response1.getEntity();
                text = getASCIIContentFromEntity(entity);
                JSONObject text_obj = new JSONObject(text);
                String status = text_obj.getString("status");

                //transition to home screen if credentials are valid
                if (status.equals("200")) {
                    startActivity(new Intent(LoginScreen.this, HomeScreen.class));
                }
                else{
                }

            // Popup to be called in case of Login Error [TEST]
            class msgLoginError extends Activity implements View.OnClickListener{
                @Override
                public void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_login_screen);
                }
                public void onClick(View v) {}
                public void launchHomeScreen(View v) {
                    new AlertDialog.Builder(this)
                            .setTitle("Login Error")
                            .setMessage("You don' goofed... Please try again.")
                            .setNeutralButton("Ok", null)
                            .show();
                }
            }

            msgLoginError showLoginErrorMsg = new msgLoginError();
            showLoginErrorMsg.launchHomeScreen(v);
        }
    }

    // Launch RegisterScreen from LoginScreen
    public void launchRegisterScreen(View v){
        startActivity(new Intent(LoginScreen.this, RegisterScreen.class));
    }

}