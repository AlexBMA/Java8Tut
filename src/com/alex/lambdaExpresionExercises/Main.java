package com.alex.lambdaExpresionExercises;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args)
    {

        Supplier<BasicCalculator> supplier = BasicCalculator::new;
        BasicCalculator<Integer> basicCalculator = supplier.get();

        int a_plus_b = basicCalculator.sum2Numbers(( a,b)-> a + b,10,20);
        System.out.println(a_plus_b);

        int a_minus_b = basicCalculator.multiplication2Numbers((a, b)->a*b,10,5);
        System.out.println(a_minus_b);

        int a_multiply_b = basicCalculator.division2Numbers((a,b)->a/b,10,5);
        System.out.println(a_multiply_b);

        int a_subtract_b = basicCalculator.subtraction2Numbers((a,b)->a-b,10,2);
        System.out.println(a_subtract_b);

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        int sum_Of_N_Numbers = basicCalculator.sumNNumbers((tempList)->
                { int sum=0;
                  for(Integer i : tempList)
                      sum = basicCalculator.add((a,b)->b=b+a,i,sum);
                  return sum; }
                ,list);
        System.out.println(sum_Of_N_Numbers);


        List<Integer> list2 = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        int sum_Of_N_Numbers2 = basicCalculator.sumNNumber2((temp,theSum)-> temp+theSum,list2,0);

        System.out.println(sum_Of_N_Numbers2);


        //Predicate<T> test(T t);
        //Function<T,R>  R apply(T t);
        //Supplier<T>
        //Consumer<T>
        //BinaryOperation<T>


        Predicate<Integer> predicate = x -> x >0;

        Function<String,Integer> f = x -> Integer.parseInt(x);

        System.out.println(f.apply("10"));




    }
}
