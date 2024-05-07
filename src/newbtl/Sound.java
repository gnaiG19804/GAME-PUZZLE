package newbtl;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Sound {
    private Clip clip;

    public Sound() {
    }

    public void playSoundMove() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String filepath = "src\\newbtl\\sound\\move.wav";
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath))) {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
    }

    public void playMusicLoop() {
        try {
            String filepath = "src\\newbtl\\sound\\music.wav";
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath))) {
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip != null && clip.isOpen()) {
            clip.stop();
            clip.close();
        }
    }
}
