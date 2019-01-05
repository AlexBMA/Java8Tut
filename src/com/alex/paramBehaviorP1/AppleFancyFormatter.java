package com.alex.paramBehaviorP1;

public class AppleFancyFormatter implements AppleFormatter {

    private int HEAVY_WEIGHT = 150;
    private String HEAVY= "heavy";
    private String LIGHT ="light";
    @Override
    public String accept(Apple a) {
        String characteristic = a.getWeight()> HEAVY_WEIGHT?HEAVY:LIGHT;
        return "A "+characteristic+ " "+a.getColor()+" apple";
    }
}
