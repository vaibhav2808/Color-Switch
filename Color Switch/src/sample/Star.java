package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Star implements Serializable{
    private transient ImageView star;
    private double x,y=0.0;
    Star(){
        display();
    }
    public void display(){
        Image image=new Image("file:src/assets/Star.png");
        star=new ImageView(image);
        star.setFitHeight(50);
        star.setFitWidth(50);
        star.setPreserveRatio(true);
        star.setTranslateY(y);
    }
    public void serialise(){
        y=star.getTranslateY();
    }
    public void deserialise(){
        display();
    }
    public ImageView get(){
        return star;
    }
}
