package com.trainer.courserunner.course.maker.policy.line;

import com.naver.maps.map.overlay.PathOverlay;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotAddress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public abstract class LineConnectPolicyPrim implements LineConnectPolicy {

    /*
    private final double MAXCOST=9999999999.0;

    @Override
    public List<ScopeDotAddress> apply(List<ScopeDotAddress> scopeDotAddressList, ScopeDotAddress startScopeDotAddress) {
        final int size=scopeDotAddressList.size();
        double[][] graphCost =new double[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                graphCost[i][j]=scopeDotAddressList.get(i).getCost(scopeDotAddressList.get(j));
            }
        }

        HashSet<Integer> excludeVertexes=new HashSet<>();
        for(int i=0;i<size;i++){
            excludeVertexes.add(i);
        }

        double[] d=new double[size];
        for(int i=0;i<size;i++){
            d[i]=MAXCOST;
        }
        d[0]=0;

        int[] tree=new int[size];
        while(excludeVertexes.size()>0)
        {
            Integer u=deleteMin(excludeVertexes,d);
            for(int v=0;v<size;v++){
                if(graphCost[u][v]<d[v]){
                    d[v]=graphCost[u][v];
                    tree[v]=u;
                }
            }
        }

        List<ScopeDotAddress> scopeDotAddressList1=new ArrayList<>();
        int i=1;
        int nextPos=0;
        while(i<size){
            scopeDotAddressList1.add(scopeDotAddressList.get(nextPos));
            nextPos=tree[i];
            i+=1;
        }

    }

    Integer deleteMin(HashSet<Integer> excludeVertexes,double[] d){
        int i=0;

    }

     */
}
