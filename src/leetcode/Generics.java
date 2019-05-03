package leetcode;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics {

    //https://medium.com/@isuru89/java-producer-extends-consumer-super-9fbb0e7dd268

    public <T extends Comparable<T> > int compare(T a, T b) {
        return a.compareTo(b);
    }

    public static void main(String[] args) {

        System.out.println((Math.ceil(12.5)));
        //unbounded Wildcard
        List<?> list1;
        list1 = new ArrayList<Integer>();
        for (Object i : list1) { };
        // list1.add(8); => compile error

        //UPPER BOUNDED WILDCARDS
        //PRODUCER EXTENDS => read
        // If we want to read T but not write T
        // List<? extends T>
        List<? extends Number> list2;
        list2 = new ArrayList<Integer>();
        List<Integer> listOfInteger = Arrays.asList(1,2,3);
        List<BigInteger> listOfBigInteger = Arrays.asList(BigInteger.ONE, BigInteger.TEN);
        show1(listOfInteger);
        show1(listOfBigInteger);

        // list2.add(8);  => compile error
        for (Number i : list2) { };
        //for (Integer i : list2) { }; => compile error
        show1(list2);
        list2= new ArrayList<Float>();
        show1(list2);

        //LOWER BOUNDED WILDCARDS
        // CONSUMER SUPER => Write
        // if we want to add T but not read
        List<? super Integer> list3 = null;
        //list3 = new ArrayList<Number>();
        //Integer x = list3.get(0);
        list3.add(8); // => No compile error
        // for (Number i : list3) { }; => Compile error
        for (Object i : list3) { };

        List<Serializable> l = new ArrayList<>();
        l.add("abc");
        l.add("efg");
        show(l);

        // If we want to add and read at same time - no wildcard
        //============TYPE ERASURE ====================//
//		List<Integer> l2 = new ArrayList<>();
//		l2.get(0)
//		------is equivalent to following in ByteCode ---------
//		List l2 = new ArrayList();
//		(Integer)l2.get(0)

// Read also Bridge Methods
    }

    public static void show( List<? super Number> list) {
        list.add(1.8);
        for (Object o: list) {
            System.out.println(o);
        }
        //list.stream().forEach(System.out::println);
    }


    public static void show1( List<? extends Number> list) {
        //list.add(1.8);
        for (Number o: list) {
            System.out.println(o);
        }

        //list.stream().forEach(System.out::println);
    }

}
