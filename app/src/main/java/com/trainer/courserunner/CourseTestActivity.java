package com.trainer.courserunner;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.trainer.courserunner.coursesuggest.CourseSuggester;
import com.trainer.courserunner.coursesuggest.DotAddress;
import com.trainer.courserunner.managedata.MapDAO;
import com.trainer.courserunner.managedata.MapDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CourseTestActivity extends NavermapActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapDAO.initMapDB(this);
        MapDAO.initMapDB(this);

        double startx = 126.7687037;
        double starty = 37.4916138;
        double endx = 126.779899;
        double endy = 37.506515;
        //DB 테스트
        List<MapDTO> scopeAddress = MapDAO.getScopeAddress(startx, starty, endx, endy);
        for (MapDTO address : scopeAddress) {
            Log.v("DBLOG", String.valueOf(address.getX()) + " " + String.valueOf(address.getY()));
            //지도그리기(API이후)
            //drawMarker(new DotAddress(startx,starty,endx,endy,address.getX(),address.getY()));
        }
        //이미지 로드 및 코스 제안 테스트
        try {
            AssetManager assetManager = getResources().getAssets();
            InputStream inputStream = assetManager.open("testbitmap1.png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            List<DotAddress> dotAddressList = (new CourseSuggester(bitmap, startx, starty, endx, endy)).suggestPath();
            for (DotAddress address : dotAddressList) {
                Log.v("DBLOG2", String.valueOf(address.getX()) + " " + String.valueOf(address.getY()));
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
