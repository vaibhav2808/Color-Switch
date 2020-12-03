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
//        super(new Image("file:src/assets/Star.png"));
//        this.x=x;
//        this.y=y;
//        this.setX(x);
//        this.setY(y);
//        this.setFitHeight(50);
//        this.setFitWidth(50);
//        this.setPreserveRatio(true);
//        loadImage();
    }
//    public void loadImage(){
//        Image image=new Image("./src/assets/star.png");
//        star=new ImageView(image);
//        star.setFitHeight(10);
//        star.setFitWidth(10);
//        star.setPreserveRatio(true);
//    }
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
