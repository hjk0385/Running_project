package com.trainer.courserunner;

import android.os.AsyncTask;
import android.os.Bundle;


public class CourseGuideActivity extends NavermapActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CourseOverseer().execute();
    }

    class CourseOverseer extends AsyncTask<Object,Object,Object> {

        @Override
        protected Object doInBackground(Object... objects) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            super.onProgressUpdate(values);
        }

        //function
        private void oversightMap() {
            //this.mapDrawer.drawpath();
        }

        private void oversightSound() {

        }
    }
}
