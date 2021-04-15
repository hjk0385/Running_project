package com.trainer.courserunner.maps;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.overlay.PolylineOverlay;
import com.trainer.courserunner.R;
import com.trainer.courserunner.drawtype.DrawingAddress;
import com.trainer.courserunner.drawtype.DrawingPath;
import com.trainer.courserunner.scopetype.ScopeDotAddress;

import java.util.ArrayList;
import java.util.List;

public abstract class NavermapActivity extends AppCompatActivity implements OnMapReadyCallback, MapDrawer {
    protected NaverMap naverMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navermap);
        settingNavermap();
    }

    private void settingNavermap(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.navermap_fragment);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.navermap_fragment, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    



    @Override
    public void clearDraw(Object drawObject) {
        if (drawObject instanceof Marker) {
            ((Marker) drawObject).setMap(null);
        } else if (drawObject instanceof PolylineOverlay) {
            ((PolylineOverlay) drawObject).setMap(null);
        } else if (drawObject instanceof PathOverlay) {
            ((PathOverlay) drawObject).setMap(null);
        }
    }
}