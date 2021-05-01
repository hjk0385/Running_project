package com.trainer.courserunner.course.drawer.drawtype;

import android.location.Location;

import androidx.core.util.Consumer;

import java.util.ArrayList;
import java.util.List;

public class DrawingPath extends ArrayList<DrawingAddress> {
    Consumer<Object> property;

    public Consumer<Object> getProperty() {
        return property;
    }

    public static class Builder {
        List<DrawingAddress> drawingAddressList;
        Builder() {
            drawingAddressList=new ArrayList<>();
        }

        Builder addLocation(Location location) {
            drawingAddressList.add(new DrawingAddress(location));
            return this;
        }

        Builder setColor(int color){

            return this;
        }
    }
}

