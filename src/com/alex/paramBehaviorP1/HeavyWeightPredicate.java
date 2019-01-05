package com.alex.paramBehaviorP1;

public class HeavyWeightPredicate implements ApplePredicate {

    private int HEAVY_WEIGHT = 150;
    @Override
    public boolean test(Apple apple) {

        return apple.getWeight() >HEAVY_WEIGHT;
    }
}
