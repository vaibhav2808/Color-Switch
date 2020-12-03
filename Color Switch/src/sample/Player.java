package sample;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int score;
    private Ball ball;
    private final int scoreForresurrection=10;
    public Player(Ball ball){
        this.ball=ball;
        this.score=0;
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
        return score>=scoreForresurrection;
    }

    public void collectStar(){
        score+=1;
    }

    public void jump(){
        ball.get().setTranslateY(ball.get().getTranslateY()- 50);
    }
}
