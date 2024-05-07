package newbtl;



import newbtl.Game;
import javax.swing.JOptionPane;
import static newbtl.PuzzleGame.name;

public class Login {
    private Game game;
    private Player play;
    private boolean menuOn;

    public Login(Game game, Player play) {
        this.game = game;
        this.play = play;
        loginProcess();
    }
    public boolean getOnGame(){
        return menuOn;
    }

    private void loginProcess() {
        boolean validNameEntered = false;
        String playerName = null;

        while (!validNameEntered) {
            playerName = JOptionPane.showInputDialog(null, "Nhập tên của bạn:");
            if (playerName == null) {
                int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn quay lại menu không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    menuOn=true;
                    return; 
                } else {
                    menuOn=false;
                    continue;
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

