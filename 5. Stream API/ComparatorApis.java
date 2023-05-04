import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import _core.Deities;
import _core.Pantheon;
import _core.Util;

public class ComparatorApis {
    public static void main(String[] args) {
        List<Pantheon> list = Util.allPantheonSupplier.get();
        List<Deities> allDeities = list.stream().map(Pantheon::getDeities).flatMap(List::stream).collect(Collectors.toList());
        
        // Example 15
        Comparator<Deities> powerComparator = Comparator.comparing(Deities::getPower);
        Comparator<Deities>  nameComparator = Comparator.comparing(Deities::getName);

        allDeities.sort(powerComparator.thenComparing(nameComparator));
        System.out.println(allDeities);
    }
}
