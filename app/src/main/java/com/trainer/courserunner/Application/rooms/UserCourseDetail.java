package com.trainer.courserunner.Application.rooms;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;

import java.util.Date;

@DatabaseView("SELECT user_course.user_course_id, user_course.course_id, user_course.course_mode_id, user_course.user_course_name, " +
        " user_course_record.user_course_record_id, user_course_record.user_course_record_date" +
        " FROM user_course JOIN user_course_record ON user_course.user_course_id = user_course_record.user_course_id ")
public class UserCourseDetail {
    @ColumnInfo(name = "user_course_id")
    public Long userCourseId;
    @ColumnInfo(name = "user_course_name")
    public Long userCourseName;
    @ColumnInfo(name = "course_mode_id")
    public Long courseModeId;
    @ColumnInfo(name = "course_id")
    public Long courseId;


    @ColumnInfo(name = "user_course_record_id")
    public Long userCourseRecordId;
    @ColumnInfo(name = "user_course_record_date")
    public Date userCourseRecordDate;
}
