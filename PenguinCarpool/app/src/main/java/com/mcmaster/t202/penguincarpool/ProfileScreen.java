package com.mcmaster.t202.penguincarpool;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;


public class ProfileScreen extends LoginScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        TextView textView = (TextView) findViewById(R.id.userFirstName);
        TextView textView1 = (TextView) findViewById(R.id.userLastName);
        TextView textView2 = (TextView) findViewById(R.id.userEmail);
        RatingBar rating = (RatingBar) findViewById(R.id.userRatingAve);

        textView.setText(firstname);
        textView1.setText(lastname);
        textView2.setText(email);
        //hack for now. come back to it and add a real rating system
        rating.setRating(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_profile, menu);
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
