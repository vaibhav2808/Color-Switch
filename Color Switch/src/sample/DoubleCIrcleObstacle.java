package sample;

import javafx.scene.Group;
import javafx.scene.shape.Circle;

public class DoubleCIrcleObstacle extends Obstacle{

    DoubleCIrcleObstacle(SerializableColor color[]){
        super((color));
        display();
    }
    @Override
    public void display(){
        CircleObstacle circleObstacle1=new CircleObstacle(getColor(),100);
        CircleObstacle circleObstacle2=new CircleObstacle(getColor(),80);
        Group group=getGroup();
        group.getChildren().addAll(circleObstacle1.getGroup().getChildren());
        group.getChildren().addAll(circleObstacle2.getGroup().getChildren());
    }

}
