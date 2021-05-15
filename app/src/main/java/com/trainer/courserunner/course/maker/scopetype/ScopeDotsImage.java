package com.trainer.courserunner.course.maker.scopetype;

import android.graphics.Bitmap;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//병목현상 발생-> 멀티쓰레드로 최적화

public class ScopeDotsImage extends ScopeDots {
    static private boolean filterColorPixel(ScopeDotPixel scopeDotPixel) {
        final int colorLimit = 100;
        return scopeDotPixel.getRed() <= colorLimit ||
                scopeDotPixel.getBlue() <= colorLimit ||
                scopeDotPixel.getGreen() <= colorLimit;
    }

    private void banRecycle() {
        if (this.size() != 0) {
            throw new IllegalStateException();
        }
    }

    public void loadAllPixel(Bitmap bitmap) {
        banRecycle();
        List<Pair<Integer, Integer>> pixelLocations = new ArrayList<>();
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                pixelLocations.add(new Pair<>(i, j));
            }
        }

        final ScopeImageInfo scopeImageInfo = new ScopeImageInfo(bitmap.getHeight(), bitmap.getWidth());
        List<ScopeDot> scopeDotList = pixelLocations.stream().map((coordinate) -> {
            int x = coordinate.first;
            int y = coordinate.second;
            int rgb = bitmap.getPixel(x, y);
            return new ScopeDotPixel(scopeImageInfo, x, y, rgb);
        })
                .filter(ScopeDotsImage::filterColorPixel)
                .map((ScopeDotPixel scopeDotPixel) -> (ScopeDot) scopeDotPixel)
                .collect(Collectors.toList());
        this.addAll(scopeDotList);
    }

    public void loadPrecisionPixel(Bitmap bitmap, int resizeWidth, int resizeHeight) {
        banRecycle();
        Bitmap resizeBitmap = Bitmap.createScaledBitmap(bitmap, resizeWidth, resizeHeight, true);
        loadAllPixel(resizeBitmap);
    }
}
