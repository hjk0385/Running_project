package com.trainer.courserunner.settingrun.normal;

import android.content.Intent;

import com.trainer.courserunner.settingrun.ImageSelectionActivity;
import com.trainer.courserunner.settingrun.RunningSetting;

public class NormalImageSelectionActivity extends ImageSelectionActivity {
    @Override
    public void nextActivity(RunningSetting runningSetting) {
        Intent intent = new Intent(getApplicationContext(), NormalDistanceActivity.class);
        intent.putExtra("runningSetting",runningSetting);
        startActivity(intent);
    }
}
