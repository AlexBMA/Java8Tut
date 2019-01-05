package com.alex.paramBehaviorP1;


import java.util.ArrayList;
import java.util.List;

public class Main {

    static  String GREEN="green";

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple("red",70));
        inventory.add(new Apple("green",120));
        inventory.add(new Apple("red",159));

        prettyPrintApple(inventory,new AppleFancyFormatter());


    }


    public static List<Apple> filterGreenApples(List<Apple> invetory)
    {
        List<Apple> result = new ArrayList<>();

        for(Apple apple : invetory)
        {
            if(GREEN.equals(apple.getColor())){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory,String color)
    {
        List<Apple> result = new ArrayList<>();

        for(Apple apple:inventory)
        {
            if(apple.getColor().equals(color))
            {
                result.add(apple);
            }
        }

        return result;
    }
    public static List<Apple> filterApplesByWeight(List<Apple> inventory,int weight)
    {
        List<Apple> result = new ArrayList<>();

        for(Apple apple:inventory)
        {
            if(apple.getWeight()>weight)
            {
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory,
                                           String color,
                                           int weight,
                                           boolean flag)
    {
        List<Apple> result = new ArrayList<>();
        for(Apple apple:inventory)
        {
            if(flag && apple.getColor().equals(color)||
                    (!flag && apple.getWeight() > weight))
            {
                result.add(apple);
            }
        }

        return result;

    }

    public static List<Apple> filterApplesGood(List<Apple> inventory,
                                               ApplePredicate p)
    {
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory)
        {
            if( p.test(apple))
            {
                result.add(apple);
            }
        }

        return  result;
    }

    public static void prettyPrintApple(List<Apple> inventory,AppleFormatter formatter)
    {
        for(Apple apple:inventory)
        {
            System.out.println(formatter.accept(apple));
        }
    }
}
