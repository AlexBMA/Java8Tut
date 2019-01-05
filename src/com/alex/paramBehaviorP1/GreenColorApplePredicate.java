package com.alex.paramBehaviorP1;

public class GreenColorApplePredicate implements ApplePredicate {

    private  String GREEN="green";

    @Override
    public boolean test(Apple apple) {
        return apple.getColor().equals(GREEN);
    }
}
