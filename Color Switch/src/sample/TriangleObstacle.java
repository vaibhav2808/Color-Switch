package sample;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import java.util.Arrays;

public class TriangleObstacle extends Obstacle{
    private double side;
    public TriangleObstacle(double side, SerializableColor color[]){
        super(Arrays.copyOf(color,3));
        this.side=side;
        display();
    }
    @Override
    public void display() {
        Group group=getGroup();
        SerializableColor[] color=getColor();
        Line l1=new Line(0,0,side,0);
        l1.setStroke(color[0].getFXColor());
        l1.setStrokeWidth(10);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        Line l2=new Line(0,0,side/2,1.73*side/2);
        l2.setStroke(color[1].getFXColor());
        l2.setStrokeWidth(10);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        Line l3=new Line(side,0,side/2,1.73*side/2);
        l3.setStroke(color[2].getFXColor());
        l3.setStrokeWidth(10);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        group.getChildren().addAll(l1,l2,l3);
        group.setTranslateY(getY());
    }
    public void setSide(double s){
        this.side = s;
    }
    public double getSide(){
        return side;
    }
}
