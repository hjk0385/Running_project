package com.trainer.courserunner.course.maker;

import com.trainer.courserunner.course.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.course.maker.scopetype.ScopeDotsMapQuanzationPolicy;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class CourseMakerBuilder {
    ScopeDotsMapQuanzationPolicy quanzationPolicyImageToMap;
    Function<List<ScopeDotAddress>,List<ScopeDotAddress>>


    ScopeDotsMapQuanzationPolicy quanzationPolicy;
    Consumer<List<ScopeDotAddress>> resultconsumer;



}
/*
    1. 그림의 양자화
    2. 양자화된 그림을 이어서 그리기
    3. 만들어진 코스를 처리하기

    함수형 인터페이스 활용
    양자화 방식들을 모아둔 클래스
    scopetype이 아니라 코스메이커에 모두 모아두는게 어떨까?
*/