package com.trainer.courserunner.rooms;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "course_mode",
        primaryKeys = {
                "course_mode_id",
                "course_mode_name"
        }
)
public class CourseMode {
    @ColumnInfo(name = "course_mode_id")
    public long courseModeId;
    @ColumnInfo(name = "course_mode_name")
    public String courseModeName;
}
