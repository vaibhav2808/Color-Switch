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

public abstract class Obstacle implements Serializable {
    private int speed;
    private transient Group group;
    private SerializableColor colors[];
    private transient RotateTransition transition;
    private ColorChangerObstacle colorSwitcher;
    private double translateY=0.0;
    public Obstacle(SerializableColor colors[]){
        this.colors=colors;
        group=new Group();
        colorSwitcher=new ColorChangerObstacle(colors);
    }

    public SerializableColor[] getColor(){
        return colors;
    }

    public abstract void display();
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

    public void serialise(){
        this.translateY=group.getTranslateY();
    }

    public void deserialise(){
        display();
        group.setTranslateY(translateY);
        colorSwitcher.display();
    }

    public double getY(){
        return translateY;
    }
}
