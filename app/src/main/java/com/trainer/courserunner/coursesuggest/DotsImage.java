package com.trainer.courserunner.coursesuggest;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DotsImage extends Dots{
    public DotsImage(Bitmap image){
        super();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getPixel(x, y);
                int red = Color.red(rgb);
                int green = Color.red(rgb);
                int blue = Color.red(rgb);

                //100~255인 점들만 추출(그려진 점들)(임의기준)
                if (red >= 100) {
                    dots.add(new DotRGB(image.getWidth(), image.getHeight(), x, y));
                }
            }
        }
    }
    public List<Dot> quantization(DotsScopeMap scopeMap){
        HashSet<Dot> mapDots=new HashSet<>();
        for(Dot dot:dots){
            mapDots.add(scopeMap.getClosestDot(dot));
        }
        return new ArrayList<Dot>(mapDots);
    }
}
