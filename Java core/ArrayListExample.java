import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String name;

        System.out.println("Enter student names (type 'end' to finish):");
        while (true) {
            System.out.print("Enter name: ");
            name = scanner.nextLine();
            if (name.equalsIgnoreCase("end")) {
                break;
            }
            students.add(name);
        }

        System.out.println("\nStudent List:");
        for (String student : students) {
            System.out.println(student);
        }

        scanner.close();
    }
}
