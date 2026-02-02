package engine;

import javax.sound.sampled.*;
import java.io.*;


public class AudioManager {

    private Clip musicClip;
    private boolean isPlaying;
    private float volume = 1.0f;


    public AudioManager() {
        this.isPlaying = false;
    }


    public void playBackgroundMusic(String filePath) {
        try {
            if (musicClip != null && musicClip.isRunning()) {
                musicClip.stop();
            }

            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);

            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            musicClip.start();
            isPlaying = true;

        } catch (Exception e) {
            System.out.println("no sound");
        }
    }


    public void stopMusic() {
        if (musicClip != null) {
            if (musicClip.isRunning()) {
                musicClip.stop();
            }
            musicClip.close();
            isPlaying = false;
        }
    }


    public void playSound(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip soundClip = AudioSystem.getClip();
            soundClip.open(audioStream);
            soundClip.start();

            soundClip.addLineListener(new LineListener() {
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        soundClip.close();
                    }
                }
            });

        } catch (Exception e) {
            System.out.println("no sound");
        }
    }


    public boolean isPlaying() {
        return isPlaying;
    }


    public void setVolume(float vol) {
        if (vol < 0.0f) vol = 0.0f;
        if (vol > 1.0f) vol = 1.0f;
        this.volume = vol;

        if (musicClip != null) {
            try {
                FloatControl volumeControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
                float range = volumeControl.getMaximum() - volumeControl.getMinimum();
                float gain = (range * vol) + volumeControl.getMinimum();
                volumeControl.setValue(gain);
            } catch (Exception e) {
                System.out.println("no volume");
            }
        }
    }


    public float getVolume() {
        return volume;
    }
}
