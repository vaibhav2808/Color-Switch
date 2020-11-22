package sample;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class TriangleObstacle extends Obstacle{
    private double side;
    public TriangleObstacle(double side, Paint color[]){
        Group group=getGroup();
        this.side=side;
        Line l1=new Line(0,0,side,0);
        l1.setStroke(color[0]);
        l1.setStrokeWidth(10);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        Line l2=new Line(0,0,side/2,1.73*side/2);
        l2.setStroke(color[1]);
        l2.setStrokeWidth(10);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        Line l3=new Line(side,0,side/2,1.73*side/2);
        l3.setStroke(color[2]);
        l3.setStrokeWidth(10);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        group.getChildren().addAll(l1,l2,l3);

    }

    public void setSide(double s){
        this.side = s;
    }
    public double getSide(){
        return side;
    }
}
