import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Define record
record Person(String name, int age) {}

public class RecordExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 25),
            new Person("Bob", 17),
            new Person("Charlie", 30)
        );

        System.out.println("All People:");
        people.forEach(System.out::println);

        System.out.println("\nPeople aged 18 and above:");
        List<Person> adults = people.stream()
                                    .filter(p -> p.age() >= 18)
                                    .collect(Collectors.toList());
        adults.forEach(System.out::println);
    }
}
