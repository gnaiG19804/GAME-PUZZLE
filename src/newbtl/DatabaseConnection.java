package newbtl;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String SERVER = "LAPTOP-25H9QNQ2\\SQLEXPRESS";
    private static final String USER = "sa";
    private static final String PASSWORD = "giang19082004";
    private static final String DATABASE = "game";
    private static final int PORT = 1433;

    private static Connection conn;

    private DatabaseConnection() {

    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                SQLServerDataSource ds = new SQLServerDataSource();
                ds.setUser(USER);
                ds.setPassword(PASSWORD);
                ds.setDatabaseName(DATABASE);
                ds.setServerName(SERVER);
                ds.setPortNumber(PORT);
                ds.setTrustServerCertificate(true);

                conn = ds.getConnection();
                System.out.println("Kết nối đến cơ sở dữ liệu thành công!"+conn.getCatalog());
            } catch (SQLException e) {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu: " + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn == null) {
            try {
                conn.close();
                System.out.println("Đã đóng kết nối đến cơ sở dữ liệu.");
            } catch (SQLException e) {
                System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
            }
        }
    }
}