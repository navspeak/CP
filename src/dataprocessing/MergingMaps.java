package dataprocessing;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Credit to
 * @author José
 * Pluralsight
 */
public class MergingMaps {

    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();

        try (
//                BufferedReader reader =
//                        new BufferedReader(
//                                new InputStreamReader(
//                                        MergingMaps.class.getResourceAsStream("People.txt")));


                Stream<String> stream = Files.lines(Paths.get("src/People.txt"));
        ) {

            stream.map(line -> {
                String[] s = line.split(" ");
                Person p = new Person(
                        s[0].trim(),
                        Integer.valueOf(s[1]),
                        s[2].trim()
                );
                persons.add(p);
                return p;
            })
                    .forEach(System.out::println);


        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        //persons.forEach(System.out::println);

        List<Person> list1 = persons.subList(1, 10);
        List<Person> list2 = persons.subList(10, persons.size());

        Map<Integer, List<Person>> map1 = mapByAge(list1);
        System.out.println("Map1 : ");
        map1.forEach((k,v)-> System.out.println(k + "--> " + v));

        Map<Integer, List<Person>> map2 = mapByAge(list2);
        System.out.println("Map2 : ");
        map2.forEach((age,list)-> System.out.println(age + "--> " + list));

        map1.entrySet().stream().forEach(
                entry ->
                        map1.merge(
                                entry.getKey(),
                                entry.getValue(),
                                (l1, l2) -> {
                                    l1.addAll(l2);
                                    return l1;
                                }
                        )
        );

        System.out.println("Map1 Merged: ");
        map1.forEach((k,v)-> System.out.println(k + "--> " + v));

    }

    private static Map<Integer, List<Person>> mapByAge(List<Person> list) {
        final Map<Integer, List<Person>> map = list.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        return map;
    }


}