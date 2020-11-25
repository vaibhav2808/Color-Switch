package sample;

import javafx.scene.Group;

import java.io.Serializable;

public class Obstacle implements Serializable {
    private int speed;
    private double angle;
    private Group group;
    public Obstacle(){
        group=new Group();
    }

    public String getColor(){
        return "Red";
    }

    public void display(){

    }
    public Group getGroup(){
        return group;
    }
    public double getRotate(){
        return ++angle%360;
    }
}
