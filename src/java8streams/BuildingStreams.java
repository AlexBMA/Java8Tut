package java8streams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class BuildingStreams {

    public static  void main(String[] args)
    {
        //Stream from stream
        Stream<String> stream = Stream.of("Java8","Lambdas","In","Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();

        //Stream from arrays
        int [] numbers ={2,3,5,7,11,13};
        int sum = Arrays.stream(numbers).sum();



        //Stream from file with neo
        long uniqueWords =0;

        try {
            Stream<String> lines = Files.lines(Paths.get("data2.txt"), Charset.defaultCharset());
            uniqueWords = lines
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct().count();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(uniqueWords);

        //Stream from functions: creating infinite streams
        Stream.iterate(0,n->n+2)
                    .limit(10)
                    .forEach(System.out::println);

        //Quiz Fibonacci tuples series

        // 0,1,1,2,3,5,8,13,...
        // a =0
        // b=1
        // temp = a a = b  b = temp + b

        Stream.iterate(new int[]{0,1},x -> new int[]{x[1],x[0]+x[1]})
                .limit(20)
                .forEach(t->System.out.println("("+t[0]+","+t[1]+ ")"));

        //Generate
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);



    }
}
