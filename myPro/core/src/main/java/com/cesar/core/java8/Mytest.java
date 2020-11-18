package com.cesar.core.java8;

import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;

/**
 * @ClassName Mytest
 * @Description TODO
 * @Author Cesar
 * @Data 2019/6/28
 **/
public class Mytest {

    public static void main(String[] args) {
        IntBinaryOperator intBinaryOperator = (int a, int b) -> a + b;

        MathOperation add = (int a, int b) -> a + b;

        int operation = add.operation(1, 2);
        System.out.println(operation);
    }
}


interface MathOperation {
    int operation(int a, int b);
}