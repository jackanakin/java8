import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import _core.Deities;
import _core.Pantheon;
import _core.Util;

public class StreamApis {
    public static void main(String[] args) {
        List<Pantheon> list = Util.allPantheonSupplier.get();
        List<Integer> integerList = Arrays.asList(1, 3, 5, 7);

        Predicate<Pantheon> isGreek = pantheon -> pantheon.getCulture().equals(Pantheon.Culture.GREEK);

        // Example 1
        Map<String, List<Deities>> greekDeitiesMap = list.stream()
                .filter(isGreek) // Stream<Deities>
                .collect(Collectors.toMap(Pantheon::getCultureName, Pantheon::getDeities)); // collects it to a Map
        System.out.println("Example 1 -----------------------------------------------");
        System.out.println("greekDeitiesMap  : " + greekDeitiesMap);
        System.out.println("---------------------------------------------------------");

        // Example 2
        List<Deities> deitiesList = list.stream() // Stream<Deities>
                .map(Pantheon::getDeities) // <Stream<List<Deities>>
                .flatMap(List::stream) // <Stream<String>
                .distinct() // removes duplicates
                .collect(Collectors.toList()); // collects it to a List.
        System.out.println("Example 2 -----------------------------------------------");
        System.out.println("deitiesList  : " + deitiesList);
        System.out.println("---------------------------------------------------------");

        // Example 3
        List<String> namesList = list.stream() // Stream<Pantheon>
                .peek((pantheon -> {
                    System.out.println("peeking:" + pantheon); // peek at pantheon object
                }))
                .map(Pantheon::getDeities) // <List<Deities>
                .peek(System.out::println) // peek at List<Deities>
                .flatMap(List::stream) // Stream<Deities>
                .map(Deities::getName) // Stream<String>
                .distinct() // removes duplicates
                .sorted() // sorting
                .collect(Collectors.toList()); // collects it to a list.
        System.out.println("Example 3 -----------------------------------------------");
        System.out.println("namesList  : " + namesList);
        System.out.println("---------------------------------------------------------");

        // Example 4
        List<Deities> orderedByName = list.stream() // Stream<Pantheon>
                .peek((pantheon -> {
                    System.out.println("peeking:" + pantheon); // peek at pantheon object
                }))
                .map(Pantheon::getDeities) // <List<Deities>
                .flatMap(List::stream) // Stream<Deities>
                .sorted(Comparator.comparing(Deities::getName)) // sorting
                .collect(Collectors.toList()); // collects it to a list.
        System.out.println("Example 4 -----------------------------------------------");
        System.out.println("orderedByName  : " + orderedByName);
        System.out.println("---------------------------------------------------------");

        // Example 5
        Optional<Integer> reduceValue = integerList.stream()
                .reduce((a, b) -> a * b); // performs multiplication for each element in the stream
        System.out.println("Example 5 -----------------------------------------------");
        System.out.println("reduceValue  : " + reduceValue.get());
        System.out.println("---------------------------------------------------------");

        // Example 6
        Integer reduceValueDefault = integerList.stream()
                .reduce(100, (a, b) -> a * b); // initial/default value

        System.out.println("Example 6 -----------------------------------------------");
        System.out.println("reduceValueDefault  : " + reduceValueDefault);
        System.out.println("---------------------------------------------------------");

        // Example 7
        String allNamesReduce = list.stream()
                .map(Pantheon::getDeities)
                .flatMap(List::stream)
                .map(Deities::getName)
                .reduce("", ( a, b ) -> a + " -> " + b );

        System.out.println("Example 7 -----------------------------------------------");
        System.out.println("allNamesReduce  : " + allNamesReduce);
        System.out.println("---------------------------------------------------------");

        // Example 8
        Optional<Integer> min = integerList.stream()
                //.reduce(0,Integer::min);
                .reduce((a,b)->(a<b) ? a : b);
        Optional<Integer> max = integerList.stream()
                //.reduce(0,(a,b)->(a>b) ? a : b);
                .reduce(Integer::max);

        System.out.println("Example 8 -----------------------------------------------");
        System.out.println("min: " + min.get() + " <- max: " + max.get());
        System.out.println("---------------------------------------------------------");
    }
}
