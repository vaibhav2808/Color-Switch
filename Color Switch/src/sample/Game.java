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
    private final int DistancebetweenObstacles=400;
    private long obstacleRenderedPosition;
    private ColorChangerObstacle colorSwitcher[]=new ColorChangerObstacle[maxNumofObstaclesrendered];
    private Paint arr[]={Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW};
    private Ball ball;
    private Player player;
    private GameManager manager;
    private ArrayList<Obstacle> obstacles;
    private Scene gameScene,pauseScene;
    private StackPane gamePlayRoot;
    private int obstacleOnscreen=0;
    public Game(Stage primaryStage, GameManager manager) throws FileNotFoundException {
        this.manager=manager;
        ball=new Ball();
        player=new Player(ball);
        obstacles=new ArrayList<>(100);
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
            colorSwitcher[i]=new ColorChangerObstacle();
            colorSwitcher[i].getGroup().setTranslateY(obstacleRenderedPosition-200);
            obstacles.get(i).getGroup().setTranslateY(obstacleRenderedPosition);
            gamePlayRoot.getChildren().addAll(colorSwitcher[i].getGroup(),obstacles.get(i).getGroup());
            obstacleRenderedPosition-=DistancebetweenObstacles;
            //add transition
            RotateTransition transition= new RotateTransition(Duration.seconds(3),obstacles.get(i).getGroup());
            transition.setInterpolator(Interpolator.LINEAR);
            transition.setByAngle(360);
            transition.setCycleCount(RotateTransition.INDEFINITE);
            transition.play();
        }
        gamePlayRoot.getChildren().add(ball);
        gameScene.setOnKeyPressed(e->{
            System.out.println("heloo");
            if(e.getCode()== KeyCode.SPACE){
                ball.setTranslateY(ball.getTranslateY()- 50);
            }
            else if(e.getCode()==KeyCode.ESCAPE){
                pauseGame(primaryStage);
            }
        });
    }

    @Override
    public void handle(long l) {
        ball.setTranslateY(ball.getTranslateY()+1);

        if(ball.getTranslateY()<-50) {
            for(int i = 0; i < obstacles.size(); i++) {
                Node arrayList=obstacles.get(i).getGroup();
                arrayList.setTranslateY(arrayList.getTranslateY() + 5);
                colorSwitcher[i].getGroup().setTranslateY(colorSwitcher[i].getGroup().getTranslateY()+5);
            }
            ball.setTranslateY(ball.getTranslateY()+5);
        }
        //ball out of screen
        if(ball.getTranslateY()>320){
            //code for gameover
            gameOver();
        }
        //collision with color switcher
        for(int i=0;i<maxNumofObstaclesrendered;i++){

            //colorswitch collision
            if(colorSwitcher[i].getGroup().getBoundsInParent().intersects(ball.getBoundsInParent())){
                System.out.println("collides");
            }

            //obstacle collision
            List<Node> list=obstacles.get(i).getGroup().getChildren();
            for(Node n:list){
                if(Shape.intersect((Shape)n,ball).getBoundsInLocal().getWidth()>0&&((Shape) n).getFill()==ball.getFill()||((Shape)n).getStroke()==ball.getFill()){
                    gameOver();
                }
            }
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

    public void exitToMainMenu(Stage thestage){
        //throw exception to go to GameManager
        manager.displayMainMenu(thestage);
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

    }

    public void continueGame(){

    }

    private void createObstacles(){
        obstacles.add(new CircleObstacle(arr,100));
        obstacles.add(new PlusObstacle(200,arr));
        obstacles.add(new TriangleObstacle(200,arr));
        obstacles.add(new DiamondObstacle(200,120,arr));
        obstacles.add(new RectangleObstacle(200,200,arr));

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
                exitToMainMenu(primaryStage);
            }
        };
        btn4.setOnAction(eventBtn4);
    }

    private void gameOver(){
        System.out.println("Game over");
    }
}

