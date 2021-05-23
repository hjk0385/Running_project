package com.trainer.courserunner.runningset;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.trainer.courserunner.Application.AppFunctionLoader;
import com.trainer.courserunner.Application.enumtype.ModeType;
import com.trainer.courserunner.Application.enumtype.StartType;
import com.trainer.courserunner.course.activity.ProjectRunnerActivity;
import com.trainer.courserunner.rooms.UserCourse;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RunningProjectRecordActivity extends ListActivity {
    UserCourse[] userCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userCourses = AppFunctionLoader.getAppDatabase().userCourseDao().getUserCourse(ModeType.PROJECTRUNNER.ordinal());
        List<String> datas = Arrays.stream(userCourses).map(new Function<UserCourse, String>() {
            @Override
            public String apply(UserCourse userCourse) {
                return String.valueOf(userCourse.userCourseId);
            }
        }).collect(Collectors.toList());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        UserCourse selectUserCourse=userCourses[position];
        //
        Intent intent = new Intent(getBaseContext(), ProjectRunnerActivity.class);
        intent.putExtra("startType",StartType.RESUME);
        intent.putExtra("userCourseId",selectUserCourse.userCourseId);
        intent.putExtra("courseId",selectUserCourse.courseId);
        //
        startActivity(intent);
    }
}