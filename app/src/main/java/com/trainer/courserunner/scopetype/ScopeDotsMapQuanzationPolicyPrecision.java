package com.trainer.courserunner.scopetype;

import java.util.ArrayList;
import java.util.List;

public class ScopeDotsMapQuanzationPolicyPrecision extends ScopeDotsMapQuanzationPolicyDefault implements ScopeDotsMapQuanzationPolicy{
    private double precision;
    public ScopeDotsMapQuanzationPolicyPrecision(double precision){
        this.precision=precision;
    }

    @Override
    public List<ScopeDot> quantization(List<ScopeDot> scopeDotListInput, List<ScopeDot> scopeDotListOutput) {
        //scope
        List<ScopeDot> scopeDotList = super.quantization(scopeDotListInput,scopeDotListOutput);
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
        return representationScopeDotList;
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
        if (extractionDots.size() == 0) {
            return null;
        }
        return extractionDots.get(0);
    }
    //
}
