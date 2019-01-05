package com.alex.lambdaExpresionExercises;

import com.alex.paramBehaviorP2.Predicate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main2 {


    public  static void main(String[] args)
    {
        List<Person> people = Arrays.asList(
                new Person("Andrei","Popescu",20),
                new Person("Vasile","Berbec",25),
                new Person("Stefan","Manole",24),
                new Person("Olga","Ionescu",22),
                new Person("Maria","Paunescu",19)
        );

        //step 1 sort list by last name

        people.sort(Comparator.comparing(Person::getLastName));
        System.out.println(people);

        System.out.println("##");
        people.forEach(p->System.out.println(p));
        //step2 create a method that prints all elements in list

        System.out.println("###");
        // step 3 create a method that prints all elements beginning with p


        printWithCondition(people,p->p.getLastName().startsWith("P"));
        //System.out.println(people);

    }

    public static  void printWithCondition(List<Person> list,Predicate<Person> p)
    {
        for(Person person:list)
        {
            if(p.test(person)) System.out.println(person);
        }
    }
}
