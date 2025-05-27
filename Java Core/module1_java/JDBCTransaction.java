import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCTransaction {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:bank.db"; // Adjust for MySQL if needed
        String debitSQL = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url)) {
            conn.setAutoCommit(false);

            try (PreparedStatement debit = conn.prepareStatement(debitSQL);
                 PreparedStatement credit = conn.prepareStatement(creditSQL)) {

                debit.setInt(1, 500);
                debit.setInt(2, 1);
                debit.executeUpdate();

                credit.setInt(1, 500);
                credit.setInt(2, 2);
                credit.executeUpdate();

                conn.commit();
                System.out.println("Transaction successful.");
            } catch (Exception e) {
                conn.rollback();
                System.out.println("Transaction failed. Rolled back.");
            }

        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}
