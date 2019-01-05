package java8streams;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamMain {

    public static void  main(String[] args)
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
        basicSteamExample(menu);


        List<String> title = Arrays.asList("Java8","In","Action");
        Stream<String> a = title.stream();
        a.forEach(System.out::println);

        List<String> names = new ArrayList<>();
        for(Dish d: menu)
        {
            names.add(d.getName());
        }

        System.out.println(names);

        List<String> names2 = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while(iterator.hasNext())
        {
            Dish d = iterator.next();
            names2.add(d.getName());
        }
        System.out.println(names2);

        List<String> names3 = menu.stream()
                                .map(Dish::getName)
                                .collect(Collectors.toList());
        System.out.println(names3);


        menu.stream().forEach(System.out::println);

        long count = menu.stream()
                        .filter(d->d.getCalories()>300)
                        .distinct()
                        .limit(3)
                        .count();
        System.out.println(count);

        //old way
        List<Dish> vegetarianDish= new ArrayList<>();
        for(Dish d:menu)
        {
            if(d.isVegetarian())
            {
                vegetarianDish.add(d);
            }
        }
        //new way with streams
        //filter elements with predicate  true/false
        List<Dish> vegetarianDish2 = menu.stream()
                                    .filter(Dish::isVegetarian)
                                    .collect(Collectors.toList());

        //filter distinct elements
        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
        numbers.stream()
                 .filter(i->i%2==0)
                 .distinct()
                 .forEach(System.out::println);

        //truncating a stream
        List<Dish> dishes3 = menu.stream()
                                .filter(d->d.getCalories()>300)
                                .limit(3)
                                .collect(Collectors.toList());

        //skipping elements
        List<Dish> dishes4 = menu.stream()
                                .filter(d->d.getCalories()>300)
                                .skip(2)
                                .collect(Collectors.toList());
        //Get the first 2 meat dishes
        List<Dish> dishes5 = menu.stream()
                                .filter(d ->d.getType()== Dish.Type.MEAT)
                                .limit(2)
                                .collect(Collectors.toList());
        System.out.println(dishes5);

        //map method is the stream API
        List<String> dishNames = menu.stream()
                                    .map(Dish::getName)
                                    .collect(Collectors.toList());
        System.out.println(dishNames);
        List<String> words = Arrays.asList("Java8","Lambdas","In","Action");
        List<Integer> wordLengths = words.stream()
                                            .map(String::length)
                                            .collect(Collectors.toList());

        System.out.println(wordLengths);

        List<Integer> dishNamesLengths = menu.stream()
                                            .map(Dish::getName)
                                            .map(String::length)
                                            .collect(Collectors.toList());
        System.out.println(dishNamesLengths);

        //Filtering streams
        String[] arrayOfWords = {"Goodbye","World"};
        Stream<String> stringStream = Arrays.stream(arrayOfWords);
        words.stream()
                .map(word->word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(stringStream);
        List<String> uniqueCharacters = words.stream()
                                            .map(w->w.split(""))
                                            .flatMap(Arrays::stream)
                                            .distinct()
                                            .collect(Collectors.toList());

        System.out.println(uniqueCharacters);
        exerciseSection5();


        //Finding and matching
        //anyMatch
        if(menu.stream().anyMatch(Dish::isVegetarian))
        {
            System.out.println("\n The menu is (somewhat) vegetarian friendly!");
        }
        //allMatch
        boolean isHealthy = menu.stream()
                                .allMatch(d->d.getCalories()<1000);

        System.out.println("\n "+isHealthy);
        //noneMatch
        boolean isHealthy2 = menu.stream()
                .allMatch(d->d.getCalories()>=1000);

        System.out.println("\n "+isHealthy2);


        //Finding an element
        Optional<Dish> dish = menu.stream()
                                    .filter(Dish::isVegetarian)
                                    .findAny();
        //Finding the first element
        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x->x*x)
                        .filter(x-> x%3 ==0)
                        .findFirst();

        //Reducing
        int sum =0;
        for(int x:numbers)
        {
            sum = sum + x;
        }


        int sum2 = numbers.stream()
                        .reduce(0,(a1,b1)-> a1 + b1 );

        System.out.println(sum2);

        int sum3 = numbers.stream().reduce(0,Integer::sum);
        System.out.println(sum3);

        int product = numbers.stream().reduce(1,(a1,b1)->a1*b1);
        System.out.println(product);

        //maximum and minimum
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);

        System.out.println(max.get());
        System.out.println(min.get());

        int countNr = menu.stream().map(d->1).reduce(0,(aa,bb)->aa+bb);

        System.out.println(countNr);


        int calories = menu.stream()
                            .map(Dish::getCalories)
                            .reduce(0,Integer::sum);

        System.out.println(calories);

        int calories2 = menu.stream()
                            .mapToInt(Dish::getCalories)
                            .sum();

        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();


        OptionalInt  maxCalories = menu.stream()
                                    .mapToInt(Dish::getCalories)
                                    .max();


        int max2 = maxCalories.orElse(1);


        IntStream evenNumbers = IntStream.rangeClosed(1,100)
                                    .filter(n->n%2 == 0);
        System.out.println(evenNumbers.count()+"[]");


        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1,100)
                .boxed()
                .flatMap(a1->IntStream.rangeClosed(a1,100)
                            .filter(b1->Math.sqrt(a1*a1+b1*b1)%1==0)
                            .mapToObj(b1->
                                new int[]{a1,b1,(int)Math.sqrt(a1*a1+b1*b1)}
                                )
                );

        pythagoreanTriples.limit(5)
                    .forEach(t->System.out.println(t[0]+", "+t[1]+","+t[2]));

        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1,100).boxed()
                        .flatMap(a1->
                            IntStream.rangeClosed(a1,100)
                                .mapToObj(
                                        b2-> new double[]{a1,b2,Math.sqrt(a1*a1+b2*b2)})
                                    .filter(t -> t[2]%1 ==0));

        pythagoreanTriples2.limit(5)
                .forEach(t->System.out.println(t[0]+", "+t[1]+","+t[2]));

        System.out.println("\n END OF FILE");
    }

    private static void exerciseSection5() {
        //Ex 1
        List<Integer> listNumbers = Arrays.asList(1,2,3,4,5);
        List<Integer> squareNumbers = listNumbers.stream()
                                        .map(nr->nr*nr)
                                        .collect(Collectors.toList());
        System.out.println(squareNumbers);


        //Ex 2
        List<Integer> listNr1 = Arrays.asList(1,2,3);
        List<Integer> listNr2 = Arrays.asList(3,4);
        List<int[]> pairList = listNr1.stream()
                                    .flatMap(nr1-> listNr2.stream()
                                                    .map(nr2-> new int[]{nr1,nr2})
                                            )
                                    .collect(Collectors.toList());

        pairList.forEach(i->System.out.print("("+i[0]+" "+i[1]+")"));

        //Ex 3 version me
        List<int[]> pairList2 = listNr1.stream()
                .flatMap(nr1-> listNr2.stream()
                        .map(nr2-> new int[]{nr1,nr2})
                )
                .filter(item->((item[0]+item[1])%3==0))
                .collect(Collectors.toList());
        System.out.println("\n$$");
        pairList2.forEach(i->System.out.print("("+i[0]+" "+i[1]+")"));

        // EX 3 version book
        List<int[]> pairList3 = listNr1.stream()
                .flatMap(nr1-> listNr2.stream()
                        .filter(nr2->(nr1+nr2)%3==0)
                        .map(nr2-> new int[]{nr1,nr2})
                )
                .collect(Collectors.toList());
        System.out.println("\n $$#");
        pairList3.forEach(i->System.out.print("("+i[0]+" "+i[1]+")"));
    }

    private static void basicSteamExample(List<Dish> menu) {
        //Stream use example
        List<String> threeHighCaloricDisNames = menu.stream()
                .filter(d->d.getCalories()>300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(threeHighCaloricDisNames);
    }
}
