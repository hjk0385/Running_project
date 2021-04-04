package com.trainer.courserunner.coursesuggest;

import java.util.ArrayList;
import java.util.List;

public class Dots {
    protected List<Dot> dots;

    public Dots() {
        dots = new ArrayList<>();
    }

    static public Dot getClosestDot(List<Dot> dots, Dot dot) {
        Dot closestDot = dots.get(0);
        for (int i = 1; i < dots.size(); i++) {
            if (closestDot.getCost(dot) >
                    dots.get(i).getCost(dot)) {
                closestDot = dots.get(i);
            }
        }
        return closestDot;
    }

    public Dot getClosestDot(Dot dot) {
        Dot closestDot = dots.get(0);
        for (int i = 1; i < dots.size(); i++) {
            if (closestDot.getCost(dot) >
                    dots.get(i).getCost(dot)) {
                closestDot = dots.get(i);
            }
        }
        return closestDot;
    }
}
