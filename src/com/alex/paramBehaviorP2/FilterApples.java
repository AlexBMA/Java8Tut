package com.alex.paramBehaviorP2;

import com.alex.paramBehaviorP1.Apple;
import com.alex.paramBehaviorP1.ApplePredicate;
import com.alex.paramBehaviorP1.GreenColorApplePredicate;
import com.alex.paramBehaviorP1.HeavyWeightPredicate;

import java.util.ArrayList;
import java.util.List;

public class FilterApples {

    public static  void main(String[] args)
    {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple("red",70));
        inventory.add(new Apple("green",120));
        inventory.add(new Apple("red",159));

        List<Apple> heavyApples =filterApples(inventory, new HeavyWeightPredicate());

        List<Apple> greenApples = filterApples(inventory,new GreenColorApplePredicate());

        List<Apple> redApples = filter(inventory,(Apple apple) -> "red".equals(apple.getColor()));
        List<Integer> numIntegers = new ArrayList<>();
        numIntegers.add(1);
        numIntegers.add(2);
        numIntegers.add(10);
        List<Integer> evenNumber = filter(numIntegers,(Integer i)-> i%2 == 0);
    }

    public static List<Apple> filterApples(List<Apple> inventory,
                                           ApplePredicate p)
    {
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory)
        {
            if(p.test(apple))
            {
                result.add(apple);
            }
        }
        return result;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate)
    {
        List<T> result = new ArrayList<>();
        for(T e :list)
        {
            if(predicate.test(e)) result.add(e);
        }
        return  result;
    }
}
