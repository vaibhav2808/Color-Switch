package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Star extends ImageView implements Serializable{
    private ImageView star;
    private double x,y;
    Star(){
        super(new Image("file:src/assets/Star.png"));
//        this.x=x;
//        this.y=y;
//        this.setX(x);
//        this.setY(y);
        this.setFitHeight(50);
        this.setFitWidth(50);
        this.setPreserveRatio(true);
//        loadImage();
    }
//    public void loadImage(){
//        Image image=new Image("./src/assets/star.png");
//        star=new ImageView(image);
//        star.setFitHeight(10);
//        star.setFitWidth(10);
//        star.setPreserveRatio(true);
//    }
}
