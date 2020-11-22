package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static java.lang.System.exit;

public class GameManager {
    private Game game;
    private int highScore;
    private Scene mainMenuScene;
    public GameManager(Stage primaryStage) throws FileNotFoundException {
        createMainMenuScreen(primaryStage);
        primaryStage.setScene(mainMenuScene);
    }

    public void setGame(Game g){
        this.game = g;
    }
    public void setHighScore(int h){
        this.highScore = h;
    }
    public Game getGame(){
        return game;
    }
    public int getHighScore(){
        return highScore;
    }

    public void startNewGame(Stage theStage){
        game=new Game();
        playGame(theStage);
    }

    public void savedGamesList(){

    }

    public void continuePreviousGame(){

    }

    public void saveGame(){

    }

    public void exitGame(){

    }

    public void playGame(Stage theStage){
        game.play(theStage);
    }

    private void createMainMenuScreen(Stage primaryStage) throws FileNotFoundException {
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
                System.out.println("Start game");
                startNewGame(primaryStage);

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
        mainMenuScene=primaryScene;
    }
}
