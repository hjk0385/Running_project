package com.trainer.courserunner.course;

import android.graphics.Bitmap;

import com.trainer.courserunner.maps.MapDrawer;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDotLocation;
import com.trainer.courserunner.scopetype.ScopeDotsImage;
import com.trainer.courserunner.scopetype.ScopeDotsMap;

import java.util.List;

public class CourseSuggesterDrawer extends CourseSuggester{
    MapDrawer mapDrawer;
    List<ScopeDotAddress> course;
    public CourseSuggesterDrawer(MapDrawer drawer, ScopeDotsMap map, ScopeDotsImage image, ScopeDotAddress currentLocation){
        course=CourseSuggester.suggestPath(image,map,currentLocation);
        drawer.drawCourse(course);
    }
}
