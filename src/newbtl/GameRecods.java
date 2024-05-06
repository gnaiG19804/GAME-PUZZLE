package newbtl;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameRecods {

    public void savePlayerAchievement(String playerName, int clickCount, long completionTime) {
        Connection conn = null;
        PreparedStatement insertStatement = null;

        try {
            conn = DatabaseConnection.getConnection();

            if (!playerExists(playerName, conn)) {
                // Người chơi chưa tồn tại trong cơ sở dữ liệu, thêm thông tin mới
                String insertQuery = "INSERT INTO ThanhTich (Name, Click, Time) VALUES (?, ?, ?)";
                insertStatement = conn.prepareStatement(insertQuery);
                insertStatement.setString(1, playerName);
                insertStatement.setInt(2, clickCount);
                insertStatement.setLong(3, completionTime / 1000); // Chia cho 1000 để chuyển đổi sang giây

                int rowsAffected = insertStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Thông tin thành tích của người chơi đã được lưu.");
                } else {
                    JOptionPane.showMessageDialog(null, "Không thể lưu thông tin thành tích của người chơi.");
                }
            } else {
                // Người chơi đã tồn tại trong cơ sở dữ liệu, kiểm tra thời gian hoàn thành
                long currentBestTime = getPlayerBestCompletionTime(playerName, conn);
                if (completionTime / 1000 < currentBestTime) {
                    // Thời gian hoàn thành mới là tốt nhất, cập nhật thông tin
                    String updateQuery = "UPDATE ThanhTich SET Click = ?, Time = ? WHERE Name = ?";
                    insertStatement = conn.prepareStatement(updateQuery);
                    insertStatement.setInt(1, clickCount);
                    insertStatement.setLong(2, completionTime / 1000); // Chia cho 1000 để chuyển đổi sang giây
                    insertStatement.setString(3, playerName);

                    int rowsAffected = insertStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Thông tin thành tích của người chơi đã được cập nhật.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Không thể cập nhật thông tin thành tích của người chơi.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Thời gian hoàn thành không đủ tốt để cập nhật thông tin.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lưu/cập nhật thành tích của người chơi: " + e.getMessage());
        } finally {
            if (insertStatement != null) {
                try {
                    insertStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseConnection.closeConnection(conn);
        }
    }

    private long getPlayerBestCompletionTime(String playerName, Connection conn) throws SQLException {
        String selectQuery = "SELECT MIN(Time) FROM ThanhTich WHERE Name = ?";
        try (PreparedStatement selectStatement = conn.prepareStatement(selectQuery)) {
            selectStatement.setString(1, playerName);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                }
            }
        }
        return Long.MAX_VALUE;
    }

    private boolean playerExists(String playerName, Connection conn) throws SQLException {
        String selectQuery = "SELECT Name FROM ThanhTich WHERE Name = ?";
        try (PreparedStatement selectStatement = conn.prepareStatement(selectQuery)) {
            selectStatement.setString(1, playerName);
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}
