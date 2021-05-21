package com.trainer.courserunner.settingrun.project;

import android.content.Intent;

import com.trainer.courserunner.Application.enumtype.StartType;
import com.trainer.courserunner.course.activity.GuideRunnerActivity;
import com.trainer.courserunner.course.activity.ProjectRunnerActivity;
import com.trainer.courserunner.settingrun.normal.NormalDistanceActivity;

public class ProjectDistanceActivity extends NormalDistanceActivity {
    protected void nextActivity(Object courseId){
        Intent intent = new Intent(getBaseContext(), ProjectRunnerActivity.class);
        intent.putExtra("courseId", (Long)courseId);
        intent.putExtra("startType", StartType.NEW);
        startActivity(intent);
    }
}
