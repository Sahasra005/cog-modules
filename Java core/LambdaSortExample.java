import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaSortExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Zara", "Mike");

        System.out.println("Before Sorting:");
        names.forEach(System.out::println);

        Collections.sort(names, (a, b) -> a.compareToIgnoreCase(b));

        System.out.println("\nAfter Sorting:");
        names.forEach(System.out::println);
    }
}
