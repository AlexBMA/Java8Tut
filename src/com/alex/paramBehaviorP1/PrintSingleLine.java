package com.alex.paramBehaviorP1;

import java.util.List;

public class PrintSingleLine implements PrintApple {
    @Override
    public void printList(List<Apple> list) {

        int size = list.size();
        for(int i=0;i<size;i++)
        {
            Apple temp = list.get(i);
            System.out.print(temp.getColor()+" "+temp.getWeight()+" ");
        }
    }
}
