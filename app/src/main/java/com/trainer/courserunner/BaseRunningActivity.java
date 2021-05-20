package com.trainer.courserunner;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.trainer.courserunner.Application.enumtype.StartType;
import com.trainer.courserunner.course.activity.GuideRunnerActivity;
import com.trainer.courserunner.course.component.maker.CourseMaker;
import com.trainer.courserunner.course.component.maker.layer.line.LineConnectLayerDfsCustom;
import com.trainer.courserunner.course.component.maker.layer.quanzation.QuanzationMininumGuarantee;
import com.trainer.courserunner.course.component.maker.layer.regist.CourseRegistLayerAll;
import com.trainer.courserunner.course.component.maker.layer.selection.MarkerSelectionNone;
import com.trainer.courserunner.course.component.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.component.maker.scopetype.ScopeMapInfo;
import com.trainer.courserunner.loader.AssetLoader;

public abstract class BaseRunningActivity extends AppCompatActivity {
    protected abstract void lockWhenGetLocation();
    protected abstract void unlockWhenGetLocation();
}
