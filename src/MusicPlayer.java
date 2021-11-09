
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import  java.io.*;

public class MusicPlayer {

    MusicPlayer() {}

    public static void playMusic() {
            try {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream
                        (new File("resources/background_music.wav").getAbsoluteFile());
                Clip song = AudioSystem.getClip();
                song.open(audioInput);
                song.loop(10);
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        }
}
