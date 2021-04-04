package com.trainer.courserunner.coursesuggest;

import com.trainer.courserunner.managedata.MapDAO;
import com.trainer.courserunner.managedata.MapDTO;

import java.util.ArrayList;
import java.util.List;

public class DotsScopeMap extends Dots{
    public DotsScopeMap(double startX, double startY,
                        double endX, double endY){
        super();
        List<MapDTO> scopeAddress = MapDAO.getScopeAddress(startX, startY, endX, endY);
        for (MapDTO address : scopeAddress) {
            dots.add(new DotAddress(startX, startY, endX, endY, address.getX(), address.getY()));
        }
    }
}
