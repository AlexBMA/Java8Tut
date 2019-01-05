package com.alex.paramBehaviorP1;

import java.util.List;

public class PrintMultipleLines implements PrintApple {
    @Override
    public void printList(List<Apple> list) {
        int size = list.size();
        for(int i=0;i<size;i++)
        {
            Apple apple = list.get(i);
            System.out.println(apple.getColor()+" "+apple.getWeight());
        }
    }
}
