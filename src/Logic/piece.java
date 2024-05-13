package Logic;

import Data.Player;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class piece {
    private JButton[] buttons;
    private BufferedImage[] imageP;
    private static Sound sound;
    private static Player player;
    public piece(BufferedImage[] imageP,JButton[] buttons) {
        this.imageP = imageP;
        this.buttons=buttons;
        sound=Sound.getInstance();
        player=Player.getInstance();
    }
    
    public int[] ran(){
        int i = 1, j, row;
        int[] bnum = new int[9];
        boolean flag;

        do {
            Random rnd = new Random();
            row = rnd.nextInt(8);
            flag = false;

            for (j = 1; j < i; j++) {
                if (bnum[j] == row) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                bnum[i] = row;
                i++;
            }
        } while (i <= 8);
        return bnum;
    }
    
    
    public static void checkMove(JButton b1, JButton b2) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        Icon icon = b2.getIcon();
        if (icon == null) {
            b2.setIcon(b1.getIcon());
            b1.setIcon(null);
            player.setClickCount(player.getClickCount()+1);
            sound.playSoundMove();
        }
    }
     public void shuffle() {
        int[] bnum = ran();
        ImageIcon icon1 = new ImageIcon(imageP[bnum[1]]);
        ImageIcon icon2 = new ImageIcon(imageP[bnum[2]]);
        ImageIcon icon3 = new ImageIcon(imageP[bnum[3]]);
        ImageIcon icon4 = new ImageIcon(imageP[bnum[4]]);
        ImageIcon icon5 = new ImageIcon(imageP[bnum[5]]);
        ImageIcon icon6 = new ImageIcon(imageP[bnum[6]]);
        ImageIcon icon7 = new ImageIcon(imageP[bnum[7]]);
        ImageIcon icon8 = new ImageIcon(imageP[bnum[8]]);
        buttons[0].setIcon(icon1);
        buttons[1].setIcon(icon2);
        buttons[2].setIcon(icon3);
        buttons[3].setIcon(icon4);
        buttons[4].setIcon(icon5);
        buttons[5].setIcon(icon6);
        buttons[6].setIcon(icon7);
        buttons[7].setIcon(icon8);
        buttons[8].setIcon(null);
    }
}
