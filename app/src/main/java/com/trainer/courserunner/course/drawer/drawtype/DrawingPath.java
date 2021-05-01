package com.trainer.courserunner.course.drawer.drawtype;

import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.overlay.PolylineOverlay;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class DrawingPath extends ArrayList<DrawingAddress> {
    Consumer<Object> property;

    public Consumer<Object> getProperty() {
        return property;
    }

    public static class Builder {
        List<DrawingAddress> drawingAddressList;
        Consumer<Object> property;

        public Builder() {
            drawingAddressList = new ArrayList<>();
            property = (o) -> {
            };
        }

        public void accept(DrawingAddress drawingAddress) {
            drawingAddressList.add(drawingAddress);
        }

        public Builder add(DrawingAddress drawingAddress) {
            drawingAddressList.add(drawingAddress);
            return this;
        }

        public Builder setColor(Integer color) {
            property=property.andThen((Object obj) -> {
                if (obj instanceof PathOverlay) {
                    ((PathOverlay) obj).setColor(color);
                } else if (obj instanceof PolylineOverlay) {
                    ((PolylineOverlay) obj).setColor(color);
                }
            });
            return this;
        }

        public Builder setWidth(Integer width) {
            property=property.andThen((Object obj) -> {
                if (obj instanceof PathOverlay) {
                    ((PathOverlay) obj).setWidth(width);
                } else if (obj instanceof PolylineOverlay) {
                    ((PolylineOverlay) obj).setWidth(width);
                }
            });
            return this;
        }

        public DrawingPath build() {
            DrawingPath drawingPath = new DrawingPath();
            drawingPath.addAll(drawingAddressList);
            drawingPath.property = property;
            return drawingPath;
        }
    }
}

