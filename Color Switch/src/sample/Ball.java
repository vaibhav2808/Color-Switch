package sample;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Ball extends Circle implements ColorChanging {
    private Paint color;
    private final int radius;

    public Ball(){
        super();
        this.radius=2;
        this.setRadius(10);
        this.setFill(Color.WHITE);
        this.setCenterX(180);
        this.setCenterY(590);
    }

    public void setColor(Paint s){
        this.color = s;
        this.setFill(color);
    }

    @Override
    public Paint getColor(){
        return color;
    }

    @Override 
    public void changeColor(){

    }
}
