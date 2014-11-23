package com.mcmaster.t202.penguincarpool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class HomeScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
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
    public void launchIdleScreen(View v){
        startActivity(new Intent(HomeScreen.this, IdleScreen.class));
    }
}
