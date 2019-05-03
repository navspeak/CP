package dataprocessing;

import java.math.BigInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FibonacciUsingStream {

    private static long number = 1000;
    public static void main(String[] args) {


        Stream.iterate(new BigInteger[]{BigInteger.ZERO, BigInteger.ONE}, x -> new BigInteger[]{x[1], x[0].add(x[1])})
                .limit(15)
                .map(x-> x[1])
                .forEach(System.out::println);


        final Stream<Integer> streamOfIntegers = Stream.iterate(new int[]{0, 1}, x -> new int[]{x[1], x[0] + x[1]})
                .map(x -> x[1])
                .limit(15);
        String result = streamOfIntegers.map(x->Integer.toString(x)).collect(Collectors.joining(", "));

        System.out.println(result);

    }

}


