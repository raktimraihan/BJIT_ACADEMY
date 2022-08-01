package com.bjit.training.secondproject.bwtesting;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class UnitTestingDemo {

    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(SpringWhiteBoxTest.class);
        for (Failure fail : result.getFailures()) {
            System.out.println(fail.toString());
        }
        if(result.wasSuccessful()) {
            System.out.println("Test result: "+result.wasSuccessful());
        }
        else System.out.println("Failed Cases: "+result.getFailureCount());

        Result result01 = JUnitCore.runClasses(SpringBlackBoxTesting.class);
        for (Failure fail : result01.getFailures()) {
            System.out.println(fail.toString());
        }
        if(result.wasSuccessful()) {
            System.out.println("Test result: "+result01.wasSuccessful());
        }
        else System.out.println("Failed Cases: "+result01.getFailureCount());

    }
}
