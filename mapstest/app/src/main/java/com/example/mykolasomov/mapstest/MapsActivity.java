package com.example.mykolasomov.mapstest;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Locale;


public class MapsActivity extends FragmentActivity {

    //private String distance1;
    // private double DoubleDistance = 0;
    // private String distance;


    private Document document;
    private Document document2;
    private GMapV2Direction v2GetRouteDirection;
    private GMapV2Direction v2GetRouteDirection2;
    public LatLng fromPosition;
    public LatLng toPosition;
    public LatLng detour;
    public String loc = RequestScreen.str_loc;
    public String dest = RequestScreen.str_dec;
    private GoogleMap mGoogleMap;
    private MarkerOptions markerOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        v2GetRouteDirection = new GMapV2Direction();
        v2GetRouteDirection2 = new GMapV2Direction();
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mGoogleMap = supportMapFragment.getMap();

        // Enabling MyLocation in Google Map

        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.getUiSettings().setCompassEnabled(true);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.getUiSettings().setAllGesturesEnabled(true);
        //mGoogleMap.setTrafficEnabled(true);
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        markerOptions = new MarkerOptions();

//        fromPosition = drawPoint(loc);
//        toPosition = drawPoint(dest);
//         detour = null;
        //detour = drawPoint("40 wilson street hamilton ontario");
        if(JoinScreen.d!=null){;
            fromPosition = drawPoint(JoinScreen.d);
            toPosition = drawPoint(dest);
            detour = drawPoint(loc);
        }
        else {
            fromPosition = drawPoint(loc);
            toPosition = drawPoint(dest);
            detour = null;
        }

        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fromPosition, 10));
        GetRouteTask getRoute = new GetRouteTask();
        getRoute.execute();
    }

    private class GetRouteTask extends AsyncTask<String, Void, String> {

        private ProgressDialog Dialog;
        String response = "";
        @Override
        protected void onPreExecute() {
            Dialog = new ProgressDialog(MapsActivity.this);
            Dialog.setMessage("Loading route...");
            Dialog.show();

        }

        @Override
        protected String doInBackground(String... urls) {

                if (detour != null) {
                    document = v2GetRouteDirection.getDocument(fromPosition, detour, GMapV2Direction.MODE_DRIVING);
                    document2 = v2GetRouteDirection2.getDocument(detour, toPosition, GMapV2Direction.MODE_DRIVING);
                } else {
                    document = v2GetRouteDirection.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
                }
                return response = "Success";
        }



        @Override
        protected void onPostExecute(String result) {

            mGoogleMap.clear();
            if(response.equalsIgnoreCase("Success")){
                if (detour == null)
                {
                    ArrayList<LatLng> directionPoint = v2GetRouteDirection.getDirection(document);
                    PolylineOptions rectLine = new PolylineOptions().width(10).color(
                            Color.RED);

                    for (int i = 0; i < directionPoint.size(); i++) {
                        rectLine.add(directionPoint.get(i));
                    }
                    // Adding route on the map
                    mGoogleMap.addPolyline(rectLine);
                    markerOptions.position(fromPosition);
                    markerOptions.draggable(true);
                    markerOptions.title("Starting Location");
                    mGoogleMap.addMarker(markerOptions);
                    markerOptions.position(toPosition);
                    markerOptions.draggable(true);
                    markerOptions.title("Destination");
                    markerOptions.snippet(v2GetRouteDirection.getDistanceText(document)+ "   " + v2GetRouteDirection.getDurationText(document));
                    mGoogleMap.addMarker(markerOptions);

                    Intent data = new Intent();
                    float distance = Float.parseFloat(v2GetRouteDirection.getDistanceText(document).split("\\s+")[0]);
                    data.putExtra("distance", distance);
                    data.putExtra("destination", dest);
                    data.putExtra("totalD",distance);

                    setResult(RESULT_OK, data);
                }
                else
                {
                    ArrayList<LatLng> directionPoint = v2GetRouteDirection.getDirection(document);
                    PolylineOptions rectLine = new PolylineOptions().width(10).color(
                            Color.RED);

                    for (int i = 0; i < directionPoint.size(); i++) {
                        rectLine.add(directionPoint.get(i));
                    }
                    // Adding route on the map
                    mGoogleMap.addPolyline(rectLine);
                    markerOptions.position(fromPosition);
                    markerOptions.draggable(true);
                    markerOptions.title("Starting Location");
                    mGoogleMap.addMarker(markerOptions);
                    markerOptions.position(detour);
                    markerOptions.draggable(true);
                    markerOptions.title("Detour");
                    markerOptions.snippet(v2GetRouteDirection.getDistanceText(document)+ "   " + v2GetRouteDirection.getDurationText(document));
                    mGoogleMap.addMarker(markerOptions);
                    float distance1 = Float.parseFloat(v2GetRouteDirection.getDistanceText(document).split("\\s+")[0]);
                    float time1 = Float.parseFloat(v2GetRouteDirection.getDurationText(document).split("\\s+")[0]);
                    directionPoint = v2GetRouteDirection2.getDirection(document2);
                    for (int i = 0; i < directionPoint.size(); i++) {
                        rectLine.add(directionPoint.get(i));
                    }
                    mGoogleMap.addPolyline(rectLine);
                    markerOptions.position(toPosition);
                    markerOptions.draggable(true);
                    markerOptions.title("Destination");
                    float distance2 = Float.parseFloat(v2GetRouteDirection2.getDistanceText(document2).split("\\s+")[0]);
                    float time2 = Float.parseFloat(v2GetRouteDirection2.getDurationText(document2).split("\\s+")[0]);
                    markerOptions.snippet(String.valueOf(distance1+distance2) + " km  " + String.valueOf(time1+time2) + " mins");
                    mGoogleMap.addMarker(markerOptions);

                    float totalD = distance1+distance2;

                    //send back total distance
                    Intent data = new Intent();
                    data.putExtra("distance", distance2);
                    data.putExtra("destination", dest);
                    data.putExtra("totalD",totalD);

                    setResult(RESULT_OK, data);
                }
            }



            Dialog.dismiss();
            //TextView dist = (TextView) findViewById(R.id.distance);
            //dist.setText(String.format("Traveled km.\n %f",DoubleDistance));
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private LatLng drawPoint(String addressName)
    {
        Context con = this;
        Geocoder geocoder = new Geocoder(con, Locale.getDefault());
        try {
            Address addr = geocoder.getFromLocationName(addressName, 1).get(0);
            LatLng point1 = new LatLng(addr.getLatitude(),
                    addr.getLongitude());

            mGoogleMap.addMarker(new MarkerOptions()
                    .position(point1)
                    .title(addr.getAddressLine(0))
                    .snippet("this is x km away"));

            mGoogleMap.setMyLocationEnabled(true);

            mGoogleMap.addMarker(new MarkerOptions()
                    .title("You are Here")
                    .position(point1));

            return point1;
        }catch (Exception e)
        {
            Log.e("MYAPP", "exception: " + e.getMessage());
            Log.e("MYAPP", "exception: " + e.toString());
            return null;
        }
    }



}