package com.emart.emartindia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/*
 * @author Bipin Singh
 * @author Bibek Kr Sah
 */


public class ContactActivity extends BaseNavigation implements OnMapReadyCallback {

    public static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    public MapView mMapView;
    EditText name, email, subject, message;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Contact");
        overridePendingTransition(R.transition.fadein, R.transition.fadeout);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_contact, null, false);

        frameLayout.addView(view);


        name = findViewById(R.id.editTextname);
        email = findViewById(R.id.editTextTextEmailAddress);
        subject = findViewById(R.id.editTextTextPersonSubject);
        message = findViewById(R.id.editTextTextMultiLine);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }
        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng mylocation = new LatLng(30.249885, 77.041877);
        map.addMarker(new MarkerOptions().position(mylocation).title("eMart"));
        float zoomLevel = 18.0f;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation, zoomLevel));
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void sendFeedback(View view) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    BasicAWSCredentials credentials = new BasicAWSCredentials("AKIA5BBOQKQGX2SIUSGZ", "IS+9EU57wMqLWUxpvw7IJZHfWV5p3fl3M7usXap1");


                    AmazonSimpleEmailServiceClient sesClient = new AmazonSimpleEmailServiceClient(credentials);


                    sesClient.setRegion(Region.getRegion(Regions.AP_SOUTH_1));


                    String title = "eMart Contact - " + name.getText();

                    String msgbody = "Sender Name: " + name.getText() + "\n" +
                            "Sender Email: " + email.getText() + "\n" +
                            "Subject: " + subject.getText() + "\n" +
                            "Message: " + message.getText();

                    Content subjectContent = new Content(title);
                    Body messageBody = new Body(new Content(msgbody));
                    Message feedbackMessage = new Message(subjectContent, messageBody);
                    Destination destination = new Destination().withToAddresses("bipinsingh130100@gmail.com");

                    SendEmailRequest request = new SendEmailRequest("bipinsingh130100@gmail.com", destination, feedbackMessage);
                    final SendEmailResult result = sesClient.sendEmail(request);

                    System.out.println("Result " + result);


                } catch (Exception e) {
                    System.out.println("" + e);
                    Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
                }


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(getApplicationContext(), "Details Submitted. Stay Tuned!", Toast.LENGTH_LONG).show();
                        name.setText("");
                        email.setText("");
                        subject.setText("");
                        message.setText("");


                    }
                });
            }


        });


        thread.start();


    }
}