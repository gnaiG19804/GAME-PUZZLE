package newbtl;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Game {
    private Connection conn;
    private Player play;

    public Game(Connection conn, Player play) {
        this.conn = conn;
        this.play = play;
    }

    public void checkPlayerExistence() {
        String playerName = play.getPlayerName();
        String checkQuery = "SELECT * FROM NguoiDung WHERE Name = ?";
        try (PreparedStatement checkStatement = conn.prepareStatement(checkQuery)) {
            checkStatement.setString(1, playerName);

            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next()) {
                    int choice = JOptionPane.showConfirmDialog(null,
                            "Tên người chơi đã có trong cơ sở dữ liệu. Tiếp tục với tên này?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION);

                    if (choice == JOptionPane.YES_OPTION) {
                        boolean passwordCorrect = false;
                        while (!passwordCorrect) {
                            String enteredPassword = JOptionPane.showInputDialog(null, "Vui lòng nhập mật khẩu:");
                            String storedPassword = resultSet.getString("Pass");
                            if (enteredPassword != null && enteredPassword.trim().equals(storedPassword.trim())) {
                                passwordCorrect = true;
                                startGame();
                            } else {
                                JOptionPane.showMessageDialog(null, "Mật khẩu không chính xác. Vui lòng thử lại.");
                            }
                        }
                    } else {
                        String newPlayerName = JOptionPane.showInputDialog(null, "Nhập lại tên của bạn:");
                        if (newPlayerName != null && !newPlayerName.isEmpty()) {
                            addNewPlayer(newPlayerName);
                        } else {
                            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên người chơi.");
                        }
                    }
                } else {
                    addNewPlayer(playerName);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage());
        }
    }

    private void addNewPlayer(String playerName) {
        String insertQuery = "INSERT INTO NguoiDung (Name) VALUES (?)";
        try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
            insertStatement.setString(1, playerName);

            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu cho những lần sau: " + playerName);

                String enteredPassword = JOptionPane.showInputDialog(null, "Vui lòng nhập mật khẩu:");
                if (enteredPassword != null && !enteredPassword.isEmpty()) {
                    savePlayerPassword(playerName, enteredPassword);
                } else {
                    JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống. Thêm người chơi không thành công.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Không thể thêm tên người chơi mới.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm người chơi mới vào cơ sở dữ liệu: " + e.getMessage());
        }
    }

    private void savePlayerPassword(String playerName, String password) {
        String updateQuery = "UPDATE NguoiDung SET Pass = ? WHERE Name = ?";
        try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
            updateStatement.setString(1, password);
            updateStatement.setString(2, playerName);

            int rowsAffected = updateStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Lưu mật khẩu cho người chơi mới thành công.");
                startGame();
            } else {
                playerName = JOptionPane.showInputDialog(null, "Không thể thêm tên người chơi mới. Vui lòng nhập lại tên:");
                if (playerName == null || playerName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Bạn đã hủy thêm người chơi mới.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lưu mật khẩu cho người chơi mới: " + e.getMessage());
        }
    }

    public void startGame() {
        JOptionPane.showMessageDialog(null, "Bắt đầu trò chơi với tên đã chọn: " + play.getPlayerName());
        new PuzzleGame().setVisible(true);
    }
}
