package com.trainer.courserunner.running.normal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.trainer.courserunner.Application.enumtype.StartType;
import com.trainer.courserunner.R;
import com.trainer.courserunner.course.activity.GuideRunnerActivity;
import com.trainer.courserunner.course.component.maker.CourseMaker;
import com.trainer.courserunner.course.component.maker.layer.line.LineConnectLayerDfsCustom;
import com.trainer.courserunner.course.component.maker.layer.quanzation.QuanzationMininumGuarantee;
import com.trainer.courserunner.course.component.maker.layer.regist.CourseRegistLayerAll;
import com.trainer.courserunner.course.component.maker.layer.selection.MarkerSelectionNone;
import com.trainer.courserunner.course.component.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.component.maker.scopetype.ScopeMapInfo;
import com.trainer.courserunner.map.drawer.NavermapActivity;
import com.trainer.courserunner.map.drawer.NavermapLocationActivity;
import com.trainer.courserunner.running.RunningNextDataInterface;

public class NormalRunningDistanceActivity extends NavermapLocationActivity implements RunningNextDataInterface {
    private Location currentLocation;
    private Integer drawableId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_running_distance);
        Intent intent = getIntent();
        drawableId = intent.getIntExtra("drawableId",-1);
    }

    @Override
    public void nextActivity(Object sendData) {
        Intent intent = new Intent(getBaseContext(), GuideRunnerActivity.class);
        intent.putExtra("courseId", (Long)sendData);
        intent.putExtra("startType", StartType.NEW);
        startActivity(intent);
    }
}