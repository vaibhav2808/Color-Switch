package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class CircleObstacle extends Obstacle{
    private double radius;
    private Group group;
    public CircleObstacle(Paint color[],double radius){
        this.radius=radius;
        group=new Group();
        Arc arcs[]=new Arc[4];
        for(int i=0;i<4;i++){
            arcs[i]=new Arc(180,200,radius,radius,90*i,90);
//            arcs[i].setStartAngle(90*i);
//            arcs[i].setLength(90);
//            arcs[i].setCenterX(100);
//            arcs[i].setCenterY(100);
            arcs[i].setStroke(color[i]);
            arcs[i].setStrokeWidth(10);
            arcs[i].setType(ArcType.OPEN);
            arcs[i].setFill(null);
            group.getChildren().add(arcs[i]);
        }
    }

    @Override
    public void display() {


    }

    public void setRadius(double r){
        this.radius = r;
    }
    public double getRadius(){
        return radius;
    }
    public Group getGroup(){
        return group;
    }
}
