package sample;

public class Game{
    private Ball ball;
    private Player player;
    private GameManager manager;
    private ArrayList<Obstacle> obstacles;

    public Game(){

    }

    public void setManager(GameManager m){
        this.manager = m;
    }
    public int getScore(){
        return player.getScore();
    }

    public boolean checkCollision(){
        return false;
    }

    public void play(){

    }

    public void exitToMainMenu(){

    }

    public void resumeGame(){

    }

    public void pauseGame(){

    }

    public void restartGame(){

    }

    public void saveGameAndExit(){

    }

    public void continueGame(){

    }
}
