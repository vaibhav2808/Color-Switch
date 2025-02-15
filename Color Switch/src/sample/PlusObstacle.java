package sample;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class PlusObstacle extends Obstacle{
    private double length;
    public PlusObstacle(double length, SerializableColor[] color){
        super(color);
        this.length=length;
        display();
    }
    @Override
    public void display() {
        Group group=getGroup();
        SerializableColor color[]=getColor();
        Line l1=new Line(0,length/2,length/2,length/2);
        l1.setStroke(color[0].getFXColor());
        l1.setStrokeWidth(10);
        Line l2=new Line(length/2,length/2,length,length/2);
        l2.setStroke(color[1].getFXColor());
        l2.setStrokeWidth(10);
        Line l3=new Line(length/2,0,length/2,length/2);
        l3.setStroke(color[2].getFXColor());
        l3.setStrokeWidth(10);
        Line l4=new Line(length/2,length/2,length/2,length);
        l4.setStroke(color[3].getFXColor());
        l4.setStrokeWidth(10);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);

        group.getChildren().addAll(l1,l2,l3,l4);
        group.setTranslateY(getY());
        group.setTranslateX(-60);
    }
    public void setLength(double l){
        this.length = l;
    }
    public double getLength(){
        return length;
    }


}
