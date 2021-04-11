package com.trainer.courserunner.scopetype;

import java.util.ArrayList;
import java.util.List;

public class MapPixel {
    ScopeDotAddress representationDotAddress;
    public MapPixel(List<ScopeDotAddress> scopeDotAddressList, double precision, double pixelStartX, double pixelStartY) {
        double pixelEndX = pixelStartX + precision;
        double pixelEndY = pixelStartY + precision;

        //픽셀추출
        List<ScopeDotAddress> extractionAddress=new ArrayList<>();
        for (ScopeDotAddress scopeDotAddress : scopeDotAddressList) {
            double x = scopeDotAddress.getX();
            double y = scopeDotAddress.getY();
            if (pixelStartX < x && x < pixelEndX) {
                if (pixelStartY < y && y < pixelEndY) {
                    extractionAddress.add(scopeDotAddress);
                }
            }
        }

        //비용계산
        double[] costs=new double[extractionAddress.size()];
        int i=0;
        for(ScopeDotAddress scopeDotAddress1 : scopeDotAddressList){
            costs[i]=0;
            for (ScopeDotAddress scopeDotAddress2 : scopeDotAddressList) {
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
        representationDotAddress= extractionAddress.get(lowestCostPixel);
    }

    public ScopeDotAddress getRepresentationDotAddress() {
        return representationDotAddress;
    }
}
