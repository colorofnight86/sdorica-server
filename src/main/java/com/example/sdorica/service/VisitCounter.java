package com.example.sdorica.service;

public class VisitCounter {

    private static final VisitCounter visitCounter = new VisitCounter();
    private static int count = 0;

    private VisitCounter(){
    }

    public static VisitCounter getVisitCounter(){
        count+=1;
        return visitCounter;
    }

    public void show(){
        System.out.println("访问次数：" + count);
    }

}
