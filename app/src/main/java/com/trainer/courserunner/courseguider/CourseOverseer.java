package com.trainer.courserunner.courseguider;

public class CourseOverseer implements Runnable{
    private MapDrawer mapDrawer=null;
    private boolean active=true;

    public CourseOverseer(MapDrawer mapDrawer){
        this.mapDrawer=mapDrawer;
    }

    public void stop(){
        this.active=false;
    }

    private void oversightMap(){
        //this.mapDrawer.drawpath();
    }
    private void oversightSound(){

    }

    @Override
    public void run() {
        while(active){
            oversightMap();
            oversightSound();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
