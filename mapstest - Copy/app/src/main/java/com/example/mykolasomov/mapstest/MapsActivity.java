package com.example.mykolasomov.mapstest;


import android.graphics.Color;

import android.location.Geocoder;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.CameraUpdateFactory;

import android.content.Context;
import  	android.location.Address;

import android.util.Log;
import org.w3c.dom.Document;

import java.util.ArrayList;

import com.google.android.gms.maps.model.*;



import java.util.Locale;


import android.app.ProgressDialog;

import android.os.AsyncTask;


public class MapsActivity extends FragmentActivity {

    //private String distance1;
    // private double DoubleDistance = 0;
    // private String distance;


    private Document document;
    private Document document2;
    private GMapV2Direction v2GetRouteDirection;
    private GMapV2Direction v2GetRouteDirection2;
    private LatLng fromPosition;
    private LatLng toPosition;
    private LatLng detour;
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
        mGoogleMap.setTrafficEnabled(true);
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        markerOptions = new MarkerOptions();
        fromPosition = drawPoint("1280 main street west hamilton ontario");
        toPosition = drawPoint("1000 main street east hamilton ontario");
        detour = null;
        //detour = drawPoint("40 wilson street hamilton ontario");

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
            //Get All Route values
            if (detour !=null) {
                document = v2GetRouteDirection.getDocument(fromPosition, detour, GMapV2Direction.MODE_DRIVING);
                document2 = v2GetRouteDirection2.getDocument(detour, toPosition, GMapV2Direction.MODE_DRIVING);
            }
            else
                document = v2GetRouteDirection.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);


            response = "Success";
            return response;

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