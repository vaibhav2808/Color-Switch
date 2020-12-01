package sample;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.List;

public class RectangleObstacle extends Obstacle{
    private double length;
    private double breadth;
    public RectangleObstacle(double l, double b, SerializableColor arr[]){
        super(arr);

        this.length=l;
        this.breadth=b;
        display();
    }

    public void setLength(double l){
        this.length = l;
    }
    public void setBreadth(double b){
        this.breadth = b;
    }
    public double getLength(){
        return length;
    }
    public double getBreadth(){
        return breadth;
    }
    @Override
    public boolean collisionWithDiffColor(Ball ball) {
        List<Node> list=getGroup().getChildren();
        for(Node n:list){
            if(Shape.intersect((Shape)n,ball).getBoundsInLocal().getWidth()>0&&!(((Shape) n).getFill().equals(ball.getFill()))){
                return true;
            }
        }
        return false;
    }

    @Override
    public void display() {
        Rectangle r,r2;
        SerializableColor color[]=getColor();
        Group group=getGroup();
        //top side
        r=new Rectangle(0,0,length,breadth);
        r2=new Rectangle(0,10,length,breadth);
        Shape shape= Shape.subtract(r,r2);
        shape.setFill(color[3].getFXColor());
        group.getChildren().add(shape);


        //bottom side
        r=new Rectangle(0,0,length,breadth);
        r2=new Rectangle(0,0,length,breadth-10);
        shape= Shape.subtract(r,r2);
        shape.setFill(color[1].getFXColor());
        group.getChildren().add(shape);

        //left side
        r=new Rectangle(0,0,length,breadth);
        r2=new Rectangle(10,0,length,breadth);
        shape= Shape.subtract(r,r2);
        shape.setFill(color[2].getFXColor());
        group.getChildren().add(shape);

        //right side
        r=new Rectangle(0,0,length,breadth);
        r2=new Rectangle(0,0,length-10,breadth);
        shape= Shape.subtract(r,r2);
        shape.setFill(color[0].getFXColor());
        group.getChildren().add(shape);
        group.setTranslateY(getY());
    }
}
