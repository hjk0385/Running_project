package com.trainer.courserunner.running.normal;

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

public class CourseImageSelectionActivity extends AppCompatActivity implements NextActivityInterface {
    private Integer[] mThumbIds={
            R.drawable.courseimage_1,
            R.drawable.courseimage_2,
            R.drawable.courseimage_3,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_image_selection);

        GridView courseImageView=(GridView)findViewById(R.id.courseImageView);
        courseImageView.setAdapter(new ImageAdapter(this));
        courseImageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CourseImageSelectionActivity.this.nextActivity(mThumbIds[i]);
            }
        });
    }

    @Override
    public void nextActivity(Object sendData) {
        Intent intent = new Intent(getBaseContext(), NormalRunningActivity.class);
        intent.putExtra("bitmap", (Integer) sendData);
        startActivity(intent);
    }


    class ImageAdapter extends BaseAdapter{
        private Context mContext;
        public ImageAdapter(Context c){
            mContext=c;
        }

        @Override
        public int getCount() {
            return mThumbIds.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
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