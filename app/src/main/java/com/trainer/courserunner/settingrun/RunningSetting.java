package com.trainer.courserunner.settingrun;

import android.graphics.Bitmap;

import com.trainer.courserunner.course.component.maker.scopetype.ScopeMapInfo;

import java.io.Serializable;

public class RunningSetting implements Serializable {
    Integer drawableId;

    public Integer getDrawableId() {
        return drawableId;
    }
    public void setDrawableId(Integer drawableId) {
        this.drawableId = drawableId;
    }
}
