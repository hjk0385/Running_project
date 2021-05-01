package com.trainer.courserunner.rooms;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "course_flag",
        primaryKeys = {
                "course_id",
                "course_flag_Id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = Course.class,
                        parentColumns = "course_id",
                        childColumns = "course_id",
                        onDelete = ForeignKey.CASCADE)
        }
)
public class CourseFlag {
    @ColumnInfo(name = "course_id")
    public long courseId;
    @ColumnInfo(name = "course_flag_Id")
    public long courseFlagId;
    @ColumnInfo(name = "course_flag_latitude")
    public Double courseFlagLatitude;
    @ColumnInfo(name = "course_flag_longitude")
    public Double courseFlagLongitude;
}
