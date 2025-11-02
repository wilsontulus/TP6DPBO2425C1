import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class Audio {

    // Declare required data variables
    InputStream inputStream;
    AudioInputStream audioStream;
    Clip audioClip;

    public Audio(String path) {
        try {
            // Get audio from specified path
            this.inputStream = getClass().getClassLoader().getResourceAsStream(path);

            // Get the stream
            this.audioStream = AudioSystem.getAudioInputStream(inputStream);

            // Get the clip of the generated stream and load it
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
        // Stop if it's still playing
        if (this.audioClip.isRunning()) {
            this.audioClip.stop();
        }

        // Reset the time position to absolute beginning
        this.audioClip.setFramePosition(0);

        // Start, or play, the audio
        this.audioClip.start();
    }

    public void stop() {
        // Stops the audio of course
        this.audioClip.stop();
    }
}
