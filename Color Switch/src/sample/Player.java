package sample;

public class Player {
    private String name;
    private int score;
    private Ball ball;

    public Player(){

    }

    public void setScore(int s){
        this.score = s;
    }
    public int getScore(){
        return score;
    }
    public Ball getBall(){
        return ball;
    }
    
    public boolean canResurrect(){
        return false;
    }

    public void collectStar(){

    }

    public void jump(){
        
    }
}
