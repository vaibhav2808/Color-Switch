package sample;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class PlusObstacle extends Obstacle{
    private double length;
    private Group group=new Group();
    public PlusObstacle(double length, Paint[] color){
        this.length=length;
        Line l1=new Line(0,length/2,length/2,length/2);
        l1.setStroke(color[0]);
        l1.setStrokeWidth(10);
        Line l2=new Line(length/2,length/2,length,length/2);
        l2.setStroke(color[1]);
        l2.setStrokeWidth(10);
        Line l3=new Line(length/2,0,length/2,length/2);
        l3.setStroke(color[2]);
        l3.setStrokeWidth(10);
        Line l4=new Line(length/2,length/2,length/2,length);
        l4.setStroke(color[3]);
        l4.setStrokeWidth(10);
        group.getChildren().addAll(l1,l2,l3,l4);
    }

    public void setLength(double l){
        this.length = l;
    }
    public double getLength(){
        return length;
    }
    public Group getGroup(){
        return group;
    }
}
