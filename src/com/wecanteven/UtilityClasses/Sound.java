package com.wecanteven.UtilityClasses;

import com.wecanteven.AreaView.ViewTime;
import com.wecanteven.MenuView.UIViewFactory;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joshua Kegley on 4/17/2016.
 */
public class Sound  {

    public static HashMap<String, Sound> SOUNDS;


    public enum Volume {
        MUTE(-80),
        LOW(-30),
        MEDIUM(-16),
        HIGH(6);
        private float gain;

        Volume(float gain){
            this.gain = gain;
        }
    }
    public static boolean mute = false;
    public boolean loop = false;
    public Volume volume = Volume.LOW;

    private Clip clip;

    public Sound(String soundFileName, Volume volume, boolean loop) {
        this.loop = loop;
        this.volume = volume;
        try {
            //URL url = this.getClass().getClassLoader().getResource(soundFileName);
            File file = new File(soundFileName);
            System.out.println(soundFileName);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
        } catch (Exception e) {

        }
    }

    // Play or Re-play the sound effect from the beginning, by rewinding.
    public void play() {
        FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gain.setValue(volume.gain);
        if (!mute && volume != Volume.MUTE) {
            if (clip.isRunning())
                return;   // Stop the player if it is still running
            clip.setFramePosition(0); // rewind to the beginning
            clip.start();     // Start playing
            float clipLength = ((float)clip.getFrameLength())/clip.getFormat().getFrameRate();
            if(loop) {
                ViewTime.getInstance().register(()->{
                    play();
                },(int)clipLength*1000);
            }
        }
    }

    public void stop() {
        clip.stop();
    }

    // Optional static method to pre-load all the sound files.
    public static void init() {
        SOUNDS = new HashMap<>();
        SOUNDS.put("menuTheme",
                new Sound("resources/Sounds/MenuSounds/MenuIntro.wav", Volume.LOW, false)
        );
        SOUNDS.put("menuMove",
                new Sound("resources/Sounds/MenuSounds/MenuSelect.wav", Volume.MEDIUM, false)
        );
        SOUNDS.put("menuConfirm",
                new Sound("resources/Sounds/MenuSounds/MenuConfirm.wav", Volume.LOW, false)
        );
        SOUNDS.put("startGame",
                new Sound("resources/Sounds/MenuSounds/StartGame.wav", Volume.MEDIUM, false)
        );

        SOUNDS.put("gameTheme",
                new Sound("resources/Sounds/Game/GameTheme.wav", Volume.LOW, true)
        );

        SOUNDS.put("Punch",
                new Sound("resources/Sounds/Abilities/Punch.wav", Volume.HIGH, false)
        );
        SOUNDS.put("OneHanded",
                new Sound("resources/Sounds/Abilities/OneHanded.wav", Volume.HIGH, false)
        );
        SOUNDS.put("Magic",
                new Sound("resources/Sounds/Abilities/Magic.wav", Volume.HIGH, false)
        );

        SOUNDS.put("BindWounds",
                new Sound("resources/Sounds/Abilities/BindWounds.wav", Volume.HIGH, false)
        );

        SOUNDS.put("Club",
                new Sound("resources/Sounds/Abilities/Club.wav", Volume.HIGH, false)
        );
    }

    public static void play(String sound) {
        SOUNDS.get(sound).play();
    }
    public static void stop(String sound) {
        SOUNDS.get(sound).stop();
    }

    public static void toggleMute() {
        stopAll();
        mute = !mute;
    }

    public static void stopAll() {
        for(Map.Entry<String, Sound> entry : SOUNDS.entrySet()) {
            entry.getValue().stop();
        }
    }

}
