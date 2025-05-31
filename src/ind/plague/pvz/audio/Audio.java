package ind.plague.pvz.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Objects;

/**
 * @author PlagueWZK
 * description: Audio
 * date: 2025/5/16 13:50
 */

public class Audio {
    Clip clip;

    public Audio(String path) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(Objects.requireNonNull(Audio.class.getResource(path)));
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            throw new RuntimeException("音频加载失败", e);
        }
    }

    public void play(boolean loop) {
        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            stop();
            reset();
        }
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void reset() {
        clip.setFramePosition(0);
    }
}
