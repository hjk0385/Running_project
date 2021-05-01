package com.trainer.courserunner.rooms;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "user_course_flag",
        primaryKeys = {
                "user_course_id",
                "course_flag_Id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = UserCourse.class,
                        parentColumns = "user_course_id",
                        childColumns = "user_course_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(
                        entity = CourseFlag.class,
                        parentColumns = "course_flag_Id",
                        childColumns = "course_flag_Id",
                        onDelete = ForeignKey.CASCADE)
        }
)
public class UserCourseFlag {
    @ColumnInfo(name = "user_course_id")
    public Long userCourseId;
    @ColumnInfo(name = "course_flag_Id")
    public Long courseFlagId;
}
