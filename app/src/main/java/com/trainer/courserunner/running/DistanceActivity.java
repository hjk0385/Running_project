package com.trainer.courserunner.running;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.Application.enumtype.StartType;
import com.trainer.courserunner.R;
import com.trainer.courserunner.course.activity.GuideRunnerActivity;
import com.trainer.courserunner.course.component.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.course.component.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.course.component.maker.scopetype.ScopeMapInfo;
import com.trainer.courserunner.map.drawer.NavermapActivity;
import com.trainer.courserunner.map.drawer.NavermapLocationActivity;
import com.trainer.courserunner.running.RunningNextDataInterface;

public class DistanceActivity extends NavermapLocationActivity implements RunningNextDataInterface {
    private Integer drawableId;
    private ScopeMapInfo scopeMapInfo=null;
    private Object overlayScopeMap=null;

    private void drawOverlayScopeMap(){
        if(overlayScopeMap!=null){
            this.clearDraw(overlayScopeMap);
        }
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        DrawingPath.Builder builder=new DrawingPath.Builder();
        builder.accept(new DrawingAddress(scopeMapInfo.getStartX(),scopeMapInfo.getStartY()));
        builder.accept(new DrawingAddress(scopeMapInfo.getStartX(),scopeMapInfo.getEndY()));
        builder.accept(new DrawingAddress(scopeMapInfo.getEndX(),scopeMapInfo.getEndY()));
        builder.accept(new DrawingAddress(scopeMapInfo.getEndX(),scopeMapInfo.getStartY()));
        builder.accept(new DrawingAddress(scopeMapInfo.getStartX(),scopeMapInfo.getStartY()));
        builder.setColor(Color.BLACK);
        builder.setWidth(10);
        DrawingPath drawingPath = builder.build();
        drawOverlayPolyline(drawingPath);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);

        SeekBar seekBar = findViewById(R.id.seekbar_distance);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //시크바 이동중
                drawOverlayScopeMap();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //조작 시작

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //조작 마무리

            }
        });

        Intent intent = getIntent();
        drawableId = intent.getIntExtra("drawableId",-1);



    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);
        Toast.makeText(this,"terta",Toast.LENGTH_LONG);
        naverMap.addOnLocationChangeListener((Location location)->{
            scopeMapInfo=ScopeMapInfo.makeScopeMapInfoOriginLeftDown(location.getLatitude(),
                    location.getLongitude(),
                    10);
        });
    }


    @Override
    public void nextActivity(SettingRunning settingRunning) {
        Intent intent = new Intent(getBaseContext(), GuideRunnerActivity.class);
        //intent.putExtra("courseId", (Long)sendData);
        //intent.putExtra("startType", StartType.NEW);
        startActivity(intent);
    }
}