package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
//import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
//import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.exit;

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
        GameManager manager=new GameManager(primaryStage);
//        manager.startNewGame(primaryStage);
//        mainMenu(primaryStage);
        primaryStage.show();


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

        arrayList.add(new ColorChangerObstacle().getGroup());
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
                    for (int i = 0; i < arrayList.size(); i++) {
                        arrayList.get(i).setTranslateY(arrayList.get(i).getTranslateY() + 5);
                    }
                    ball.setTranslateY(ball.getTranslateY()+5);
                }
            }
        }.start();

    }

    public void mainMenu( final Stage primaryStage ) throws FileNotFoundException {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: BLACK");
        final Scene primaryScene = new Scene(root, 360, 640);

        GridPane paneLvl1 = new GridPane();
        paneLvl1.setAlignment(Pos.CENTER);
        paneLvl1.setVgap(10);
        root.getChildren().add(paneLvl1);

        Image gameName = new Image( new FileInputStream("src/assets/GameName.png") );
        ImageView imageViewGameName = new ImageView(gameName);
        imageViewGameName.setPreserveRatio(true);
        imageViewGameName.setFitHeight(150);
        paneLvl1.add(imageViewGameName, 0 ,1);

        GridPane paneLvl2a = new GridPane();
        paneLvl2a.setAlignment(Pos.CENTER);
        Image logo = new Image( new FileInputStream("src/assets/Logo.jpg") );
        ImageView imageViewLogo = new ImageView(logo);
        imageViewLogo.setPreserveRatio(true);
        imageViewLogo.setFitHeight(150);
        paneLvl2a.add(imageViewLogo, 0 ,3);
        paneLvl1.add(paneLvl2a, 0, 4);

        GridPane paneLvl2b = new GridPane();
        paneLvl2b.setAlignment(Pos.CENTER);
        paneLvl2b.setVgap(10);
        Button btn1 = new Button("Start New Game");
        btn1.setAlignment(Pos.CENTER);
        btn1.setPrefSize(150, 10);
        btn1.setStyle("-fx-font-size:15");
        btn1.setTextFill(Color.DARKBLUE);
        Button btn2 = new Button("Load Existing Game");
        btn2.setAlignment(Pos.CENTER);
        btn2.setPrefSize(150, 10);
        btn2.setStyle("-fx-font-size:15");
        btn2.setTextFill(Color.RED);
        Button btn3 = new Button("Exit");
        btn3.setAlignment(Pos.CENTER);
        btn3.setPrefSize(150, 10);
        btn3.setStyle("-fx-font-size:15");
        btn3.setTextFill(Color.PURPLE);
        paneLvl2b.add(btn1, 0 ,4);
        paneLvl2b.add(btn2, 0 ,7);
        paneLvl2b.add(btn3, 0 ,10);
        paneLvl1.add(paneLvl2b, 0, 5);

        EventHandler<ActionEvent> eventBtn1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                try {
                    pauseMenu(primaryStage);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        };
        btn1.setOnAction(eventBtn1);
        EventHandler<ActionEvent> eventBtn2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                GridPane listPane = new GridPane();

                ListView gamesList = new ListView();
                gamesList.setPrefWidth(360);
                gamesList.setPrefHeight(600);
                gamesList.setStyle("-fx-font-size:20");
                gamesList.getItems().add("Savestate 1");
                gamesList.getItems().add("Savestate 2");
                gamesList.getItems().add("Savestate 3");

                Button goBack = new Button("Back to Main Menu");
                goBack.setAlignment(Pos.CENTER);
                goBack.setPrefSize(360, 10);
                goBack.setStyle("-fx-font-size:20");
                goBack.setTextFill(Color.DARKBLUE);

                listPane.add(gamesList, 0, 0);
                listPane.add(goBack, 0, 1);

                EventHandler<ActionEvent> eventGoBack = new EventHandler<ActionEvent>() {
                    public  void handle(ActionEvent e)
                    {
                        primaryStage.setScene( primaryScene );
                    }
                };
                goBack.setOnAction(eventGoBack);

                Scene continueGameScene = new Scene(listPane, 360, 640);
                primaryStage.setScene(continueGameScene);
            }
        };
        btn2.setOnAction(eventBtn2);
        EventHandler<ActionEvent> eventBtn3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                exit(0);
            }
        };
        btn3.setOnAction(eventBtn3);

        primaryStage.setScene( primaryScene );
    }

    public void pauseMenu( final Stage primaryStage ) throws FileNotFoundException {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: BLACK");
        final Scene pauseScene = new Scene(root, 360, 640);

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
            }
        };
        btn1.setOnAction(eventBtn1);
        EventHandler<ActionEvent> eventBtn2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                // restart game
            }
        };
        btn2.setOnAction(eventBtn2);
        EventHandler<ActionEvent> eventBtn3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                // save the game
                try {
                    mainMenu(primaryStage);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        };
        btn3.setOnAction(eventBtn3);
        EventHandler<ActionEvent> eventBtn4 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                try {
                    mainMenu(primaryStage);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        };
        btn4.setOnAction(eventBtn4);

        primaryStage.setScene( pauseScene );
    }
}
