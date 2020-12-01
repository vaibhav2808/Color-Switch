package sample;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.Serializable;

public class Ball extends Circle implements ColorChanging, Serializable {
    private SerializableColor color;
    private final int radius;

    public Ball(){
        super();
        this.radius=10;
        this.setRadius(radius);
        this.setFill(Color.WHITE);
        this.setCenterX(180);
        this.setCenterY(590);
        this.setTranslateY(250);
    }

    @Override
    public Paint getColor(){
        return color.getFXColor();
    }

    @Override 
    public void changeColor(SerializableColor color){
        this.color=color;
        this.setFill(color.getFXColor());
    }
}
