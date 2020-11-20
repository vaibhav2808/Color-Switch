package sample;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle implements ColorChanging {
    private String color;
    private final int radius;

    public Ball(){
        super();
        this.radius=2;
        this.setRadius(10);
        this.setFill(Color.WHITE);
        this.setCenterX(180);
        this.setCenterY(590);
    }

    public void setColor(String s){
        this.color = s;
    }

    @Override
    public String getColor(){
        return "Red";
    }

    @Override 
    public void changeColor(){

    }
}
