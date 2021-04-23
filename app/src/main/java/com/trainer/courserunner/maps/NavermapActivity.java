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

public class NavermapActivity extends AppCompatActivity implements OnMapReadyCallback, MapDrawer {
    protected NaverMap naverMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navermap);
        settingNavermap();
    }

    private void settingNavermap() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.navermap_fragment);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.navermap_fragment, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
    }


    @Override
    public Object[] drawOverlayMarkers(DrawingPath drawingPath, Consumer<Object> property) {
        Object[] markerObjects = new Object[drawingPath.size()];
        for (int i = 0; i < drawingPath.size(); i++) {
            double latitude = drawingPath.get(i).getLatitude();
            double longitude = drawingPath.get(i).getLongitude();
            //marker
            Marker marker = new Marker();
            marker.setPosition(new LatLng(latitude, longitude));
            property.accept(marker);
            marker.setMap(this.naverMap);
            markerObjects[i] = marker;
        }
        return markerObjects;
    }

    @Override
    public Object drawOverlayPolyline(DrawingPath drawingPath, Consumer<Object> property) {
        //setting
        List<LatLng> lngList = new ArrayList<>();
        for (DrawingAddress drawingAddress : drawingPath) {
            double longitude = drawingAddress.getLongitude();
            double latitude = drawingAddress.getLatitude();
            lngList.add(new LatLng(latitude, longitude));
        }
        //draw
        PolylineOverlay polyline = new PolylineOverlay();
        polyline.setCoords(lngList);
        property.accept(polyline);
        polyline.setMap(naverMap);
        return polyline;
    }

    @Override
    public Object drawOverlayPathline(DrawingPath drawingPath, Consumer<Object> property) {
        //setting
        List<LatLng> lngList = new ArrayList<>();
        for (DrawingAddress drawingAddress : drawingPath) {
            double longitude = drawingAddress.getLongitude();
            double latitude = drawingAddress.getLatitude();
            lngList.add(new LatLng(latitude, longitude));
        }
        //draw
        PathOverlay pathOverlay = new PathOverlay();
        pathOverlay.setCoords(lngList);
        property.accept(pathOverlay);
        pathOverlay.setMap(naverMap);
        return pathOverlay;
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

    @Override
    public Consumer<Object> getLineColorProperty(int color) {
        return (Object drawObject) -> {
            if (drawObject instanceof PolylineOverlay) {
                ((PolylineOverlay) drawObject).setColor(color);
            } else if (drawObject instanceof PathOverlay) {
                ((PathOverlay) drawObject).setColor(color);
            }
        };
    }
}