package sample;

import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;

import java.util.List;

public class CircleObstacle extends Obstacle{
    private double radius;
    public CircleObstacle(SerializableColor color[],double radius){
        super(color);
        this.radius=radius;
        display();
    }

    @Override
    public void display() {

        Arc arcs[]=new Arc[4];
        SerializableColor color[]=getColor();
        Arc innerArc,outerArc;
        for(int i=0;i<4;i++) {
            arcs[i] = new Arc(180, 200, radius, radius, 90 * i, 90);
            arcs[i].setStroke(color[i].getFXColor());
            arcs[i].setStrokeWidth(10);
            arcs[i].setType(ArcType.OPEN);
            arcs[i].setFill(null);
            getGroup().getChildren().add(arcs[i]);
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
