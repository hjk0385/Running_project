package com.trainer.courserunner.course.component.timelaps;

import android.os.AsyncTask;
import android.util.Log;

import com.trainer.courserunner.Application.AppFunctionLoader;
import com.trainer.courserunner.course.component.CourseComponent;
import com.trainer.courserunner.course.component.drawer.CourseDrawer;
import com.trainer.courserunner.course.component.drawer.CourseDrawerPolyline;
import com.trainer.courserunner.course.component.drawer.CourseDrawerUserRecord;
import com.trainer.courserunner.course.component.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.course.component.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.UserCourse;
import com.trainer.courserunner.rooms.UserCourseRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourseTimelaps {
    UserCourseRecord[] userLocationloadRecords;
    List<Object> overlayObjs;
    long userCourseId;
    long currentNumber;
    MapDrawer mapDrawer;
    public CourseTimelaps(long usercourseId,MapDrawer mapDrawer){
        this.userCourseId=usercourseId;
        this.currentNumber=0;
        this.mapDrawer=mapDrawer;
        this.overlayObjs=new ArrayList<>();
        userLocationloadRecords=null;
    }

    public void execute(){
        CourseTimelapsStarter courseTimelapsStarter= new CourseTimelapsStarter();
        courseTimelapsStarter.setFinishEventConsumer(o -> {
            new CourseTimelapsTimer().execute();
        });
        courseTimelapsStarter.runComponent();
    }

    class CourseTimelapsTimer extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            while(true){
                try {
                    currentNumber = (currentNumber + 1) % userLocationloadRecords.length;
                }
                catch(ArithmeticException ex){
                    currentNumber = 1;
                }
                publishProgress();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Object[] objects=Arrays.stream(userLocationloadRecords).limit(currentNumber).toArray();
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

            if (overlayObjs != null) {
                for (Object overlayObj : overlayObjs) {
                    mapDrawer.clearDraw(overlayObj);
                }
                overlayObjs.clear();
            }

            for (DrawingPath drawingPath : drawingPathList) {
                if (drawingPath.size() >= 2) {
                    overlayObjs.add(mapDrawer.drawOverlayPolyline(drawingPath));
                }
            }
        }
    }

    class CourseTimelapsStarter extends CourseComponent{
        @Override
        protected Object runInWorkThread() {
            userLocationloadRecords = AppFunctionLoader.getAppDatabase().userCourseRecordDao().getUserLocationRecords(userCourseId);
            return null;
        }
    }
}
