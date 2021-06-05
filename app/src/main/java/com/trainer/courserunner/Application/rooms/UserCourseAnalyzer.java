package com.trainer.courserunner.Application.rooms;

import com.trainer.courserunner.geo.DistanceConverter;

public class UserCourseAnalyzer {
    //m기준
    static public double getDistance(long userCourseId){
        UserCourseDetail[] userCourseDetails= AppDatabaseConnector.getAppDatabaseConnection()
                .userCourseDetailDao()
                .getUserCourseDetailWhereUserCourseIdOrder(userCourseId);
        if(userCourseDetails.length==0){
            return -1;
        }

        double fullDistance=0;
        for(int i=1;i<userCourseDetails.length;i++){
            double latitude1=userCourseDetails[i-1].userCourseRecordLatitude;
            double longitude1=userCourseDetails[i-1].userCourseRecordLongitude;
            double latitude2=userCourseDetails[i].userCourseRecordLatitude;
            double longitude2=userCourseDetails[i].userCourseRecordLongitude;
            fullDistance+=DistanceConverter.getDistance(latitude1,longitude1,latitude2,longitude2);
        }
        return fullDistance;
    }

}
