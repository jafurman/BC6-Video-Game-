package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {

        soundURL[0] = getClass().getResource("/sound/theme.wav");
        soundURL[1] = getClass().getResource("/sound/pickUp.wav");
        soundURL[2] = getClass().getResource("/sound/doorOpen.wav");
        soundURL[3] = getClass().getResource("/sound/record.wav");
        soundURL[4] = getClass().getResource("/sound/recordScratch.wav");
        soundURL[5] = getClass().getResource("/sound/electric.wav");
        soundURL[6] = getClass().getResource("/sound/snowfallSong.wav");
        soundURL[7] = getClass().getResource("/sound/gymnopedie.wav");
        soundURL[8] = getClass().getResource("/sound/rickRoll.wav");
        soundURL[9] = getClass().getResource("/sound/steveLacy.wav");
    }

    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e ) {

        }

    }
    public void play() {

        clip.start();

    }
    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void stop() {

        clip.stop();
    }
}
