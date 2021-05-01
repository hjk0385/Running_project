package com.trainer.courserunner.rooms;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

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
        },
        indices = {
                @Index(value={"course_flag_Id"}, unique = true)
        }
)
public class CourseFlag {
    @NonNull
    @ColumnInfo(name = "course_id")
    public Long courseId;
    @NonNull
    @ColumnInfo(name = "course_flag_Id")
    public Long courseFlagId;
    @ColumnInfo(name = "course_flag_latitude")
    public Double courseFlagLatitude;
    @ColumnInfo(name = "course_flag_longitude")
    public Double courseFlagLongitude;
}
