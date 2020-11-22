package sample;

import javafx.stage.Stage;

public class GameManager {
    private Game game;
    private int highScore;

    public GameManager(){

    }

    public void setGame(Game g){
        this.game = g;
    }
    public void setHighScore(int h){
        this.highScore = h;
    }
    public Game getGame(){
        return game;
    }
    public int getHighScore(){
        return highScore;
    }

    public void startNewGame(Stage theStage){
        game=new Game();
        playGame(theStage);
    }

    public void savedGamesList(){

    }

    public void continuePreviousGame(){

    }

    public void saveGame(){

    }

    public void exitGame(){

    }

    public void playGame(Stage theStage){
        game.play(theStage);
    }
}
