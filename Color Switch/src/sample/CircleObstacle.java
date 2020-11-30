package sample;

import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;

import java.util.List;

public class CircleObstacle extends Obstacle{
    private double radius;
    public CircleObstacle(Paint color[],double radius){
        super(color);
        this.radius=radius;
        display();
    }

    @Override
    public void display() {

        Arc arcs[]=new Arc[4];
        Paint color[]=getColor();
        Arc innerArc,outerArc;
        for(int i=0;i<4;i++) {
            arcs[i] = new Arc(180, 200, radius, radius, 90 * i, 90);
//            innerArc=new Arc(180,200,radius-10,radius-10,90*i,90);
//            outerArc=new Arc(180,200,radius,radius,90*i,90);
//            Shape shape= Shape.subtract(outerArc,innerArc);
//            shape.i
//            shape.setFill(color[i]);

            arcs[i].setStroke(color[i]);
            arcs[i].setStrokeWidth(10);
            arcs[i].setType(ArcType.OPEN);
            arcs[i].setFill(null);
            getGroup().getChildren().add(arcs[i]);
//            shape.setOnMouseClicked(e->{
//                System.out.println("clicked");
//            });
//            group.getChildren().add(shape);
//            System.out.println(shape.getBoundsInLocal());
//            System.out.println(shape.getBoundsInParent());
//            System.out.println(shape.getLayoutBounds());
        }
        getGroup().setTranslateY(getY());
    }

    public void setRadius(double r){
        this.radius = r;
    }
    public double getRadius(){
        return radius;
    }
}
