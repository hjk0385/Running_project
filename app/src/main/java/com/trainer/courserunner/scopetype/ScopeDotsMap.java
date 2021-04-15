package com.trainer.courserunner.scopetype;

import com.trainer.courserunner.managedata.MapDAO;
import com.trainer.courserunner.managedata.MapDTO;

import java.util.ArrayList;
import java.util.List;

public class ScopeDotsMap extends ScopeDots {
    ScopeMapInfo scopeMapInfo;
    public ScopeDotsMap(ScopeMapInfo scopeMapInfo) {
        List<MapDTO> scopeMapAddress = MapDAO.getScopeAddress(scopeMapInfo);
        for (MapDTO address : scopeMapAddress) {
            scopeDotList.add(new ScopeDotAddress(scopeMapInfo, address.getX(), address.getY()));
        }
        this.scopeMapInfo=scopeMapInfo;
    }
    private ScopeDotsMap(List<ScopeDot> scopeDotList) {
        this.scopeDotList = scopeDotList;
    }
    public List<ScopeDotAddress> getFlagAddresses(){
        List<ScopeDotAddress> scopeDotAddressList=new ArrayList<>();
        for(ScopeDot scopeDot:this.scopeDotList){
            scopeDotAddressList.add((ScopeDotAddress)scopeDot);
        }
        return scopeDotAddressList;
    }

    //legacy quantization
    public ScopeDotsMap quantizationToScopeDotsMap(ScopeDots scopeDots) {
        return new ScopeDotsMap(this.quantization(scopeDots));
    }
    //

    //new quantization
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
    }
    //

    public ScopeMapInfo getScopeMapInfo() {
        return scopeMapInfo;
    }
}
