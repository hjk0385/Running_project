package com.trainer.courserunner.rooms;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.util.Date;

@Entity(tableName = "user_course_record",
        primaryKeys = {
                "user_course_record_id",
                "user_course_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = UserCourse.class,
                        parentColumns = "user_course_id",
                        childColumns = "user_course_id",
                        onDelete = ForeignKey.CASCADE)
        }
)
public class UserCourseRecord {
    @ColumnInfo(name = "user_course_id")
    public long userCourseId;
    @ColumnInfo(name = "user_course_record_id")
    public long userCourseRecordId;
    @ColumnInfo(name = "user_course_record_color")
    public Integer userCourseRecordColor;
    @ColumnInfo(name = "user_course_record_latitude")
    public double userCourseRecordLatitude;
    @ColumnInfo(name = "user_course_record_longitude")
    public double userCourseRecordLongitude;
    @ColumnInfo(name = "user_course_record_date")
    public Date userCourseRecordDate;
}
