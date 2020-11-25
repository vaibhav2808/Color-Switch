package sample;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

import java.util.List;

public class PlusObstacle extends Obstacle{
    private double length;
    public PlusObstacle(double length, Paint[] color){
        super(color);
        Group group=getGroup();
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
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);


        Circle circle=new Circle();
        circle.setCenterX(length/2);
        circle.setCenterY(length/2);
        circle.setFill(Color.BLACK);
        circle.setStroke(Color.BLACK);
        circle.setRadius(8);
        group.getChildren().addAll(l1,l2,l3,l4,circle);
    }

    public void setLength(double l){
        this.length = l;
    }
    public double getLength(){
        return length;
    }
}
