package com.trainer.courserunner.map.drawer;

import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.trainer.courserunner.R;

public class NaverMapOptionActivity extends NavermapLocationActivity{
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_guidecolor_option, menu);
        return true;
    }
}
