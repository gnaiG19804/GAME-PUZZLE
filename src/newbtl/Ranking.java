package newbtl;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ranking {

    private Connection conn;
    private JTable table;

    public Ranking(Connection conn, JTable table) {
        this.conn = conn;
        this.table = table;
    }
    
    public void updateRankingTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Top");
        model.addColumn("Tên Người Chơi");
        model.addColumn("Số Lần Click");
        model.addColumn("Thời Gian Hoàn Thành");

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ThanhTich ORDER BY time ASC,Click ASC")) {

            int top = 1;
            while (rs.next()) {
                String playerName = rs.getString("name");
                int clickCount = rs.getInt("click");
                String completionTime = rs.getString("time");

                model.addRow(new Object[]{top, playerName, clickCount, completionTime});
                top++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setModel(model);
    }
}
