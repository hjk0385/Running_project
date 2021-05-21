package com.trainer.courserunner.settingrun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.trainer.courserunner.R;
import com.trainer.courserunner.settingrun.normal.NormalDistanceActivity;

public abstract class ImageSelectionActivity extends AppCompatActivity implements RunningSettingInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_running_image_selection);

        GridView courseImageView=(GridView)findViewById(R.id.courseImageView);
        courseImageView.setAdapter(new ImageAdapter(this));
        courseImageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=getIntent();
                RunningSetting runningSetting= (RunningSetting) intent.getSerializableExtra("runningSetting");
                runningSetting.setDrawableId((Integer) adapterView.getAdapter().getItem(i));
                nextActivity(runningSetting);
            }
        });
    }

    static class ImageAdapter extends BaseAdapter {
        private final Integer[] mThumbIds={
                R.drawable.courseimage_1,
                R.drawable.courseimage_2,
                R.drawable.courseimage_3,
        };

        private final Context mContext;
        public ImageAdapter(Context c){
            mContext=c;
        }

        @Override
        public int getCount() {
            return mThumbIds.length;
        }

        @Override
        public Object getItem(int i) {
            return mThumbIds[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView;
            if(view==null){
                imageView=new ImageView(mContext);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(300,300));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8,8,8,8);
            }else{
                imageView=(ImageView)view;
            }
            imageView.setImageResource(mThumbIds[i]);
            return imageView;
        }
    }
}