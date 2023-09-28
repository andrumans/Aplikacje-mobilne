package com.example.lab03intencje;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Geoactivity extends AppCompatActivity {

    private Button buttonstart, buttonstop;
    private TextView Widthtextview, Lengthtextview;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private boolean isLocationTracking = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geoactivity);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        buttonstart = findViewById(R.id.buttonstart);
        buttonstop = findViewById(R.id.buttonstop);
        Widthtextview = findViewById(R.id.Widthtextview);
        Lengthtextview = findViewById(R.id.Lengthtextview);

        // Sprawdzenie czy są nadane pozwolenia
        if (checkLocationPermission()) {
            setUpLocationListener();
        }

        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkLocationPermission()) {
                    if (!isLocationTracking) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
                        isLocationTracking = true;
                        buttonstart.setEnabled(false); // Wyłącz przycisk "Start"
                        buttonstop.setEnabled(true); // Włącz przycisk "Stop"
                    }
                }
            }
        });

        buttonstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLocationTracking) {
                    locationManager.removeUpdates(locationListener);
                    isLocationTracking = false;
                    buttonstart.setEnabled(true); // Włącz przycisk "Start"
                    buttonstop.setEnabled(false); // Wyłącz przycisk "Stop"
                    Widthtextview.setText("N/A");
                    Lengthtextview.setText("N/A");
                }
            }
        });
    }

    private boolean checkLocationPermission() {
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {

            // Request po uprawnienia
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            return false;
        }
        return true;
    }

    private void setUpLocationListener() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double szer = location.getLatitude();
                double dlg = location.getLongitude();
                Widthtextview.setText("" + szer);
                Lengthtextview.setText("" + dlg);
            }

//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//                Toast.makeText(Geoactivity.this, "Dostęp do lok. wyłączony", Toast.LENGTH_SHORT).show();
//            }
        };
    }
}
