package com.trainer.courserunner.runactivity.record;

import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.Application.AppFunctionLoader;
import com.trainer.courserunner.Application.mapdrawer.MapDrawer;
import com.trainer.courserunner.Application.rooms.UserCourseRecord;
import com.trainer.courserunner.R;
import com.trainer.courserunner.component.drawer.CourseDrawerPolyline;
import com.trainer.courserunner.component.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.component.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.Application.mapdrawer.NavermapActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TimelapsActivity extends NavermapActivity {
    MapDrawer mapDrawer;

    long userCourseId;
    UserCourseRecord[] userLocationloadRecords;

    TimelapsRunner timelapsRunner;
    ScheduledExecutorService scheduledExecutorService;

    final long defaultMiliseconds=1000;
    double currentMultiple=1;

    private long currentTimelapsMiliseconds(){
        return (long)((double)defaultMiliseconds*currentMultiple);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);
        userCourseId=getIntent().getLongExtra("userCourseId",-1);
        if(userCourseId==-1){
            throw new IllegalArgumentException();
        }
        mapDrawer = this;
        //
        new TimelapsActivityStarter().execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimelaps();
    }

    public void runTimelaps(){
        if(scheduledExecutorService!=null){
            stopTimelaps();
        }
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(timelapsRunner, 0, currentTimelapsMiliseconds(), TimeUnit.MILLISECONDS);
    }

    public void stopTimelaps(){
        timelapsRunner.clearOverlay();
        scheduledExecutorService.shutdown();
        scheduledExecutorService=null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.timelaps_speed_05:
                currentMultiple=2;
                break;
            case R.id.timelaps_speed_1:
                currentMultiple=1;
                break;
            case R.id.timelaps_speed_2:
                currentMultiple=0.5;
                break;
            case R.id.timelaps_speed_3:
                currentMultiple=0.3;
                break;
            case R.id.timelaps_speed_4:
                currentMultiple=0.25;
                break;
            default:
                break;
        }
        stopTimelaps();
        runTimelaps();
        return super.onOptionsItemSelected(item);
    }

    class TimelapsActivityStarter extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            userLocationloadRecords=AppFunctionLoader
                    .getAppDatabase()
                    .userCourseRecordDao()
                    .getUserLocationRecords(userCourseId);
            timelapsRunner=new TimelapsRunner(mapDrawer);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            runTimelaps();
        }
    }

    class TimelapsRunner extends CourseDrawerPolyline implements Runnable{
        int currentNumber;
        int maxNumber;

        public TimelapsRunner(MapDrawer mapDrawer) {
            super(mapDrawer);
            this.maxNumber=userLocationloadRecords.length;
            this.currentNumber=0;
        }

        @Override
        protected List<DrawingPath> makeDrawing() {
            currentNumber=(currentNumber+1)%maxNumber;

            Object[] objects= Arrays.stream(userLocationloadRecords).limit(currentNumber).toArray();
            UserCourseRecord[] userLocationRecords = new UserCourseRecord[objects.length];
            for(int i=0;i<objects.length;i++){
                userLocationRecords[i]=(UserCourseRecord) objects[i];
            }

            //생성
            List<DrawingPath> drawingPathList = new ArrayList<>();
            int i = 0;
            while (i < userLocationRecords.length) {
                Integer currentDrawingColor = userLocationRecords[i].userCourseRecordColor;
                //빌더
                DrawingPath.Builder drawingPathBuilder = new DrawingPath.Builder();
                drawingPathBuilder.setColor(currentDrawingColor);
                drawingPathBuilder.setWidth(15);

                if (i > 0) {
                    drawingPathBuilder.accept(new DrawingAddress(userLocationRecords[i - 1]));
                }
                //컬러 경로 만들기
                int j = i;
                while (j < userLocationRecords.length && Objects.equals(currentDrawingColor, userLocationRecords[j].userCourseRecordColor)) {
                    drawingPathBuilder.accept(new DrawingAddress(userLocationRecords[j]));
                    j++;
                }
                drawingPathList.add(drawingPathBuilder.build());
                i++;
            }
            return drawingPathList;
        }

        @Override
        public void run() {
            this.runComponent();
        }
    }
}
