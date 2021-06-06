package com.trainer.courserunner.Application.rooms;

import android.util.Log;

import androidx.core.util.Pair;

import com.trainer.courserunner.geo.DistanceConverter;

import java.util.Date;

public class UserCourseAnalyzer {
    //m기준
    static public double getDistance(long userCourseId) {
        UserCourseDetail[] userCourseDetails = AppDatabaseConnector.getAppDatabaseConnection()
                .userCourseDetailDao()
                .getUserCourseDetailWhereUserCourseIdOrder(userCourseId);
        if (userCourseDetails.length == 0) {
            return -1;
        }

        double fullDistance = 0;
        for (int i = 1; i < userCourseDetails.length; i++) {
            double latitude1 = userCourseDetails[i - 1].userCourseRecordLatitude;
            double longitude1 = userCourseDetails[i - 1].userCourseRecordLongitude;
            double latitude2 = userCourseDetails[i].userCourseRecordLatitude;
            double longitude2 = userCourseDetails[i].userCourseRecordLongitude;
            fullDistance += DistanceConverter.getDistance(latitude1, longitude1, latitude2, longitude2);
        }
        return fullDistance;
    }

    static public Pair<Date, Date> getStartEndTime(long userCourseId) {
        UserCourseDetail[] userCourseDetails = AppDatabaseConnector.getAppDatabaseConnection()
                .userCourseDetailDao()
                .getUserCourseDetailWhereUserCourseIdOrder(userCourseId);
        if (userCourseDetails.length == 0) {
            return null;
        }

        Date startDate = userCourseDetails[0].userCourseRecordDate;
        Date endDate = userCourseDetails[userCourseDetails.length - 1].userCourseRecordDate;
        return new Pair<>(startDate, endDate);
    }

    static public Double allExerciseDistance() {
        UserCourse[] userCourses = AppDatabaseConnector.getAppDatabaseConnection()
                .userCourseDao()
                .getAllUserCourse();
        double distances = 0;
        for (int i = 0; i < userCourses.length; i++) {
            distances += getDistance(userCourses[i].userCourseId);
        }
        return distances;
    }

    static public Long allExersiceTime() {
        UserCourse[] userCourses = AppDatabaseConnector.getAppDatabaseConnection()
                .userCourseDao()
                .getAllUserCourse();
        long exerciseTimes = 0;
        for (int i = 0; i < userCourses.length; i++) {
            Pair<Date, Date> dateStartEndPair = getStartEndTime(userCourses[i].userCourseId);
            long exerciseTime = DateConverters.dateToTimestamp(dateStartEndPair.second) - DateConverters.dateToTimestamp(dateStartEndPair.first);
            exerciseTimes += exerciseTime;
        }
        return exerciseTimes;

    }

    static public Double getStartEndTimeDistance(Date prevDate, Date nextDate) {
        UserCourse[] userCourses = AppDatabaseConnector.getAppDatabaseConnection()
                .userCourseDao()
                .getAllUserCourse();
        double distances = 0;
        for (int i = 0; i < userCourses.length; i++) {
            Pair<Date, Date> dateStartEndPair = getStartEndTime(userCourses[i].userCourseId);
            long startTime=prevDate.getTime();
            long endTime=nextDate.getTime();

            long selectStartTime=dateStartEndPair.first.getTime();
            long selectEndTime=dateStartEndPair.second.getTime();
            if (selectStartTime<startTime&&startTime<selectEndTime) {
                distances += getDistance(userCourses[i].userCourseId);
            }
        }
        return distances;
    }

    static public Long getStartEndTimeExercise(Date prevDate, Date nextDate) {
        UserCourse[] userCourses = AppDatabaseConnector.getAppDatabaseConnection()
                .userCourseDao()
                .getAllUserCourse();
        long exerciseTimes = 0;
        for (int i = 0; i < userCourses.length; i++) {
            Pair<Date, Date> dateStartEndPair = getStartEndTime(userCourses[i].userCourseId);
            long startTime=prevDate.getTime();
            long endTime=nextDate.getTime();

            long selectStartTime=dateStartEndPair.first.getTime();
            long selectEndTime=dateStartEndPair.second.getTime();

            Log.v("TEST",String.valueOf(startTime));
            Log.v("TEST",String.valueOf(endTime));
            Log.v("TEST",String.valueOf(selectStartTime));
            Log.v("TEST",String.valueOf(selectEndTime));


            if (selectStartTime<startTime&&startTime<selectEndTime) {
                long exerciseTime = DateConverters.dateToTimestamp(dateStartEndPair.second) - DateConverters.dateToTimestamp(dateStartEndPair.first);
                exerciseTimes += exerciseTime;
            }
        }
        return exerciseTimes;

    }

}
