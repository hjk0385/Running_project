package com.trainer.courserunner.course;

import android.location.Location;
import android.os.Bundle;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.trainer.courserunner.managedata.MapDAO;
import com.trainer.courserunner.maps.NavermapLocationActivity;


public class CourseGuideActivity extends NavermapLocationActivity {
    CourseOverseer courseOverseer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DB초기화
        MapDAO.initMapDB(this);
    }

    @Override
    public void onMapReady() {
        CameraUpdate cameraUpdate = CameraUpdate.toCameraPosition(new CameraPosition(new LatLng(this.userLatitude, this.userLongitude), 10));
        naverMap.moveCamera(cameraUpdate);


    }


    @Override
    public void onLocationChangeListener(Location location) {
        super.onLocationChangeListener(location);
    }
}
