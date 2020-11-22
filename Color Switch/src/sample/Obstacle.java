package sample;

import javafx.scene.Group;

public class Obstacle {
    private int speed;
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

}
