package sample;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
public class Game extends AnimationTimer {
    private final int maxNumofObstaclesrendered=5;
    private final int DistancebetweenObstacles=400;
    private long obstacleRenderedPosition;
    private ColorChangerObstacle colorSwitcher[]=new ColorChangerObstacle[maxNumofObstaclesrendered];
    private Paint arr[]={Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW};
    private Ball ball;
    private Player player;
    private GameManager manager;
    private ArrayList<Obstacle> obstacles;
    private Scene gamescene;
    private StackPane root;
    private int obstacleOnscreen=0;
    public Game(){
        ball=new Ball();
        player=new Player(ball);
        obstacles=new ArrayList<>(100);
        root=new StackPane();
        gamescene=new Scene(root,360,640,Color.BLACK);
        createObstacles();
        obstacleRenderedPosition=50;


        for (int i = 0; i < maxNumofObstaclesrendered ; i++) {
            colorSwitcher[i]=new ColorChangerObstacle();
            colorSwitcher[i].getGroup().setTranslateY(obstacleRenderedPosition-200);
            obstacles.get(i).getGroup().setTranslateY(obstacleRenderedPosition);
            root.getChildren().addAll(colorSwitcher[i].getGroup(),obstacles.get(i).getGroup());
            obstacleRenderedPosition-=DistancebetweenObstacles;
            //add transition
            RotateTransition transition= new RotateTransition(Duration.seconds(3),obstacles.get(i).getGroup());
            transition.setInterpolator(Interpolator.LINEAR);
            transition.setByAngle(360);
            transition.setCycleCount(RotateTransition.INDEFINITE);
            transition.play();
        }
        root.getChildren().add(ball);
        gamescene .setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.SPACE){
                ball.setTranslateY(ball.getTranslateY()- 50);
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

    public void play(Stage thestage){
        thestage.setScene(gamescene);
        this.start();
    }

    public void exitToMainMenu(){
        //throw exception to go to GameManager
    }

    public void resumeGame(){
        this.start();
    }

    public void pauseGame(){
        this.stop();
        //add code for display pause game menu
    }

    public void restartGame(){

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
}
