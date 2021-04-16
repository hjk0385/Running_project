package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "usercourseinfo",
        primaryKeys = {
                "usercourse_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = CourseInfo.class,
                        parentColumns = "course_id",
                        childColumns = "course_id",
                        onDelete = ForeignKey.CASCADE)
        }
        )
public class UserCourseInfo {
    @PrimaryKey(autoGenerate = true)
    public long usercourse_id;
    public long course_id;
}
