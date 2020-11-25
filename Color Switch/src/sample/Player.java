package sample;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int score;
    private Ball ball;

    public Player(Ball ball){
        this.ball=ball;
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
        ball.setTranslateY(ball.getTranslateY()- 50);
    }
}
