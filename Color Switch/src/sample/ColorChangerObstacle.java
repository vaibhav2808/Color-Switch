package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import java.io.Serializable;

public class ColorChangerObstacle implements Serializable {
    private Group group;
    private final int radius=15;
    ColorChangerObstacle(){
        group=new Group();
        Arc arcs[]=new Arc[4];
        Paint color[]={Color.DEEPPINK,Color.LIGHTBLUE,Color.YELLOW,Color.DARKVIOLET};
        for(int i=0;i<4;i++){
            arcs[i]=new Arc(20,20,radius,radius,90*i,90);
            arcs[i].setFill(color[i]);
//            arcs[i].setStrokeWidth(10);
            arcs[i].setType(ArcType.ROUND);
            group.getChildren().add(arcs[i]);
        }

    }
    public Group getGroup(){
        return group;
    }
}
