package sample;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game extends AnimationTimer implements Serializable {
    private final int maxNumofObstaclesrendered=5;
    private final int DistancebetweenObstacles=450;
    private long obstacleRenderedPosition;
    private volatile ColorChangerObstacle colorSwitcher[]=new ColorChangerObstacle[maxNumofObstaclesrendered];
    private SerializableColor arr[]={new SerializableColor(Color.BLUE),new SerializableColor(Color.RED),new SerializableColor(Color.GREEN),new SerializableColor(Color.YELLOW)};
    private Ball ball;
    private Player player;
    private GameManager manager;
    private ArrayList<Obstacle> allObstacles,obstaclesOnScreen;
    private transient Scene gameScene,pauseScene;
    private transient StackPane gamePlayRoot;
    private int lastObstacleId=maxNumofObstaclesrendered;
    private Star[] stars=new Star[maxNumofObstaclesrendered];
    public Game(Stage primaryStage, GameManager manager) throws FileNotFoundException {
        this.manager=manager;
        ball=new Ball();
        ball.setFill(arr[0].getFXColor());
        player=new Player(ball);
        allObstacles=new ArrayList<>(100);
        obstaclesOnScreen=new ArrayList<>();
        gamePlayRoot=new StackPane();
        gamePlayRoot.setStyle("-fx-background-color: BLACK");
        gameScene=new Scene(gamePlayRoot,360,640,Color.BLACK);
        createObstacles();
        createPauseScene(primaryStage);
        obstacleRenderedPosition=50;
        //add pause button
//        Image pause = new Image( new FileInputStream("src/assets/Pause.jpg") );
//        ImageView imageViewPause = new ImageView(pause);
//        imageViewPause.setPreserveRatio(true);
//        imageViewPause.setFitHeight(20);
//        Button pauseBtn = new Button("", imageViewPause);
//        gamePlayRoot.getChildren().add(pauseBtn);

        for (int i = 0; i < maxNumofObstaclesrendered ; i++) {
            colorSwitcher[i]=allObstacles.get(i).getColorSwitcher();
            colorSwitcher[i].getGroup().setTranslateY(obstacleRenderedPosition+250);
            stars[i]=new Star();
            stars[i].setTranslateY(obstacleRenderedPosition);
            allObstacles.get(i).getGroup().setTranslateY(obstacleRenderedPosition);
            gamePlayRoot.getChildren().addAll(colorSwitcher[i].getGroup(),allObstacles.get(i).getGroup());
            gamePlayRoot.getChildren().add(stars[i]);
            obstaclesOnScreen.add(allObstacles.get(i));

            obstacleRenderedPosition-=DistancebetweenObstacles;
            //add transition
           allObstacles.get(i).startTransition();
        }
        colorSwitcher[0].getGroup().setTranslateY(2000);
        gamePlayRoot.getChildren().add(ball);
        gameScene.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.SPACE){
                player.jump();
            }
            else if(e.getCode()==KeyCode.ESCAPE){
                pauseGame(primaryStage);
            }
        });
    }

    @Override
    public void handle(long l) {
        ball.setTranslateY(ball.getTranslateY()+1);

        //collision with color switcher
        for(int i=0;i<maxNumofObstaclesrendered;i++){
            //colorswitch collision
            if(colorSwitcher[i].getGroup().getBoundsInParent().intersects(ball.getBoundsInParent())){
//                System.out.println("collides");
                colorSwitcher[i].getGroup().setDisable(true);
                colorSwitcher[i].getGroup().setTranslateY(2000);
                ball.changeColor(new SerializableColor(colorSwitcher[i].getRandomColor()));
                renderNextObstacle();
            }
            if(ball.getBoundsInParent().intersects(stars[i].getBoundsInParent())){
                //add code for increasing stars
                stars[i].setVisible(false);
                stars[i].setTranslateY(2000);
                player.collectStar();
            }
        }

        //obstacle collision
        for(Obstacle o:obstaclesOnScreen){
            if(o.collisionWithDiffColor(ball)){
                gameOver();
            }
        }

        if(ball.getTranslateY()<-50) {
            for(Obstacle o:obstaclesOnScreen){
                o.getGroup().setTranslateY(o.getGroup().getTranslateY()+5);
            }
            for(int i=0;i<maxNumofObstaclesrendered;i++){
                colorSwitcher[i].getGroup().setTranslateY(colorSwitcher[i].getGroup().getTranslateY()+5);
                stars[i].setTranslateY(stars[i].getTranslateY()+5);
            }
            ball.setTranslateY(ball.getTranslateY()+5);
        }
        //ball out of screen
        if(ball.getTranslateY()>320){
            //code for gameover
            gameOver();
        }

    }

    public void setManager(GameManager m){
        this.manager = m;
    }
    public int getScore(){
        return player.getScore();
    }

    public boolean checkCollision(){
        return false;
    }

    public void  play(Stage thestage){
        thestage.setScene(gameScene);
        this.start();
    }

    public void exitToMainMenu(){
        //throw exception to go to GameManager
        manager.displayMainMenu();
    }

    public void resumeGame(Stage thestage){
        thestage.setScene(gameScene);
        this.start();
    }

    public void pauseGame(Stage thestage){
        this.stop();
        thestage.setScene(pauseScene);
        //add code for display pause game menu
    }

    public void restartGame(){
        manager.startNewGame();
    }

    public void saveGameAndExit(){
        manager.saveGame();
        manager.displayMainMenu();
    }

    public void continueGame(){

    }

    private void createObstacles(){
        double duration=4.5;
        int diff=0;
        for(int i=0;i<10;i++) {
            allObstacles.add(new CircleObstacle(arr, 100));
            allObstacles.add(new PlusObstacle(200, arr));
            allObstacles.add(new TriangleObstacle(200, arr));
            allObstacles.add(new DiamondObstacle(200, 120, arr));
            allObstacles.add(new RectangleObstacle(200, 200, arr));

            for(int j=diff;j<diff+5;j++){
                allObstacles.get(j).createTransition(duration);
            }
            duration-=0.5;
            diff+=5;
        }
    }

    private void createPauseScene(Stage primaryStage) throws FileNotFoundException {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: BLACK");
        pauseScene = new Scene(root, 360, 640);

        GridPane paneLvl1 = new GridPane();
        paneLvl1.setAlignment(Pos.CENTER);
        paneLvl1.setVgap(10);
        root.getChildren().add(paneLvl1);

        FlowPane flow1 = new FlowPane();
        flow1.setAlignment(Pos.CENTER);
        flow1.setHgap(5);
        Label label1 = new Label("Player: ");
        label1.setStyle("-fx-font-size:20");
        label1.setTextFill(Color.WHITE);
        Label label2 = new Label("<player_name>");
        label2.setStyle("-fx-font-size:20");
        label2.setTextFill(Color.WHITE);
        flow1.getChildren().addAll(label1, label2);

        FlowPane flow2 = new FlowPane();
        flow2.setAlignment(Pos.CENTER);
        flow1.setHgap(5);
        Image star = new Image( new FileInputStream("src/assets/Star.jpeg") );
        ImageView imageViewStar = new ImageView(star);
        imageViewStar.setPreserveRatio(true);
        imageViewStar.setFitHeight(25);
        Label label3 = new Label("Stars: ", imageViewStar);
        label3.setStyle("-fx-font-size:20");
        label3.setTextFill(Color.WHITE);
        Label label4 = new Label("<star_count>");
        label4.setStyle("-fx-font-size:20");
        label4.setTextFill(Color.WHITE);
        flow2.getChildren().addAll(label3, label4);

        GridPane paneLvl2a = new GridPane();
        paneLvl2a.setAlignment(Pos.CENTER);
        paneLvl2a.setVgap(10);
        paneLvl2a.add(flow1, 0, 1);
        paneLvl2a.add(flow2, 0, 3);
        paneLvl1.add(paneLvl2a, 0, 2);

        GridPane paneLvl2b = new GridPane();
        paneLvl2b.setAlignment(Pos.CENTER);
        paneLvl2b.setVgap(10);
        Button btn1 = new Button("Resume");
        btn1.setAlignment(Pos.CENTER);
        btn1.setPrefSize(150, 10);
        btn1.setStyle("-fx-font-size:15");
        btn1.setTextFill(Color.DARKBLUE);
        btn1.setAlignment(Pos.CENTER);
        Button btn2 = new Button("Restart");
        btn2.setAlignment(Pos.CENTER);
        btn2.setPrefSize(150, 10);
        btn2.setStyle("-fx-font-size:15");
        btn2.setTextFill(Color.RED);
        Button btn3 = new Button("Save and Exit");
        btn3.setAlignment(Pos.CENTER);
        btn3.setPrefSize(150, 10);
        btn3.setStyle("-fx-font-size:15");
        btn3.setTextFill(Color.PURPLE);
        Button btn4 = new Button("Exit");
        btn4.setAlignment(Pos.CENTER);
        btn4.setPrefSize(150, 10);
        btn4.setStyle("-fx-font-size:15");
        btn4.setTextFill(Color.BROWN);
        paneLvl2b.add(btn1, 0 ,4);
        paneLvl2b.add(btn2, 0 ,7);
        paneLvl2b.add(btn3, 0 ,10);
        paneLvl2b.add(btn4, 0, 13);
        paneLvl1.add(paneLvl2b, 0, 5);

        EventHandler<ActionEvent> eventBtn1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                // resume game
                resumeGame(primaryStage);
            }
        };
        btn1.setOnAction(eventBtn1);
        EventHandler<ActionEvent> eventBtn2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                // restart game
                restartGame();
            }
        };
        btn2.setOnAction(eventBtn2);
        EventHandler<ActionEvent> eventBtn3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                saveGameAndExit();
            }
        };
        btn3.setOnAction(eventBtn3);
        EventHandler<ActionEvent> eventBtn4 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                //exit
                exitToMainMenu();
            }
        };
        btn4.setOnAction(eventBtn4);
    }

    private void gameOver(){
//        manager.displayMainMenu();
    }

    private void renderNextObstacle(){
        if(obstaclesOnScreen.get(0).getGroup().getTranslateY()>400){
            obstaclesOnScreen.remove(0);
        }
        Obstacle obstacle=allObstacles.get(lastObstacleId);
        double obstacleY=obstaclesOnScreen.get(obstaclesOnScreen.size()-1).getGroup().getTranslateY();
        obstacle.getGroup().setTranslateY(obstacleY-450);
        gamePlayRoot.getChildren().add(obstacle.getGroup());
        obstacle.startTransition();
        int id=lastObstacleId%maxNumofObstaclesrendered;
        colorSwitcher[id]=obstacle.getColorSwitcher();
        colorSwitcher[id].getGroup().setTranslateY(obstacleY-200);
        stars[id].setTranslateY(obstacleY);
        stars[id].setVisible(true);
        lastObstacleId++;

        obstaclesOnScreen.add(obstacle);
        //clear all obstacles
        gamePlayRoot.getChildren().clear();

        //add everything back to screen again
        for(Obstacle o:obstaclesOnScreen){
            gamePlayRoot.getChildren().add(o.getGroup());
        }
        gamePlayRoot.getChildren().add(ball);
        for(int i=0;i<maxNumofObstaclesrendered;i++){
            gamePlayRoot.getChildren().addAll(colorSwitcher[i].getGroup(),stars[i]);
        }
    }

    public void deserialise(Stage theStage){
        for(Obstacle o:allObstacles){
            o.deserialise();
        }
        try {
            createPauseScene(theStage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        gamePlayRoot=new StackPane();
        gamePlayRoot.setStyle("-fx-background-color: BLACK");

        //adding obtscales, star on screen
        for(Obstacle o:obstaclesOnScreen){
            gamePlayRoot.getChildren().add(o.getGroup());
        }
        for(int i=0;i<maxNumofObstaclesrendered;i++){
            gamePlayRoot.getChildren().addAll(stars[i],colorSwitcher[i].getGroup());
        }

        gameScene=new Scene(gamePlayRoot,360,640,Color.BLACK);
        gameScene.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.SPACE){
                player.jump();
            }
            else if(e.getCode()==KeyCode.ESCAPE){
                pauseGame(theStage);
            }
        });
    }

    public void serialise(){
        for(Obstacle o:obstaclesOnScreen){
            o.serialise();
        }
    }
}

