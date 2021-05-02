package com.trainer.courserunner.rooms;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "user_course_flag",
        primaryKeys = {
                "user_course_id",
                "course_flag_Id",
                "course_id"
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
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(
                        entity = Course.class,
                        parentColumns = "course_id",
                        childColumns = "course_id",
                        onDelete = ForeignKey.CASCADE)
        }
)
public class UserCourseFlag {
    @NonNull
    @ColumnInfo(name = "user_course_id")
    public Long userCourseId;
    @NonNull
    @ColumnInfo(name = "course_id")
    public Long courseId;
    @NonNull
    @ColumnInfo(name = "course_flag_Id")
    public Long courseFlagId;
}
