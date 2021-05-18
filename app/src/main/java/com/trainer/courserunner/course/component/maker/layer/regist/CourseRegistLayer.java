package com.trainer.courserunner.course.component.maker.layer.regist;

import com.trainer.courserunner.course.component.maker.scopetype.ScopeDotAddress;

import java.util.List;
import java.util.function.Function;

public interface CourseRegistLayer extends Function<List<ScopeDotAddress>, Long> {
}
