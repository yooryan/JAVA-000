package com.github.yooryan.spring.javaagent;

import com.github.yooryan.nointruder.RespondTime;


/**
 * @author linyunrui
 */
public class AopCaseImpl {


    @RespondTime
    public void test() {
        System.out.println("22222");
    }

    public void test1() {
        System.out.println("123123123123");
    }

    public static void main(String[] args) throws Exception {
        AopCaseImpl aopCase = new AopCaseImpl();
        aopCase.test();
        aopCase.test1();
    }

}
