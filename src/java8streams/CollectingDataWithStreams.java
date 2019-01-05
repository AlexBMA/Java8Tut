package java8streams;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectingDataWithStreams {

    public static void main(String[] args)
    {

        List<Dish> menu = Arrays.asList(
                new Dish("pork",false,800,Dish.Type.MEAT),
                new Dish("beef",false,700,Dish.Type.MEAT),
                new Dish("chicken",false,400,Dish.Type.MEAT),
                new Dish("french fires",true,530,Dish.Type.OTHER),
                new Dish("rice",true,350,Dish.Type.OTHER),
                new Dish("season fruit",true,120,Dish.Type.OTHER),
                new Dish("pizza",true,550,Dish.Type.OTHER),
                new Dish("prawns",false,300,Dish.Type.FISH),
                new Dish("salmon",false,450,Dish.Type.FISH)
        );
        //Reducing and summarizing
        long howManyDishes = menu.stream().collect(Collectors.counting());
        long howManyDishes2 = menu.stream().count();

        System.out.println(howManyDishes);
        System.out.println(howManyDishes2);

        //Finding maximum and minimum in a stream of values
        Comparator<Dish> dishCaloriesComparator =
                    Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> mostCalorieDish = menu.stream()
                                            .collect(maxBy(dishCaloriesComparator));
        System.out.println(mostCalorieDish.get());

        //Summarization
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(avgCalories);

        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics.toString());

        //Joining strings
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        System.out.println(shortMenu);

        String shortMenu2 = menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(shortMenu2);

        //Generalized summarization with reduction
        int totalCalories2 = menu.stream().collect(reducing(0,Dish::getCalories,(i,j)->i+j));
        System.out.println(totalCalories2);

        Optional<Dish> mostCalorieDish2 =
                menu.stream()
                .collect(reducing((d1,d2)->d1.getCalories()>d2.getCalories()?d1:d2));

        System.out.println(mostCalorieDish2.get());

    }
}
