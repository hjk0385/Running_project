package com.trainer.courserunner.settingrun.project;

import android.content.Intent;

import com.trainer.courserunner.settingrun.ImageSelectionActivity;
import com.trainer.courserunner.settingrun.RunningSetting;
import com.trainer.courserunner.settingrun.normal.NormalDistanceActivity;

public class ProjectImageSelectionActivity extends ImageSelectionActivity {
    @Override
    public void nextActivity(RunningSetting runningSetting) {
        Intent intent = new Intent(getApplicationContext(), ProjectDistanceActivity.class);
        intent.putExtra("runningSetting",runningSetting);
        startActivity(intent);
    }
}
