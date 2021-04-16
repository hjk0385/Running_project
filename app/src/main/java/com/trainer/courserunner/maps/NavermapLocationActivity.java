package com.trainer.courserunner.maps;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.util.FusedLocationSource;

public abstract class NavermapLocationActivity extends NavermapActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    protected FusedLocationSource locationSource;
    protected double userLongitude;
    protected double userLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
        //naverMap.addOnLocationChangeListener(this::onLocationChangeListener);
    }
/*
    //위치변동시마다 호출될 메소드
    public void onLocationChangeListener(Location location) {
        this.userLatitude = location.getLatitude();
        this.userLongitude = location.getLongitude();
    }
*/
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) { // 권한 거부됨
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }
}
