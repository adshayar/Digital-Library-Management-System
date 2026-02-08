import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/task_five",
                    "root",
                    "Adshaya@5232"
            );
        } catch (Exception e) {
            return null;
        }
    }
}
