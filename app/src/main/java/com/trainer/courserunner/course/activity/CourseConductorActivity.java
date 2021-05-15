package com.trainer.courserunner.course.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.location.Location;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.naver.maps.map.NaverMap;
import com.trainer.courserunner.R;
import com.trainer.courserunner.course.CourseConductor;
import com.trainer.courserunner.map.drawer.NavermapLocationActivity;


//이 엑티비티를 사용해서 만든다.
public abstract class CourseConductorActivity extends NavermapLocationActivity {
    CourseConductor courseConductor;

    protected abstract CourseConductor createCourseConductor();

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        super.onMapReady(naverMap);
        courseConductor = createCourseConductor();
        naverMap.addOnLocationChangeListener((Location location) -> {
            courseConductor.update(null, location);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_courseconductor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.courseconductor_colorpicker:
                ColorPickerDialogBuilder
                        .with(this)
                        .initialColor(Color.WHITE)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                Log.v("tempTest", String.valueOf(selectedColor));
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface d, int lastSelectedColor, Integer[] allColors) {
                                courseConductor.setCurrentColor(lastSelectedColor);
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .build()
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
