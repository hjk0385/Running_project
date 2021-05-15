package com.trainer.courserunner.course.component.maker.layer.line;

import com.trainer.courserunner.course.component.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.component.maker.scopetype.ScopeDotsMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LineConnectPolicyDfsCustom extends LineConnectLayerDfs{
    double precision;
    public LineConnectPolicyDfsCustom(double precision){
        this.precision=precision;
    }

    @Override
    public List<ScopeDotAddress> apply(ScopeDotsMap scopeDots, ScopeDotAddress startAddress) {
        final double costLimit=0.1;

        List<ScopeDotAddress> prevRoundResult=super.apply(scopeDots,startAddress);
        Iterator<ScopeDotAddress> prevRoundResultIter = prevRoundResult.iterator();
        while(prevRoundResultIter.hasNext()){
            ScopeDotAddress dotAddress=prevRoundResultIter.next();
            List<ScopeDotAddress> removeScopeDotAddressList=
                    prevRoundResult.stream().filter((ScopeDotAddress scopeDotAddress)->{
                        if(dotAddress==scopeDotAddress||scopeDotAddress.getCost(dotAddress)>costLimit){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }).collect(Collectors.toList());
            prevRoundResult.removeAll(removeScopeDotAddressList);
        }
        return prevRoundResult;
    }
}
