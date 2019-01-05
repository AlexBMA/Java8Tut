package com.alex.lambdaExpresion;

import com.alex.paramBehaviorP1.Apple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class Test {

    public static void main(String[] args) throws IOException {

        Predicate<String> nonEmptyStringPredicate = (String s)->!s.isEmpty();
        //List<String> nonEmpty = filter(li)

        String fileName="data2.txt";
        String oneLine = processFile((BufferedReader br)->br.readLine(),fileName);
        String twoLines = processFile((BufferedReader br) -> br.readLine()+ br.readLine(),fileName);


        System.out.println(oneLine);
        System.out.println(twoLines);

        int sum = executeOperation((int a,int b)->a*b,20,4);
        int sum2 = executeOperation((a,b)->a*b,20,4 );
        List<Integer> list =  Arrays.asList(1,2,3,4,5,10);

        int sumList = executeOperationList((List<Integer> l)-> {  int suma=0; for(Integer i : l) suma = simpleMethodSum((int a)-> a,i,suma); return  suma;}
                        //simpleMethod2((int a)-> a, i,suma); return  suma; }
        ,list);

        System.out.println("## sum:"+sum);
        System.out.println("## sum2:"+sum2);
        System.out.println("$$ " + sumList);
        forEach(
                Arrays.asList(1,2,3,4,5,10),
                (Integer i)->System.out.println(i*i));



        List<Integer> l = map (
            Arrays.asList("One","Two","Three"),(String s)->s.length());

        System.out.println(l.toString());

        List<String> str = Arrays.asList("a","c","A","B");
        //str.sort((s1,s2)->s1.compareToIgnoreCase(s2));
        str.sort(String::compareToIgnoreCase);
        System.out.println("$: "+str);


        Function<String,Integer> stringToInteger = (String s)->Integer.parseInt(s);
        System.out.println(stringToInteger.apply("8") == 8);
        Function<String,Integer> stringToInteger2 = Integer::parseInt;
        System.out.println(stringToInteger2.apply("9")==9);

        BiPredicate<List<String>,String> contains = (list2,element) ->list2.contains(element);
        System.out.println(contains.test(Arrays.asList("Casa","Masina","Frigider"),"Masina"));
        BiPredicate<List<String>,String> contains2 = List::contains;
        System.out.println(contains2.test(Arrays.asList("Casa","Masina","Frigider"),"Casa"));


        Supplier<Apple> c1 = Apple::new;

        Apple a = c1.get();

        Function<Integer,Apple> c2 = Apple::new;
        Apple a2 = c2.apply(150);

        List<Integer> weights = Arrays.asList(7,3,4,10);
        List<Apple> apples = map(weights,Apple::new);

        System.out.println(apples.toString());



        BiFunction<String,Integer,Apple> c3 = Apple::new;
        Apple a3 = c3.apply("red",150);

        System.out.println(contains.test(Arrays.asList("Casa","Masina","Frigider"),"Masina"));

        List<Integer> weights2 = Arrays.asList(7,3,4,10);
        List<String> colors = Arrays.asList("red","green","red","green");


        Function<Integer, Integer> f = x -> x+1;
        Function<Integer,Integer> g = x -> x*2;
        Function<Integer,Integer> h = f.andThen(g);
        Function<Integer,Integer> h2 = f.compose(g);
        int rez  = h.apply(1);
        int rez2 = h2.apply(1);

        System.out.println(rez);
        System.out.println(rez2);


    }

    public static  int simpleMethodSum(Simple simple,int x,int suma)
    {
        return  suma+simple.op(x);
    }

    public static  int simpleMethod(Simple simple,int x)
    {
        return  simple.op(x);
    }

    public static  String processFile(BufferedReaderProcessor p,String fileName) throws IOException{

    try(BufferedReader br =
                new BufferedReader(new FileReader(fileName))) {
                return p.process((br));
    }

    }

    public static  int executeOperation(SimpleOperation simple,int a,int b){
        return simple.execute(a,b);
    }

    public static int executeOperationList(SimpleListOperation simpleListOperation,List<Integer> list)
    {
        return  simpleListOperation.execute(list);
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for(T i :list) {
            c.accept(i);
        }
    }

    public static <T,R>List<R> map(List<T> list, Function<T,R>f) {
        List<R> result = new ArrayList<>();
        for(T s:list){
            result.add(f.apply(s));
        }
        return result;
    }


}
