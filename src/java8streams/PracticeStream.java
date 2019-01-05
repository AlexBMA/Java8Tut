package java8streams;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PracticeStream {

    public static void main(String[] args)
    {
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactionList = Arrays.asList(
            new Transaction(brian,2011,300),
            new Transaction(raoul,2012,1000),
            new Transaction(raoul,2011,400),
            new Transaction(mario,2012,710),
            new Transaction(mario,2012,700),
            new Transaction(alan,2012,950)
        );

        //Ex1 Find all transactions in the year
        List<Transaction> rezEx1 = transactionList.stream()
                .filter(x->x.getYear()==2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        rezEx1.forEach(x->System.out.println(x));
        //Ex2 What are all the unique cities where the
        // traders work

        List<String> listRez2 = transactionList.stream()
                                .map(x->x.getTrader().getCityName())
                                .distinct()
                                .collect(Collectors.toList());
        listRez2.forEach(x->System.out.println(x));

        //Ex3 Find all the traders from Cambridge
        // and sort them by name

        List<Trader> listRez3 =transactionList.stream()
                            .filter(x->x.getTrader().getCityName().equalsIgnoreCase("Cambridge"))
                            .map(x->x.getTrader())
                            .distinct()
                            .sorted((x,y)->x.getName().compareToIgnoreCase(y.getName()))
                            .collect(Collectors.toList());
        listRez3.forEach(x->System.out.println(x));

        //Ex4 Return a string of all traders' names
        //sorted alphabetically

        String rez4 = transactionList.stream()
                        .map(x->x.getTrader().getName())
                        .distinct()
                        .sorted((x,y)->x.compareToIgnoreCase(y))
                        .reduce("",(s1,s2)->s1+" "+s2);
        System.out.println(rez4);

        //Ex5 Are there any trades based in Milan

        boolean rez5 = transactionList.stream()
                            .anyMatch(x->x.getTrader()
                                            .getCityName()
                                            .equalsIgnoreCase("Milan")
                            );
        System.out.println(rez5);

        //Ex6 Print all transactions' values
        //from traders living in Cambridge

        transactionList.stream()
                .filter(x->x.getTrader().getCityName().equalsIgnoreCase("Cambridge"))
                .forEach(x->System.out.println(x.getValue()));

        //Ex7 What is the highest value of all transaction
        Optional<Integer> max = transactionList.stream()
                        .map(x->x.getValue())
                        .reduce(Integer::max);
        System.out.println("MAX: "+max.get());
        //Ex8 Find the transaction with the smallest value

        Optional<Transaction> rez8 = transactionList.stream()
                            .sorted(Comparator.comparing(Transaction::getValue))
                            .findFirst();

        System.out.println(rez8.get());
    }
}
