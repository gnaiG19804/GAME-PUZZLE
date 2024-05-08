package Logic;


import Data.GameRecords;
import Data.Player;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static PUZZLE.PuzzleGame.name;


public class checkWin {
    private static Sound sound;
    private static Player player;
    public checkWin() {
        this.sound = Sound.getInstance();
        player=Player.getInstance();
    }
    
    public static void Won(JButton[] buttons, BufferedImage[] imageP, JLabel jLabelNumOfClicks, time timer, piece pe) {
        jLabelNumOfClicks.setText(Integer.toString(player.getClickCount()));
        if (isGameWon(buttons, imageP)) {
            sound.playSoundWin();
            GameRecords records = new GameRecords();
            timer.stopTimer();
            player.setFinishTime();
            JOptionPane.showMessageDialog(null, "Chúc mừng! Bạn đã chiến thắng!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            player.setCompletionTime();
            records.savePlayerAchievement(name, player.getClickCount(), player.getCompletionTime());
            pe.shuffle();
            timer.resetTime();
            timer.startTimer();
            player.setStartTime();
            player.setClickCount(0);
            jLabelNumOfClicks.setText(Integer.toString(player.getClickCount()));
            
        }
    }

    public static boolean isGameWon(JButton[] buttons, BufferedImage[] imageP) {
        for (int i = 0; i < buttons.length - 1; i++) {
            ImageIcon icon = (ImageIcon) buttons[i].getIcon();
            if (icon == null) {
                return false;
            }

            Image image = icon.getImage();
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();

            if (!bufferedImagesEqual(bufferedImage, imageP[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
            return false;
        }

        for (int y = 0; y < img1.getHeight(); y++) {
            for (int x = 0; x < img1.getWidth(); x++) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }
}
