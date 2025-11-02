import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class Audio {
    InputStream inputStream;
    AudioInputStream audioStream;
    AudioFormat audioFormat;
    Clip audioClip;
    DataLine.Info audioInfo;

    public Audio(String path) {
        try {
            this.inputStream = getClass().getClassLoader().getResourceAsStream(path);
            this.audioStream = AudioSystem.getAudioInputStream(inputStream);
            this.audioClip = AudioSystem.getClip();
            this.audioClip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public Clip getAudioClip() {
        return audioClip;
    }

    public void play() {
        if (this.audioClip.isRunning()) {
            this.audioClip.stop();
        }

        this.audioClip.setFramePosition(0);
        this.audioClip.start();
    }

    public void stop() {
        this.audioClip.stop();
    }
}
