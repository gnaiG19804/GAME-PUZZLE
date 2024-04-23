package newbtl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class time {
    private javax.swing.JLabel jLabel3;
    private int elapsedTime = 0;
    private Timer timer;

    public time(JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }

    public void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                updateTimeLabel();
            }
        });
        timer.start();
    }

    public void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }
    
    private void updateTimeLabel() {
        int hours = elapsedTime / 3600;
        int minutes = (elapsedTime % 3600) / 60;
        int seconds = elapsedTime % 60;

        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        jLabel3.setText(timeString);
    }
    
    public void resetTime() {
        this.elapsedTime = 0;
        updateTimeLabel();
    }
}
