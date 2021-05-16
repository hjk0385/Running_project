package com.trainer.courserunner.course.component.maker.layer.line;

import com.trainer.courserunner.course.component.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.component.maker.scopetype.ScopeDotsMap;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class LineConnectPolicyDfsCustom extends LineConnectLayerDfs {
    double costLimit;

    public LineConnectPolicyDfsCustom(double costLimit) {
        this.costLimit = costLimit;
    }

    @Override
    public List<ScopeDotAddress> apply(ScopeDotsMap scopeDots, ScopeDotAddress startAddress) {
        List<ScopeDotAddress> preprocessCourse = super.apply(scopeDots, startAddress);
        List<ScopeDotAddress> course = new Vector<>();

        while (preprocessCourse.size() > 0) {
            ScopeDotAddress chosenOne = preprocessCourse.get(0);
            course.add(chosenOne);
            preprocessCourse = preprocessCourse.parallelStream()
                    .filter(scopeDotAddress -> scopeDotAddress.getCost(chosenOne) > costLimit)
                    .collect(Collectors.toList());
        }
        return course;
    }
}
