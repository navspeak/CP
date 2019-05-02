package dataprocessing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
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


        try(Stream<String> streamOfLines = Files.lines(Paths.get("src/People.txt"));){
            final List<List<String>> listOfLineEntry = streamOfLines.filter(A0::notACommentLine)
                    .map(A0::getLineContentAsList)
                    .collect(Collectors.toList());
            System.out.println(listOfLineEntry);
            List<Person> persons = new ArrayList<>();
            listOfLineEntry.stream()
                    .map(t->{

                        String name = t.get(0);
                        Integer age = Integer.valueOf(t.get(1).trim());
                        String gender = t.get(2);
                        return new Person(name, age, gender);
                    })
                    .forEach(persons::add);
            Comparator<Person> compareAgeAndThenName = Comparator.comparing(Person::getAge, Comparator.reverseOrder())
                    .thenComparing(Person::getName);
            persons.sort(compareAgeAndThenName);

            persons.forEach(System.out::println);





        } catch (IOException e) {
            e.printStackTrace();

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

        List<String> res = new ArrayList<>();
        final Stream<String> peek = Stream.of("A", "AB", "C", "AD")
                .peek(System.out::println)
                .filter(x -> x.startsWith("A"))
                .peek(result::add);// does nothing as peek is intermediary

        peek.forEach(c1); // foreach is terminal (final)

        // question what is the correct output:
        // a) A \n A \n AB \n AB \n C \n AD \n AD
        // b) A \n AB \n C \n AD \n A \n AB \AD
        // ans - a. think why

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2= Arrays.asList(10, 20, 30);
        List<Integer> list3 = Arrays.asList(100, 200, 300, 400, 500);

        /*
        <R> Stream<R> map(Function<T, R> mapper);
        <R> Stream<R> flatMap(Function<T, Stream<R>>);
         */
        List<List<Integer>> totList = Arrays.asList(list1,list2,list3);
        Function<List<?>, Integer> sizeMap = List::size;
        Function<List<Integer>, Stream<Integer>> flatMapper = l -> l.stream();

        System.out.println("---");
        totList.stream().map(sizeMap).forEach(System.out::println);
        System.out.println("---");
        totList.stream().map(flatMapper).forEach(System.out::println);
        System.out.println("---");
        totList.stream().flatMap(flatMapper).forEach(System.out::println);

        List<String> strings = Arrays.asList("one", "two", "three", "four", "five");
        //strings is unmodifable
        Collection<String> col = new ArrayList<>(strings);
        col.removeIf(s -> s.length() > 3);
        System.out.println(col.stream().collect(Collectors.joining(", ")));
        List<String> list_1 = new ArrayList<>(strings);
        list_1.replaceAll(String::toUpperCase);
        System.out.println(list_1.stream().collect(Collectors.joining(", ")));


    }

    static boolean notACommentLine(String line) { return !line.startsWith("#");}
    static List<String> getLineContentAsList(String line) { return Arrays.asList(line.split(" "));}



}
