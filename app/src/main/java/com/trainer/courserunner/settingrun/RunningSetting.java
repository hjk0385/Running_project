package com.trainer.courserunner.settingrun;

import android.graphics.Bitmap;

import com.trainer.courserunner.course.component.maker.scopetype.ScopeMapInfo;

import java.io.Serializable;

public class RunningSetting implements Serializable {
    Bitmap bitmap;
    ScopeMapInfo scopeMapInfo;

    public void setScopeMapInfo(ScopeMapInfo scopeMapInfo) {
        this.scopeMapInfo = scopeMapInfo;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public ScopeMapInfo getScopeMapInfo() {
        return scopeMapInfo;
    }
}
