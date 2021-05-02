package com.trainer.courserunner.rooms;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "course_mode",
        primaryKeys = {
                "course_mode_id"
        }
)
public class CourseMode {
    @ColumnInfo(name = "course_mode_id")
    @NonNull
    public Long courseModeId;
    @NonNull
    @ColumnInfo(name = "course_mode_name")
    public String courseModeName;
}
