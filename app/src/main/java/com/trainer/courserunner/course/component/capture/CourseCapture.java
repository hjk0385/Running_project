package com.trainer.courserunner.course.component.capture;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

import com.jraska.falcon.Falcon;
import com.trainer.courserunner.course.component.CourseComponent;

import java.io.File;

public class CourseCapture extends CourseComponent {
    String screenshotBareboneUrl;
    String screenshotName;

    Context context;

    public CourseCapture(Long userCourseId, Context context){
        this.context=context;
        screenshotBareboneUrl = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString()+"/trainer/";
        File folder;

        folder = new File(screenshotBareboneUrl);
        if(!folder.exists()){
            folder.mkdirs();
        }
        screenshotBareboneUrl = screenshotBareboneUrl + "capture/";
        folder=new File(screenshotBareboneUrl);
        if(!folder.exists()){
            folder.mkdirs();
        }
    }


    public void setScreenshotName(String screenshotName){
        this.screenshotName=screenshotName;
    }

    @Override
    protected Object runInWorkThread() {
        return null;
    }

    @Override
    protected void runInUiThread(Object object) {
        super.runInUiThread(object);
        //File file=new File(screenshotBareboneUrl+screenshotName+".png");
        //Falcon.takeScreenshot((Activity) context,file);
    }
}
