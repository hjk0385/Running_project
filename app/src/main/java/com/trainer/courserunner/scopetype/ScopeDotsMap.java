package com.trainer.courserunner.scopetype;

import com.trainer.courserunner.managedata.MapDAO;
import com.trainer.courserunner.managedata.MapDTO;

import java.util.ArrayList;
import java.util.List;

public class ScopeDotsMap extends ScopeDots {
    public ScopeDotsMap(double startX, double startY,
                        double endX, double endY) {
        List<MapDTO> scopeMapAddress = MapDAO.getScopeAddress(startX, startY, endX, endY);
        for (MapDTO address : scopeMapAddress) {
            scopeDotList.add(new ScopeDotAddress(startX, startY, endX, endY, address.getX(), address.getY()));
        }
    }

    private ScopeDotsMap(List<ScopeDot> scopeDotList){
        this.scopeDotList=scopeDotList;
    }

    public ScopeDotsMap quantizationToScopeDotsMap(ScopeDots scopeDots) {
        List<ScopeDot> scopeDotList = this.quantization(scopeDots);
        return new ScopeDotsMap(scopeDotList);
    }

    public List<ScopeDotAddress> getShortestPath(){
        List<ScopeDotAddress> course=new ArrayList<>();
        List<ScopeDot> remainDots=new ArrayList<ScopeDot>(this.scopeDotList);
        ScopeDot currentScopeDot = remainDots.get(0);
        remainDots.remove(currentScopeDot);
        course.add((ScopeDotAddress)currentScopeDot);
        while (remainDots.size() != 0) {
            currentScopeDot = ScopeDots.getClosestDot(remainDots, currentScopeDot);
            remainDots.remove(currentScopeDot);
            course.add((ScopeDotAddress)currentScopeDot);
        }
        return course;
    }

}
