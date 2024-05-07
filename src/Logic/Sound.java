package Logic;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class Sound {
    private static Sound instance;
    private Clip clip;
    private boolean soundOn;

    private Sound() {
        this.soundOn = true;
    }

    public static Sound getInstance() {
        if (instance == null) {
            instance = new Sound();
        }
        return instance;
    }

    public void toggleSound() {
        this.soundOn = !soundOn;
        if (soundOn) {
            playMusicLoop();
        } else {
            stopMusic(); 
        }
    }


    public boolean isSoundOn() {
        return soundOn;
    }

    private void playSound(String filepath) {
        try {
            if (isSoundOn()) {
                InputStream audioSrc = getClass().getResourceAsStream(filepath);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioSrc);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playSoundMove() {
        playSound("/PUZZLE/sound/move.wav");
    }

    public void playSoundWin(){
        playSound("/PUZZLE/sound/win.wav");
    }
    
    public void playMusicLoop() {
        try {
            if (isSoundOn()) {
                InputStream audioSrc = getClass().getResourceAsStream("/PUZZLE/sound/music.wav");
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioSrc);
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
