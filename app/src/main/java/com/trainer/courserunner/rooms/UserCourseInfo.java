package com.trainer.courserunner.rooms;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "usercourseinfo",
        foreignKeys = {
                @ForeignKey(
                        entity = CourseInfo.class,
                        parentColumns = "course_id",
                        childColumns = "course_id",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("course_id"),@Index(value = {"usercourse_id", "course_id"})}
)
public class UserCourseInfo {
    @PrimaryKey(autoGenerate = true)
    public long usercourse_id;
    public long course_id;
}
