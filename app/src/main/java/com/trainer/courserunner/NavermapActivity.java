package com.trainer.courserunner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.overlay.PolylineOverlay;
import com.trainer.courserunner.courseguider.MapDrawer;
import com.trainer.courserunner.coursesuggest.DotAddress;

import java.util.ArrayList;
import java.util.List;

public class NavermapActivity extends AppCompatActivity implements OnMapReadyCallback , MapDrawer {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navermap);

        //그리기 API 활용을 위한 NaverMap 가져오기
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.navermap_fragment);
        if(mapFragment==null){
            mapFragment=MapFragment.newInstance();
            fm.beginTransaction().add(R.id.navermap_fragment,mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
        //
    }

    private NaverMap naverMap;
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        //획득된 NaverMap를 클래스 객체에 저장
        this.naverMap=naverMap;
    }

    @Override
    public Object drawMarker(DotAddress address) {
        //setting
        double longitude=address.getLongitude();
        double latitude=address.getLatitude();
        //draw
        Marker marker = new Marker();
        marker.setPosition(new LatLng(latitude,longitude));
        marker.setMap(this.naverMap);
        return marker;
    }

    @Override
    public Object drawPolylineOverlay(List<DotAddress> addressList) {
        //setting
        List<LatLng> lngList=new ArrayList<>();
        for(DotAddress address:addressList){
            double longitude= address.getLongitude();
            double latitude= address.getLatitude();
            lngList.add(new LatLng(latitude,longitude));
        }
        //draw
        PolylineOverlay polyline = new PolylineOverlay();
        polyline.setCoords(lngList);
        polyline.setMap(naverMap);
        return polyline;
    }

    @Override
    public Object drawPathOverleay(List<DotAddress> addressList) {
        //setting
        List<LatLng> lngList=new ArrayList<>();
        for(DotAddress address:addressList){
            double longitude= address.getLongitude();
            double latitude= address.getLatitude();
            lngList.add(new LatLng(latitude,longitude));
        }
        //draw
        PathOverlay pathOverlay = new PathOverlay();
        pathOverlay.setCoords(lngList);
        pathOverlay.setMap(naverMap);
        return pathOverlay;
    }

    @Override
    public void clearDraw(Object drawObject) {
        if(drawObject instanceof Marker){
            ((Marker) drawObject).setMap(null);
        }
        else if(drawObject instanceof PolylineOverlay){
            ((PolylineOverlay) drawObject).setMap(null);
        }
        else if(drawObject instanceof PathOverlay){
            ((PathOverlay) drawObject).setMap(null);
        }
    }
}