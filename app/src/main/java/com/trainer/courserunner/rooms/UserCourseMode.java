package com.trainer.courserunner.rooms;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_course_mode")
public class UserCourseMode {
    @PrimaryKey
    @ColumnInfo(name="user_course_mode_id")
    public long userCourseModeId;
    @ColumnInfo(name = "user_course_mode_name")
    public String userCourseModeName;
}
