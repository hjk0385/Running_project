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

    private ScopeDotsMap(List<ScopeDot> scopeDotList) {
        this.scopeDotList = scopeDotList;
    }

    public ScopeDotsMap quantizationToScopeDotsMap(ScopeDots scopeDots) {
        return new ScopeDotsMap(this.quantization(scopeDots));
    }

    public ScopeDotsMap quantizationToScopeDotsMap(ScopeDots scopeDots, double precision) {
        //scope
        List<ScopeDot> scopeDotList = this.quantization(scopeDots);
        List<ScopeDot> representationScopeDotList = new ArrayList<>();
        //calculate
        int xNumber = (int) (1 / precision);
        int yNumber = (int) (1 / precision);
        for (int i = 0; i < xNumber; i++) {
            for (int j = 0; j < yNumber; j++) {
                ScopeDot scopeDot = getRepresentationDotAddress(scopeDotList, precision, i * precision, j * precision);
                if (scopeDot != null) {
                    representationScopeDotList.add(scopeDot);
                }
            }
        }
        return new ScopeDotsMap(representationScopeDotList);
    }

    private ScopeDot getRepresentationDotAddress(List<ScopeDot> scopeDotAddressList, double precision,
                                                 double pixelStartX, double pixelStartY) {
        double pixelEndX = pixelStartX + precision;
        double pixelEndY = pixelStartY + precision;
        //픽셀추출
        List<ScopeDot> extractionDots = new ArrayList<>();
        for (ScopeDot scopeDotAddress : scopeDotAddressList) {
            double x = scopeDotAddress.getNormalizeX();
            double y = scopeDotAddress.getNormalizeY();
            if (pixelStartX < x && x < pixelEndX) {
                if (pixelStartY < y && y < pixelEndY) {
                    extractionDots.add(scopeDotAddress);
                }
            }
        }
        if(extractionDots.size()==0){
            return null;
        }
        return extractionDots.get(0);
        /*
        //모든 픽셀과 가까이 있는 점을 대표점으로 지정
        double lowestCost=1;
        ScopeDot representationDot=null;
        for (ScopeDot scopeDotAddress1 : scopeDotAddressList) {
            double cost=0;
            for (ScopeDot scopeDotAddress2 : scopeDotAddressList) {
                cost += scopeDotAddress1.getCost(scopeDotAddress2);
            }
            if(lowestCost>cost){
                lowestCost=cost;
                representationDot=scopeDotAddress1;
            }
        }
        return representationDot;

         */
    }


    public List<ScopeDotAddress> getShortestPath() {
        List<ScopeDotAddress> course = new ArrayList<>();
        List<ScopeDot> remainDots = new ArrayList<ScopeDot>(this.scopeDotList);
        ScopeDot currentScopeDot = remainDots.get(0);
        remainDots.remove(currentScopeDot);
        course.add((ScopeDotAddress) currentScopeDot);
        while (remainDots.size() != 0) {
            currentScopeDot = ScopeDots.getClosestDot(remainDots, currentScopeDot);
            remainDots.remove(currentScopeDot);
            course.add((ScopeDotAddress) currentScopeDot);
        }
        return course;
    }

}
