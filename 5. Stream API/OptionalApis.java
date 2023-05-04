import java.util.Optional;

import _core.Pantheon;
import _core.Util;

public class OptionalApis {
    public static void main(String[] args) {
        // Example 13
        Optional<String> empty = Optional.empty();
        Optional<String> ofNotNull = Optional.of("name"); // can't pass null value, will throw NPE
        Optional<String> ofNull = Optional.ofNullable(null); // might pass null value as well


        System.out.println(empty.isPresent());
        System.out.println(ofNotNull.isPresent());
        System.out.println(ofNull.isEmpty());

        // Example 14
        Optional<String> opt = Optional.of("Jardel Kuhn");
        opt.ifPresent(name -> System.out.println(name.length()));
    }
}
