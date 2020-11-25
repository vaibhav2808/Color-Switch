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
    public RectangleObstacle(double l, double b, Paint arr[]){
        super(arr);
        Group group=getGroup();
        this.length=l;
        this.breadth=b;
        Rectangle r,r2;

        //top side
        r=new Rectangle(0,0,length,breadth);
        r2=new Rectangle(0,10,length,breadth);
        Shape shape= Shape.subtract(r,r2);
        shape.setFill(arr[3]);
        group.getChildren().add(shape);


        //bottom side
        r=new Rectangle(0,0,length,breadth);
        r2=new Rectangle(0,0,length,breadth-10);
        shape= Shape.subtract(r,r2);
        shape.setFill(arr[1]);
        group.getChildren().add(shape);

        //left side
        r=new Rectangle(0,0,length,breadth);
        r2=new Rectangle(10,0,length,breadth);
        shape= Shape.subtract(r,r2);
        shape.setFill(arr[2]);
        group.getChildren().add(shape);

        //right side
        r=new Rectangle(0,0,length,breadth);
        r2=new Rectangle(0,0,length-10,breadth);
        shape= Shape.subtract(r,r2);
        shape.setFill(arr[0]);
        group.getChildren().add(shape);

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
}
