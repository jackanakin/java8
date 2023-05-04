import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import _core.Deities;
import _core.Pantheon;
import _core.Util;
import _core.Pantheon.Culture;

public class StreamApis {
    public static void main(String[] args) {
        List<Pantheon> list = Util.allPantheonSupplier.get();
        List<Deities> allDeities = list.stream().map(Pantheon::getDeities).flatMap(List::stream).collect(Collectors.toList());
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

        // Example 9
        double totalPower = allDeities.stream().collect(Collectors.summingDouble(Deities::getPower));
        double averagePower = allDeities.stream().collect(Collectors.averagingDouble(Deities::getPower));
        System.out.println("Example 9 -----------------------------------------------");
        System.out.println("totalPower: " + totalPower);
        System.out.println("averagePower: " + averagePower);
        System.out.println("---------------------------------------------------------");

        // Example 10
        Map<Culture, List<Deities>> cultureGrouping =  allDeities.stream().collect(Collectors.groupingBy(Deities::getCulture));
        Map<String, List<Deities>> customGrouping =  allDeities.stream().collect(Collectors.groupingBy( god -> god.getPower() > 9d ? "Powerfull" : "Weak"));
        
        Map<Culture,  Map<String,List<Deities>>> twoLevelGrouping =  allDeities.stream().collect(Collectors.groupingBy(Deities::getCulture, Collectors.groupingBy(god -> god.getPower() > 9d ? "Powerfull" : "Weak")));

        System.out.println("Example 10 -----------------------------------------------");
        System.out.println("cultureGrouping: " + cultureGrouping);
        System.out.println("customGrouping: " + customGrouping);
        System.out.println("twoLevelGrouping: " + twoLevelGrouping);
        System.out.println("---------------------------------------------------------");

        // Example 11
        Map<Boolean, List<Deities>> powerfullPartitioning = allDeities.stream().collect(Collectors.partitioningBy(Util.isPowerfullPredicate));
        

        System.out.println("Example 11 -----------------------------------------------");
        System.out.println("powerfullPartitioning: " + powerfullPartitioning);
        System.out.println("---------------------------------------------------------");

        // Example 12
        IntStream of = IntStream.of(1,2,3,4,5);
        Stream<Integer> boxed = of.boxed();
    }
}
