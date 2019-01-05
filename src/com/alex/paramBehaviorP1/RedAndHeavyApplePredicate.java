package com.alex.paramBehaviorP1;

public class RedAndHeavyApplePredicate implements ApplePredicate {

    private  String RED ="red";
    private  int WEIGHT =150;

    @Override
    public boolean test(Apple apple) {

        return apple.getColor().equals(RED)&&apple.getWeight()>WEIGHT;
        //return false;
    }
}
