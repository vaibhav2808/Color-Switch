package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
//import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Color Switch");
        Pane root=new Pane();
        root.setStyle("-fx-background-color: Black");
        Scene thescene=new Scene(root,360,640);
//        Canvas canvas= new Canvas(400,600);
//        StackPane canvasholder=new StackPane();
//        canvasholder.getChildren().add(canvas);
//        root.getChildren().add(canvasholder);
//        canvasholder.setStyle("-fx-background-color: Black");
//        GraphicsContext gc= canvas.getGraphicsContext2D();
//        Arc arc=new Arc();
//        arc.setCenterX(25);
////        arc.setCenter;
//        arc.setStartAngle(180.0f);
//        arc.setLength(90.0f);
//
//        gc.strokeArc(200,200,90,90,180,90, ArcType.OPEN);
        Paint arr[]={Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW};
        root.getChildren().add(new CircleObstacle(arr,100).getGroup());
        root.getChildren().add(new Ball());
        primaryStage.setScene(thescene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
