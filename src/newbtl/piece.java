package newbtl;

import java.util.Random;
import javax.swing.Icon;
import javax.swing.JButton;
import static newbtl.newbtl.counter;

public class piece {
    
    
    public int[] shuffle(){
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
    
    
    public static void checkMove(JButton b1, JButton b2) {
        Icon icon = b2.getIcon();
        if (icon == null) {
            b2.setIcon(b1.getIcon());
            b1.setIcon(null);
            counter++;
        }
    }
}
