import java.util.HashMap;
import java.util.Scanner;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> studentMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student data (type -1 to stop):");
        while (true) {
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // consume newline
            if (id == -1) break;

            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            studentMap.put(id, name);
        }

        System.out.print("\nEnter ID to retrieve name: ");
        int searchId = scanner.nextInt();
        String student = studentMap.get(searchId);
        if (student != null) {
            System.out.println("Student Name: " + student);
        } else {
            System.out.println("No student found with that ID.");
        }

        scanner.close();
    }
}
