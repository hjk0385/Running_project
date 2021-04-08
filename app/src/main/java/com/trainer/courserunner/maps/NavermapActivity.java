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
import com.trainer.courserunner.scopetype.ScopeDotAddress;

import java.util.ArrayList;
import java.util.List;

public class NavermapActivity extends AppCompatActivity implements OnMapReadyCallback, MapDrawer {
    protected NaverMap naverMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navermap);
        // 그리기 API 활용을 위한 NaverMap 가져오기
        FragmentManager fragmentManager = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.navermap_fragment);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.navermap_fragment, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        // 완료 -> onMapReady
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
    }

    @Override
    public Object drawMarker(ScopeDotAddress address, Consumer<Object> property) {
        //setting
        double longitude = address.getLongitude();
        double latitude = address.getLatitude();
        //draw
        Marker marker = new Marker();
        marker.setPosition(new LatLng(latitude, longitude));
        //속성
        property.accept(marker);
        marker.setMap(this.naverMap);
        return marker;
    }

    @Override
    public Object drawPolyline(List<ScopeDotAddress> addressList, Consumer<Object> property) {
        //setting
        List<LatLng> lngList = new ArrayList<>();
        for (ScopeDotAddress address : addressList) {
            double longitude = address.getLongitude();
            double latitude = address.getLatitude();
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
    public Object drawPath(List<ScopeDotAddress> addressList, Consumer<Object> property) {
        //setting
        List<LatLng> lngList = new ArrayList<>();
        for (ScopeDotAddress address : addressList) {
            double longitude = address.getLongitude();
            double latitude = address.getLatitude();
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
    public Object drawPath(List<ScopeDotAddress> addressList) {
        return drawPolyline(addressList, (Object object) -> {
            ((PolylineOverlay) object).setColor(Color.BLACK);
        });
    }

    @Override
    public Object drawRemainPath(List<ScopeDotAddress> addressList) {
        return drawPolyline(addressList, (Object object) -> {
            ((PolylineOverlay) object).setColor(Color.RED);
        });
    }

    @Override
    public Object drawPassedPath(List<ScopeDotAddress> addressList) {
        return drawPolyline(addressList, (Object object) -> {
            ((PolylineOverlay) object).setColor(Color.BLUE);
        });
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