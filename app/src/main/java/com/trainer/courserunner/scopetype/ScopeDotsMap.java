package com.trainer.courserunner.scopetype;

import com.trainer.courserunner.managedata.MapDAO;
import com.trainer.courserunner.managedata.MapDTO;

import java.util.List;

public class ScopeDotsMap extends ScopeDots {
    public ScopeDotsMap(double startX, double startY,
                        double endX, double endY) {
        super();
        List<MapDTO> scopeAddress = MapDAO.getScopeAddress(startX, startY, endX, endY);
        for (MapDTO address : scopeAddress) {
            dots.add(new ScopeDotAddress(startX, startY, endX, endY, address.getX(), address.getY()));
        }
    }
}
