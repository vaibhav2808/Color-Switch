package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
//import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
//import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {
//    private static final int STAR_COUNT = 20000;
//
//    private final Node[] nodes = new Node[STAR_COUNT];
//    private final double[] angles = new double[STAR_COUNT];
//    private final long[] start = new long[STAR_COUNT];
//
//    private final Random random = new Random();
//
//    @Override
//    public void start(final Stage primaryStage) {
//        Paint arr[]={Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW};
//        for (int i=0; i<STAR_COUNT; i++) {
//            nodes[i] = new RectangleObstacle(5,5,arr).getGroup();
//            angles[i] = 2.0 * Math.PI * random.nextDouble();
//            start[i] = random.nextInt(2000000000);
//        }
//        final Scene scene = new Scene(new Group(nodes), 800, 600, Color.BLACK);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                final double width = 0.5 * primaryStage.getWidth();
//                final double height = 0.5 * primaryStage.getHeight();
//                final double radius = Math.sqrt(2) * Math.max(width, height);
//                for (int i=0; i<STAR_COUNT; i++) {
//                    final Node node = nodes[i];
//                    final double angle = angles[i];
//                    final long t = (now - start[i]) % 2000000000;
//                    final double d = t * radius / 2000000000.0;
//                    node.setTranslateX(Math.cos(angle) * d + width);
//                    node.setTranslateY(Math.sin(angle) * d + height);
//                }
//            }
//        }.start();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Color Switch");
        GameplayScene(primaryStage);

    }



    public static void main(String[] args) {
        launch(args);
    }

    public static void GameplayScene(Stage primaryStage){

        StackPane root=new StackPane();
//        root.setPrefWrapLength(50000);
        root.setAlignment(Pos.CENTER);
//        root.setVgap(100);
//        root.setHgap(1000);
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
        ArrayList<Node> arrayList=new ArrayList<>();

        Paint arr[]={Color.BLUE,Color.RED,Color.GREEN,Color.YELLOW};
//        root.getChildren().add(new CircleObstacle(arr,100).getGroup());
//        root.getChildren().add(new PlusObstacle(100,arr).getGroup());
//        root.getChildren().add(new TriangleObstacle(100,arr).getGroup());
//        root.getChildren().add(new DiamondObstacle(100,120,arr).getGroup());
//        root.getChildren().add(new RectangleObstacle(100,100,arr).getGroup());
//        root.getChildren().add(new Ball());


        arrayList.add(new CircleObstacle(arr,100).getGroup());
        arrayList.add(new PlusObstacle(200,arr).getGroup());
        arrayList.add(new TriangleObstacle(200,arr).getGroup());
        arrayList.add(new DiamondObstacle(200,120,arr).getGroup());
        arrayList.add(new RectangleObstacle(200,200,arr).getGroup());
        Ball ball=new Ball();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).setTranslateY(50-350*i);
            RotateTransition transition= new RotateTransition(Duration.seconds(3),arrayList.get(i));
            transition.setInterpolator(Interpolator.LINEAR);
            transition.setByAngle(360);
            transition.setCycleCount(RotateTransition.INDEFINITE);
            transition.play();
        }
        root.getChildren().addAll(arrayList);
        root.getChildren().add(ball);
        root.setOnMouseClicked(e->{
            ball.setTranslateY(ball.getTranslateY()-60);
        });
        ball.setTranslateY(300);
//        root2.setContent(root);
//        root2.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        root2.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        root2.set
        primaryStage.setScene(thescene);
        primaryStage.show();
        System.out.println(thescene.getHeight());
        new AnimationTimer(){
            @Override
            public void handle(long now){
                ball.setTranslateY(ball.getTranslateY()+1);

                if(ball.getTranslateY()<-50) {
                    System.out.println("hello");
                    for (int i = 0; i < arrayList.size(); i++) {
                        arrayList.get(i).setTranslateY(arrayList.get(i).getTranslateY() + 5);
                    }
                    ball.setTranslateY(ball.getTranslateY()+5);
                }
            }
        }.start();

    }
}
