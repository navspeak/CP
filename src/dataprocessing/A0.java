package dataprocessing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class A0 {
    public static void main(String[] args) {
        List<String> myList = Stream.of("a", "b")
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(myList);

        List<List<String>> list = Arrays.asList(
                Arrays.asList("a", "1", "one"),
                Arrays.asList("b", "2", "two"));
        System.out.println(list);

        Stream<List<String>> streamOfList = list.stream();
        Stream<String> streamOfString = streamOfList.flatMap(Collection::stream);
        System.out.println(
                streamOfString.collect(Collectors.toList())
        );

        System.out.println(
                list.stream().flatMap(Collection::stream).collect(Collectors.toList())
        );

        String a = "abcdefg";
        IntStream intStream =  a.chars();
        intStream.forEach(System.out::println);



        Path path = Paths.get("/src/test.txt");
        try(Stream<String> streamOfLines = Files.lines(path);){
            final List<List<String>> listOfPersons = streamOfLines.filter(A0::notACommentLine)
                    .map(A0::getLineContentAsList)
                    .collect(Collectors.toList());
            List<Person> personList = new ArrayList<>();
//            listOfPersons.stream()
//                    .collect(Person::new,
        } catch (IOException e) {
            //e.printStackTrace();
        }

        /*
        java.util.function: 4 categories:
         Supplier: T get()
         Consumer: void accept(T t)
         BiConsumer: void accept(T t, U u)
         Predicate:  boolean test(T t)
         BiPredicate(T t, U u)
         Funtion<T, R>: R apply(T t)
         BiFunction<T, U, R>: R apply(T t, U u, R r);
            UnararyOperator<T> extends Function<T,T>
            BiOperator<T> extends BiFunction<T, T, T>
         */
        Supplier<Integer> supplier = () -> Integer.valueOf(10);
        Consumer<Integer> consumer = System.out::println;
        BiConsumer<String, Integer> biConsumer = System.out::printf;

        List<String> strs = Arrays.asList("one", "two", "three", "four");
        List<String> result = new ArrayList<>();
        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = result::add;
        strs.forEach(c1.andThen(c2));
        System.out.println("Size of result (should be 4) " + result.size());

        Predicate<String> p2 = Predicate.isEqual("two");
        Predicate<String> p3 = Predicate.isEqual("three");
        Stream.of("one","two", "three").filter(p2.or(p2)).forEach(c1);


    }

    static boolean notACommentLine(String line) { return !line.startsWith("#");}
    static List<String> getLineContentAsList(String line) { return Arrays.asList(line.split(","));}

}
