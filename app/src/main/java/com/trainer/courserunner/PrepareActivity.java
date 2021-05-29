package com.trainer.courserunner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import java.security.Permission;

public class PrepareActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int PERMISSION_REQUEST_FIND_LOCATION=0;
    private static final int PERMISSION_REQUEST_INTERNET=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);




        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            requestPermissionAccessFindLocation();
        }
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET)!= PackageManager.PERMISSION_GRANTED){
            requestPermissionInternet();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PERMISSION_REQUEST_FIND_LOCATION){
            if(grantResults.length==0){
                finish();
            }
        }
        else if(requestCode==PERMISSION_REQUEST_INTERNET){
            if(grantResults.length==0){
                finish();
            }
        }
    }
    



    private void requestPermissionAccessFindLocation(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(getApplicationContext(),"이 앱은 위치권한이 필요합니다",Toast.LENGTH_LONG).show();
        }
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUEST_FIND_LOCATION);
    }

    private void requestPermissionInternet(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)){
            Toast.makeText(getApplicationContext(),"이 앱은 인터넷 권한이 필요합니다",Toast.LENGTH_LONG).show();
        }
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},PERMISSION_REQUEST_INTERNET);
    }

}


/*
    권한체크 -> 권한요구 -> 종료(권한없음)
            -> MainActivity 실행
    권한요구
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.INTERNET" />
    데이터베이스 불러오기
    roomsdb
    불러오지 않는 데이터베이스
    mapdb
*/
