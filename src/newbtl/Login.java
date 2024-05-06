package newbtl;

import javax.swing.JOptionPane;
import static newbtl.PuzzleGame.name;
import static newbtl.PuzzleGame.showMenu;

public class Login {
    private Game game;
    private Player play;

    public Login(Game game, Player play) {
        this.game = game;
        this.play = play;
        loginProcess();
    }

    private void loginProcess() {
        boolean validNameEntered = false;
        String playerName = null;

        while (!validNameEntered) {
            playerName = JOptionPane.showInputDialog(null, "Nhập tên của bạn:");

            if (playerName == null) {
                int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn nhập lại tên không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    continue;
                } else {                    
                    showMenu();
                    break;
                }
            } else if (!playerName.isEmpty()) {
                validNameEntered = true;
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tên của bạn!");
            }
        }

        if (validNameEntered) {
            if (game != null) {
                play.setPlayerName(playerName);
                name = play.getPlayerName();
                game.checkPlayerExistence();
            } else {
                System.out.println("game is null");
            }
        }
    }
}
