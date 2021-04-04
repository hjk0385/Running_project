package com.trainer.courserunner;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.naver.maps.map.OnMapReadyCallback;
import com.trainer.courserunner.courseguider.CourseOverseer;
import com.trainer.courserunner.coursesuggest.CourseSuggester;
import com.trainer.courserunner.coursesuggest.DotAddress;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class CourseGuideActivity extends NavermapActivity{
    CourseOverseer courseOverseer;
    CourseOverseerTasker courseOverseerTasker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //debug -> release intent
        try {
            double startx = 126.7687037;
            double starty = 37.4916138;
            double endx = 126.779899;
            double endy = 37.506515;
            AssetManager assetManager = getResources().getAssets();
            InputStream inputStream = assetManager.open("testbitmap1.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            CourseSuggester courseSuggester = new CourseSuggester(bitmap,startx,starty,endx,endy);
            List<DotAddress> addressList=courseSuggester.suggestPath();
            courseOverseer=new CourseOverseer(this,addressList);
            courseOverseerTasker=new CourseOverseerTasker();
            courseOverseerTasker.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        courseOverseer = new CourseOverseer(this, course);
        */
    }

    class CourseOverseerTasker extends AsyncTask<Void, Void, Void> {
        boolean active;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            active = true;
        }

        @Override
        protected Void doInBackground(Void... values) {
            while (active) {
                try {
                    Thread.sleep(1000);
                    publishProgress();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            //위치 새로고침
            courseOverseer.refresh(userLongitude,userLatitude);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            active = false;
        }
    }
    //권한 지정과 위치추적 설정 + 변경이벤트의 구현 및 그 리스너에서 현재 위치를 저장하는 변수의 지정의 구현

}
