package com.trainer.courserunner.settingrun;

import android.graphics.Bitmap;

import com.trainer.courserunner.course.component.maker.scopetype.ScopeMapInfo;

import java.io.Serializable;

public class RunningSetting implements Serializable {
    Integer drawableId;
    double latitude;
    double longitude;
    public Integer getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(Integer drawableId) {
        this.drawableId = drawableId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
