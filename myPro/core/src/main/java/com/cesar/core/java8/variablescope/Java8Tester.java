package com.cesar.core.java8.variablescope;

public class Java8Tester {
    final static String salutation = "Hello! ";

    public static void main(String args[]) {
        GreetingService greetService1 = message ->
                System.out.println(salutation + message);
        greetService1.sayMessage("Runoob");
//====================相当于下面==============================
        GreetingService g = new GreetingService() {
            @Override
            public void sayMessage(String message) {
                System.out.println(salutation + message);
            }
        };
        g.sayMessage("jack");
//===========================================================
    }

    interface GreetingService {
        void sayMessage(String message);
    }
}