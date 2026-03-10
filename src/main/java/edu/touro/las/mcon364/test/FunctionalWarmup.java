package edu.touro.las.mcon364.test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalWarmup {

    /**
     * Problem 1
     * Return a Supplier that gives the current month number (1-12).
     */
    public static Supplier<Integer> currentMonthSupplier() {
        return () -> LocalDate.now().getMonthValue();

    }

    /**
     * Problem 2
     * Return a Predicate that is true only when the input string
     * has more than 5 characters.
     */
    public static Predicate<String> longerThanFive() {
        return x -> x.length() > 5;
    }

    /**
     * Problem 3
     * Return a Predicate that checks whether a number is both:
     * - positive
     * - even
     * <p>
     * Prefer chaining smaller predicates.
     */
    public static Predicate<Integer> positiveAndEven() {
        return x -> x > 0 && x % 2 == 0;
    }

    /**
     * Problem 4
     * Return a Function that counts words in a string.
     * <p>
     * Notes:
     * - Trim first.
     * - Blank strings should return 0.
     * - Words are separated by one or more spaces (use can use regex "\\s+")
     *
     */
    public static Function<String, Integer> wordCounter() {
        Function<String, String> trim = String::trim;
        Predicate<String> testIf0 = String::isBlank;
        Function<String, String[]> split = s -> s.split("\\s+");

        return x -> { x= trim.apply(x);
            if(testIf0.test(x)){
                return 0;
            }
          //after you split them, you count up the words in the array- each word gets its own index
               return split.apply(x).length;



        };
    }

    /**
     * Problem 5
     * Process the input labels as follows:
     * - remove blank strings
     * - trim whitespace
     * - convert to uppercase
     * - return the final list in the same relative order
     * <p>
     * Example:
     * ["  math ", "", " java", "  "] -> ["MATH", "JAVA"]
     */
    public static List<String> cleanLabels(List<String> labels) {
        Predicate<String> testIf0 = String::isBlank;
        Function<String, String> trim = String::trim;
        Function<String, String> upperCase = String::toUpperCase;
        Consumer<List<String>> editList = x -> {
            for (String s : x) {
                s = trim.apply(s);
                if (!testIf0.test(s)) {
                    s = upperCase.apply(s);
                } else {
                    x.remove(s);
                }
            }
        };
        editList.accept(labels);
        return  labels;
    }
}