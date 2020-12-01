package sample;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class DiamondObstacle extends Obstacle{
    private double angle;
    private double side;
    public DiamondObstacle(double s, double angle, SerializableColor color[]){
        super(color);
        this.side=s;
        this.angle=angle;
        display();
    }

    public void setAngle(double angle){
        this.angle = angle;
    }
    public void setSide(double s){
        this.side = s;
    }
    public double getAngle(){
        return angle;
    }
    public double getSide(){
        return side;
    }
    @Override
    public void display() {
        Group group=getGroup();

        SerializableColor color[]=getColor();
        Line l1=new Line(0,0,side,0);
        l1.setStroke(color[0].getFXColor());
        l1.setStrokeWidth(10);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        Line l2=new Line(0,0,side*Math.sin(angle),side*Math.cos(angle));
        l2.setStroke(color[1].getFXColor());
        l2.setStrokeWidth(10);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        Line l3=new Line(side,0,side*Math.sin(angle)+side,side*Math.cos(angle));
        l3.setStroke(color[2].getFXColor());
        l3.setStrokeWidth(10);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        Line l4=new Line(side*Math.sin(angle),side*Math.cos(angle),side*Math.sin(angle)+side,side*Math.cos(angle));
        l4.setStroke(color[3].getFXColor());
        l4.setStrokeWidth(10);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        group.getChildren().addAll(l1,l2,l3,l4);
        group.setTranslateY(getY());
    }
}
