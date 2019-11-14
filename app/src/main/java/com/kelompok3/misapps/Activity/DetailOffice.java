package com.kelompok3.misapps.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.kelompok3.misapps.Model.OfficeModel;
import com.kelompok3.misapps.R;
import com.kelompok3.misapps.Util.CMapView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailOffice extends AppCompatActivity implements OnMapReadyCallback {

    OfficeModel model;

    @BindView(R.id.imgCompany)
    ImageView imgCompany;
    @BindView(R.id.txOfficeName)
    TextView txOfficeName;
    @BindView(R.id.txOfficeAddress)
    TextView txOfficeAddress;
    @BindView(R.id.txOfficeDesc)
    TextView txOfficeDesc;
    @BindView(R.id.btnCall)
    MaterialButton btnCall;
    @BindView(R.id.btnEmail)
    MaterialButton btnEmail;
    @BindView(R.id.mapView)
    CMapView mMapView;

    double latitude, longitude;

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_office);
        ButterKnife.bind(this);

        model = getIntent().getParcelableExtra("DATA_OFFICE");

        initGoogleMap(savedInstanceState);

        Picasso.get().load(model.getBase_url()).resize(500,500).into(imgCompany);
        txOfficeName.setText(model.getOffice_name());
        txOfficeAddress.setText(model.getOffice_address());
        txOfficeDesc.setText(model.getOffice_description());

        String[] latlong =  model.getLocation_gps().split(",");
        latitude = Double.parseDouble(latlong[0]);
        longitude = Double.parseDouble(latlong[1]);
    }

    @OnClick(R.id.btnCall)
    public void setBtnCall(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + model.getCell_phone()));
        startActivity(intent);
    }

    @OnClick(R.id.btnEmail)
    public void setBtnEmail(View view) {
        Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + model.getEmail()));
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(intent);
    }

    private void initGoogleMap(Bundle savedInstanceState){
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
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
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Office Location"));

        //Melakukan zoom
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude),15));
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
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
}
