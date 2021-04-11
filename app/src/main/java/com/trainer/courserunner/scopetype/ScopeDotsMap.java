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
        List<ScopeDot> scopeDotList = this.quantization(scopeDots);
        return new ScopeDotsMap(scopeDotList);
    }

    public ScopeDotsMap quantizationToScopeDotsMap(ScopeDots scopeDots,double precision) {
        List<ScopeDot> scopeDotList = this.quantization(scopeDots);
        List<ScopeDot> scopePrecisionDotList = new ArrayList<>();
        int xNumber= (int) (1/precision);
        int yNumber= (int) (1/precision);
        for(int i=0;i<xNumber;i++){
            for(int j=0;j<yNumber;j++){
                scopePrecisionDotList.add(getRepresentationDotAddress(scopeDotList,precision,i*precision,j*precision));
            }
        }
        return new ScopeDotsMap(scopePrecisionDotList);
    }

    private ScopeDot getRepresentationDotAddress(List<ScopeDot> scopeDotAddressList, double precision,
                                                              double pixelStartX, double pixelStartY) {
        double pixelEndX = pixelStartX + precision;
        double pixelEndY = pixelStartY + precision;

        //픽셀추출
        List<ScopeDot> extractionAddress=new ArrayList<>();
        for (ScopeDot scopeDotAddress : scopeDotAddressList) {
            double x = scopeDotAddress.getNormalizeX();
            double y = scopeDotAddress.getNormalizeY();
            if (pixelStartX < x && x < pixelEndX) {
                if (pixelStartY < y && y < pixelEndY) {
                    extractionAddress.add(scopeDotAddress);
                }
            }
        }

        //비용계산
        double[] costs=new double[extractionAddress.size()];
        int i=0;
        for(ScopeDot scopeDotAddress1 : scopeDotAddressList){
            costs[i]=0;
            for (ScopeDot scopeDotAddress2 : scopeDotAddressList) {
                costs[i]+=scopeDotAddress1.getCost(scopeDotAddress2);
            }
            i+=1;
        }

        //픽셀생성
        double lowestCost=costs[0];
        int lowestCostPixel=0;
        for(i=1;i<extractionAddress.size();i++){
            if(costs[i]<lowestCost){
                lowestCost=costs[i];
                lowestCostPixel=i;
            }
        }
        return extractionAddress.get(lowestCostPixel);
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
