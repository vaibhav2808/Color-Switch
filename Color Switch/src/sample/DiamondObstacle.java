package sample;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class DiamondObstacle extends Obstacle{
    private double angle;
    private double side;
    private Group group=new Group();
    public DiamondObstacle(double s, double a, Paint color[]){

        this.side=s;
        this.angle=a;
        Line l1=new Line(0,0,side,0);
        l1.setStroke(color[0]);
        l1.setStrokeWidth(10);
        l1.setStrokeLineCap(StrokeLineCap.ROUND);
        Line l2=new Line(0,0,side*Math.sin(a),side*Math.cos(a));
        l2.setStroke(color[1]);
        l2.setStrokeWidth(10);
        l2.setStrokeLineCap(StrokeLineCap.ROUND);
        Line l3=new Line(side,0,side*Math.sin(a)+side,side*Math.cos(a));
        l3.setStroke(color[2]);
        l3.setStrokeWidth(10);
        l3.setStrokeLineCap(StrokeLineCap.ROUND);
        Line l4=new Line(side*Math.sin(a),side*Math.cos(a),side*Math.sin(a)+side,side*Math.cos(a));
        l4.setStroke(color[3]);
        l4.setStrokeWidth(10);
        l4.setStrokeLineCap(StrokeLineCap.ROUND);
        group.getChildren().addAll(l1,l2,l3,l4);
    }

    public void setAngle(double a){
        this.angle = a;
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
    public Group getGroup(){
        return group;
    }
}
