package com.trainer.courserunner.course.component;

import android.os.AsyncTask;

/*
    DB관련작업을 수행한 후에 주 쓰레드로 넘어가서 UI를 변경해야 한다.
    작업쓰레드 -> 주쓰레드
*/
public abstract class CourseComponent {
    protected abstract Object runInWorkThread();
    protected abstract void runInUiThread(Object object);
    public void runComponent(){
        new CourseComponentAsyncTask().execute();
    }

    private class CourseComponentAsyncTask extends AsyncTask<Void,Void,Object> {
        @Override
        protected Object doInBackground(Void... voids) {
            return runInWorkThread();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            runInUiThread(o);
        }
    }
}
