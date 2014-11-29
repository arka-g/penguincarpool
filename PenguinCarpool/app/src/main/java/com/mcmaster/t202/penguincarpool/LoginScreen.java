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


    // Popup to be called in case of Login Error [TEST]
    //public class msgLoginError extends Activity implements View.OnClickListener{
    //    @Override
    //    public void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_login_screen);
    //    }
    //    public void onClick(View v) {}
    //    public void launchHomeScreen(View v) {
    //        new AlertDialog.Builder(this)
    //                .setTitle("Login Error")
    //                .setMessage("You don' goofed... Please try again.")
    //                .setNeutralButton("Ok", null)
    //                .show();
    //    }
    //}


    // Launch HomeScreen if email and password fields are nonempty (CHANGE)
    public void launchHomeScreen(View v){

        Integer emailField = ((EditText) findViewById(R.id.loginEmail)).getText().toString().trim().length();
        Integer passwordField = ((EditText) findViewById(R.id.loginPassword)).getText().toString().trim().length();

        if(emailField != 0 & passwordField != 0) {
            startActivity(new Intent(LoginScreen.this, HomeScreen.class));
        }
        else{

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