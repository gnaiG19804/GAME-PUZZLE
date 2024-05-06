package newbtl;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Helper {

    public static void useHelp(BufferedImage[] imageP, JButton[] buttons, int counter, Player player, String name, time timer, piece pe, JLabel jLabelNumOfClicks) {
        timer.stopTimer();
        timer.resetTime();
        ImageIcon[] icons = new ImageIcon[8];
        for (int i = 0; i < 8; i++) {
            icons[i] = new ImageIcon(imageP[i]);
        }

        for (int i = 0; i < 8; i++) {
            buttons[i].setIcon(icons[i]);
        }
        buttons[8].setIcon(null);
        JOptionPane.showMessageDialog(null, "Bạn đã dùng trợ giúp để hoàn thành, thành tích của bạn sẽ không được tính!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//        player.setFinishTime();
//        player.setClickCount(counter);
//        player.setCompletionTime();
//        GameRecods records = new GameRecods();
//        records.savePlayerAchievement(name, player.getClickCount(), player.getCompletionTime());
//        jLabelNumOfClicks.setText(Integer.toString(0));
//        timer.startTimer();
        pe.shuffle();
        player.setStartTime();
    }
}

