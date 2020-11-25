package sample;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.List;

public class Obstacle implements Serializable {
    private int speed;
    private Group group;
    private Paint colors[];
    private RotateTransition transition;
    private ColorChangerObstacle colorSwitcher;
    public Obstacle(Paint colors[]){
        this.colors=colors;
        group=new Group();
        colorSwitcher=new ColorChangerObstacle(colors);
    }

    public Paint[] getColor(){
        return colors;
    }

    public void display(){

    }
    public Group getGroup(){
        return group;
    }

    public ColorChangerObstacle getColorSwitcher(){
        return colorSwitcher;
    }


    public boolean collisionWithDiffColor(Ball ball) {
        List<Node> list=getGroup().getChildren();
        for(Node n:list){
            if(Shape.intersect((Shape)n,ball).getBoundsInLocal().getWidth()>0&&!(((Shape) n).getStroke().equals(ball.getFill()))){
                return true;
            }
        }
        return false;
    }

    public void createTransition(double duration){
        transition= new RotateTransition(Duration.seconds(duration),group);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setByAngle(360);
        transition.setCycleCount(RotateTransition.INDEFINITE);
    }

    public void startTransition(){
        transition.play();
    }
}
