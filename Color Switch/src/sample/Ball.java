package sample;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.Serializable;

public class Ball implements ColorChanging, Serializable {
    private SerializableColor color;
    private final int radius;
    private transient Circle ball;
    private double y=250;
    public Ball(){
        color=new SerializableColor(Color.WHITE);
        this.radius=10;
        display();
    }
    public void display(){
        ball=new Circle();
        ball.setRadius(radius);
        ball.setFill(color.getFXColor());
        ball.setCenterX(180);
        ball.setCenterY(590);
        ball.setTranslateY(y);
    }
    @Override
    public Paint getColor(){
        return color.getFXColor();
    }

    @Override 
    public void changeColor(SerializableColor color){
        this.color=color;
        ball.setFill(color.getFXColor());
    }

    public void serialise(){
        y=ball.getTranslateY();
    }

    public void deserialise(){
        display();
    }

    public Circle get(){
        return ball;
    }
}
