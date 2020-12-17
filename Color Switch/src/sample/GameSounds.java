package sample;

import javafx.scene.media.AudioClip;

import java.nio.file.Paths;


//facade,singleton
public class GameSounds {
    private AudioClip playSound;
    private AudioClip hitButton;
    private AudioClip colorSwitchHit;
    private AudioClip starHit;
    private AudioClip gameOver;
    private AudioClip error;
    private AudioClip jump;
    private static GameSounds gameSounds;

    public static final int PLAY_SOUND=1;
    public static final int BUTTON_SOUND=2;
    public static final int COLORSWITCHER_SOUND=3;
    public static final int STAR_SOUND=4;
    public static final int GAMEOVER_SOUND=5;
    public static final int ERROR_SOUND=6;
    public static final int JUMP_SOUND=7;
    private GameSounds(){
        playSound=new AudioClip(Paths.get("./src/assets/sounds/start.wav").toUri().toString());
        hitButton=new AudioClip(Paths.get("./src/assets/sounds/button.wav").toUri().toString());
        colorSwitchHit=new AudioClip(Paths.get("./src/assets/sounds/colorswitch.wav").toUri().toString());
        starHit=new AudioClip(Paths.get("./src/assets/sounds/star.wav").toUri().toString());
        gameOver=new AudioClip(Paths.get("./src/assets/sounds/dead.wav").toUri().toString());
        error=new AudioClip(Paths.get("./src/assets/sounds/error.wav").toUri().toString());
        jump=new AudioClip(Paths.get("./src/assets/sounds/jump.wav").toUri().toString());
    }

    public static GameSounds getInstance(){
        if(gameSounds==null){
            gameSounds=new GameSounds();
        }
        return gameSounds;
    }

    public void play(int option){
        switch (option) {
            case 1 -> playSound.play();
            case 2 -> hitButton.play();
            case 3 -> colorSwitchHit.play();
            case 4 -> starHit.play();
            case 5 -> gameOver.play();
            case 6 -> error.play();
            case 7 -> jump.play();
        }
    }
}
