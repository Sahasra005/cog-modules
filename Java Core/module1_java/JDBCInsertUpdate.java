import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

class StudentDAO {
    private Connection getConnection() throws Exception {
        return DriverManager.getConnection("jdbc:sqlite:students.db"); // Adjust for MySQL if needed
    }

    public void insertStudent(int id, String name, int marks) {
        String sql = "INSERT INTO students (id, name, marks) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, marks);
            ps.executeUpdate();
            System.out.println("Student inserted successfully.");
        } catch (Exception e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }

    public void updateMarks(int id, int newMarks) {
        String sql = "UPDATE students SET marks = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newMarks);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Student marks updated.");
        } catch (Exception e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }
}

public class JDBCInsertUpdate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        System.out.print("Enter ID, Name, Marks: ");
        int id = scanner.nextInt();
        String name = scanner.next();
        int marks = scanner.nextInt();
        dao.insertStudent(id, name, marks);

        System.out.print("Enter ID to update and new marks: ");
        int uid = scanner.nextInt();
        int newMarks = scanner.nextInt();
        dao.updateMarks(uid, newMarks);
    }
}
