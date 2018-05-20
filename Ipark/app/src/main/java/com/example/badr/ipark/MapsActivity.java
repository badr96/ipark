package com.example.badr.ipark;//com.example.badr.ipark

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int PLACE_PICKER_REQUEST = 1;

    Button loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        loc = (Button) findViewById(R.id.button1);
        mapFragment.getMapAsync(this);

        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent;
                try {
                    intent = builder.build(MapsActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);

                    System.out.println("start activity for result");
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
    }
       // mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        //onMapReady(mMap);
    //}


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng Paris = new LatLng(48.8534,2.3488);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Paris,12));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("onActivityResult");
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {

                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format("Home: %s", place.getAddress());
                //place.get
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                //TextView getLocation1 = (TextView)findViewById(R.id.getLocTV);
                loc.setText(toastMsg);
                String arr="";
                //arr =

                Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
                double lng = place.getLatLng().longitude;
                double lat =  place.getLatLng().latitude;
                List<Address> addresses = null;
                try {
                    addresses = gcd.getFromLocation(place.getLatLng().latitude, lng, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //if (addresses.size() > 0)
                //System.out.println(addresses.get(0).getLocality());



              //  getLocality.setText(addresses.get(0).getPostalCode());


                if (addresses.get(0).getPostalCode().equals("75001")){
                    mMap.clear();
                    LatLng sydney1 = new LatLng(48.86217258102777, 2.34151267069747);
                    LatLng sydney2 = new LatLng(48.861734530121126, 2.3514829595568068);
                    LatLng sydney3 = new LatLng(48.86606589219459, 2.336690679526799);
                    LatLng sydney4 = new LatLng(48.866030628355276, 2.336987475669955);
                    LatLng sydney5 = new LatLng(48.863797508867, 2.3398282343271877);
                    LatLng sydney6 = new LatLng(48.866498762595526, 2.3242509830427087);


                    mMap.addMarker(new MarkerOptions().position(sydney1).title("75001"));
                    mMap.addMarker(new MarkerOptions().position(sydney2).title("75001"));
                    mMap.addMarker(new MarkerOptions().position(sydney3).title("75001"));
                    mMap.addMarker(new MarkerOptions().position(sydney4).title("75001"));
                    mMap.addMarker(new MarkerOptions().position(sydney5).title("75001"));
                    mMap.addMarker(new MarkerOptions().position(sydney6).title("75001"));


                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney1,12));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney2,12));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney3,12));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney4,12));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney5,12));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney6,12));
                }
                else if(addresses.get(0).getPostalCode().equals("75002")){
                    mMap.clear();
                    LatLng paris = new LatLng(48.87006800494606, 2.350548323263452);
                    LatLng paris1 = new LatLng(48.870782154398604, 2.3469257137833983);
                    LatLng paris2 = new LatLng(48.869619070033984, 2.344380769199021);
                    LatLng paris3 = new LatLng(48.866550078711384, 2.3444107845966515);
                    LatLng paris4 = new LatLng(48.869208695028654, 2.3486472176677573);

                    mMap.addMarker(new MarkerOptions().position(paris).title("75002"));
                    mMap.addMarker(new MarkerOptions().position(paris1).title("75002"));
                    mMap.addMarker(new MarkerOptions().position(paris2).title("75002"));
                    mMap.addMarker(new MarkerOptions().position(paris3).title("75002"));
                    mMap.addMarker(new MarkerOptions().position(paris4).title("75002"));

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris4,12));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris3,12));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris2,12));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris1,12));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris,12));

                }
                else if (addresses.get(0).getPostalCode().equals("75013")){
                    mMap.clear();
                    LatLng sydney3 = new LatLng(48.826968094438584,  2.3652642576656);
                    mMap.addMarker(new MarkerOptions().position(sydney3).title("75013"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney3,12));
                }
                else if(addresses.get(0).getPostalCode().equals("75008")){
                    mMap.clear();
                    LatLng paris08_1 = new LatLng(48.871050466004434, 2.308375348617711);
                    LatLng paris08_2 = new LatLng(48.8650544818902, 2.307852699978849);
                    LatLng paris08_3 = new LatLng(48.86539036883078, 2.3012830110364986);
                    LatLng paris08_4 = new LatLng(48.86979682407124, 2.3011635620473863);
                    LatLng paris08_5 = new LatLng(48.873074831432795, 2.3053946050953433);

                    mMap.addMarker(new MarkerOptions().position(paris08_1).title("75008"));
                    mMap.addMarker(new MarkerOptions().position(paris08_2).title("75008"));
                    mMap.addMarker(new MarkerOptions().position(paris08_3).title("75008"));
                    mMap.addMarker(new MarkerOptions().position(paris08_4).title("75008"));
                    mMap.addMarker(new MarkerOptions().position(paris08_5).title("75008"));

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris08_1,12));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris08_2,12));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris08_3,12));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris08_4,12));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(paris08_5,12));


                }

            }
        }
    }
}
