package utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audios {
    static String[] playList = {
        "src/utils/resource/bullet.wav",
        "src/utils/resource/bgMusic.wav",
        "src/utils/resource/dyingGhost.wav",
        "src/utils/resource/gameOver.wav"
    };
    public static Clip clip;
    static AudioInputStream audioInputStream;
    public static Clip getAudio(int i) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        audioInputStream = AudioSystem.getAudioInputStream(new File(playList[i]).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        return clip;
    }
}