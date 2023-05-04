package _core;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Util {

    public static Predicate<Deities> isPowerfullPredicate = god -> god.getPower() > 9;
    // public Predicate<Deities> isNordic = god -> god.() > 9;

    public static Supplier<List<Deities>> norseDeitiesSupplier = () -> {
        return Arrays.asList(new Deities("Odin", 10, Pantheon.Culture.NORSE), new Deities("Thor", 9, Pantheon.Culture.NORSE), new Deities("Loki", 8, Pantheon.Culture.NORSE),
                new Deities("Frigg", 7, Pantheon.Culture.NORSE));
    };

    public static Supplier<List<Deities>> egyptianDeitiesSupplier = () -> {
        return Arrays.asList(new Deities("Osiris", 10, Pantheon.Culture.EGYPTIAN), new Deities("Isis", 9, Pantheon.Culture.EGYPTIAN), new Deities("Horus", 8, Pantheon.Culture.EGYPTIAN),
                new Deities("Seth", 7, Pantheon.Culture.EGYPTIAN));
    };

    public static Supplier<List<Deities>> greekDeitiesSupplier = () -> {
        return Arrays.asList(new Deities("Zeus", 10, Pantheon.Culture.GREEK), new Deities("Hera", 9, Pantheon.Culture.GREEK), new Deities("Poseidon", 8, Pantheon.Culture.GREEK),
                new Deities("Aphrodite", 7, Pantheon.Culture.GREEK));
    };

    public static Supplier<Pantheon> norsePantheonSupplier = () -> {
        return new Pantheon(Pantheon.Culture.NORSE, norseDeitiesSupplier.get());
    };

    public static Supplier<Pantheon> greekPantheonSupplier = () -> {
        return new Pantheon(Pantheon.Culture.GREEK, greekDeitiesSupplier.get());
    };

    public static Supplier<Pantheon> egyptianPantheonSupplier = () -> {
        return new Pantheon(Pantheon.Culture.EGYPTIAN, egyptianDeitiesSupplier.get());
    };

    public static Supplier<List<Pantheon>> allPantheonSupplier = () -> {
        return Arrays.asList(norsePantheonSupplier.get(), egyptianPantheonSupplier.get(), greekPantheonSupplier.get());
    };

    public static Optional<Pantheon> optionalPantheon = Optional.of( new Pantheon(Pantheon.Culture.EGYPTIAN, egyptianDeitiesSupplier.get()) );
}
