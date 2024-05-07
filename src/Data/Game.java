package Data;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    private Connection conn;
    private Player play;
    private boolean playGame;

    public Game(Connection conn, Player play) {
        this.conn = conn;
        this.play = play;
    }

    public boolean getPlayGame() {
        return playGame;
    }

    public void setPlayGame(boolean playGame) {
        this.playGame = playGame;
    }

    private static String getMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public void checkPlayerExistence() {
        String playerName = play.getPlayerName();
        String checkQuery = "SELECT * FROM NguoiDung WHERE Name = ?";
        try (PreparedStatement checkStatement = conn.prepareStatement(checkQuery)) {
            checkStatement.setString(1, playerName);

            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (resultSet.next()) {
                    int choice = JOptionPane.showConfirmDialog(null,
                            "Tên người chơi đã có bạn có muốn tiếp tục với tên này?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        String storedPassword = resultSet.getString("Pass");
                        if (storedPassword != null) {
                            boolean passwordCorrect = false;

                            while (!passwordCorrect) {
                                JPasswordField passwordField = new JPasswordField();
                                Object[] message = {"Vui lòng nhập mật khẩu:", passwordField};

                                int option = JOptionPane.showConfirmDialog(null, message, "Nhập mật khẩu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                                if (option == JOptionPane.OK_OPTION) {
                                    char[] enteredPasswordChars = passwordField.getPassword();
                                    String enteredPassword = new String(enteredPasswordChars);

                                    if (!enteredPassword.isEmpty() && getMD5Hash(enteredPassword).equals(storedPassword)) {
                                        passwordCorrect = true;
                                        playGame = true;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Mật khẩu không chính xác. Vui lòng thử lại.");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Bạn đã hủy nhập mật khẩu.");
                                    playGame = false;
                                    break;
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Không tìm thấy mật khẩu.");
                            playGame = false;
                        }
                    } else {
                        playGame = false;
                    }
                } else {
                    addNewPlayer(playerName);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage());
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    private void addNewPlayer(String playerName) {
        String insertQuery = "INSERT INTO NguoiDung (Name, Pass) VALUES (?, ?)";
        JOptionPane.showMessageDialog(null, "Lần đầu tiên đăng nhập hãy nhập mật khẩu");
        try (PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {
            insertStatement.setString(1, playerName);

            JPasswordField passwordField = new JPasswordField();
            Object[] message = {"Vui lòng nhập mật khẩu:", passwordField};

            int option = JOptionPane.showConfirmDialog(null, message, "Nhập mật khẩu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                char[] enteredPasswordChars = passwordField.getPassword();
                String enteredPassword = new String(enteredPasswordChars);

                if (!enteredPassword.isEmpty()) {
                    insertStatement.setString(2, getMD5Hash(enteredPassword));
                    int rowsAffected = insertStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Đăng ký thành công.");
                        playGame = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Không thể thêm tên người chơi mới.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Mật khẩu không được để trống. Thêm người chơi không thành công.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Bạn đã hủy nhập mật khẩu.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm người chơi mới vào cơ sở dữ liệu: " + e.getMessage());
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
