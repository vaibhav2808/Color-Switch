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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import static java.lang.System.exit;

public class GameManager implements Serializable {
    private Game game;
    private int highScore;
    private transient Scene mainMenuScene;
    private transient final Stage theStage;
    public GameManager(Stage primaryStage) throws FileNotFoundException {
        this.theStage=primaryStage;
        createMainMenuScreen(primaryStage);
        primaryStage.setScene(mainMenuScene);
    }

    public void setGame(Game g){
        this.game = g;
    }
    public void setHighScore(int h){
        if(h>highScore)
        this.highScore = h;
    }
    public Game getGame(){
        return game;
    }
    public int getHighScore(){
        return highScore;
    }

    public void startNewGame(){
        try {
            game=new Game(theStage,this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        playGame(theStage);
    }

    public void savedGamesList(){
        FileChooser fileChooser=new FileChooser();
        Button button = new Button("Select File");
        button.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(theStage);
            continuePreviousGame(selectedFile.getAbsolutePath());
        });

        VBox vBox = new VBox(button);
        Scene scene = new Scene(vBox, 360, 640);
        theStage.setScene(scene);
    }

    public void continuePreviousGame(String path){
        ObjectInputStream in;
        try{
            in=new ObjectInputStream(new FileInputStream(path));
            GameManager newmanager=(GameManager)in.readObject();
            this.game= newmanager.getGame();
            game.setManager(this);
            game.deserialise(theStage);
            playGame(theStage);
            in.close();
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void saveGame(){
//        DirectoryChooser directoryChooser=new DirectoryChooser();
//        Button button =new Button("Select save location");
//        button.setOnAction(e->{
//            File file=directoryChooser.showDialog(theStage);
//        });
        //should store path
        game.serialise();
        String path="savedgame1.txt";
        ObjectOutputStream out;
        try{
            out=new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(this);
            out.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void exitGame(){
        exit(0);
    }

    public void playGame(Stage theStage){
        game.play(theStage);
    }

    public void displayMainMenu(){
        theStage.setScene(mainMenuScene);
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
                startNewGame();
            }
        };
        btn1.setOnAction(eventBtn1);
        EventHandler<ActionEvent> eventBtn2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
//                GridPane listPane = new GridPane();
//                ListView gamesList = new ListView();
//                gamesList.setPrefWidth(360);
//                gamesList.setPrefHeight(600);
//                gamesList.setStyle("-fx-font-size:20");
//                gamesList.getItems().add("Savestate 1");
//                gamesList.getItems().add("Savestate 2");
//                gamesList.getItems().add("Savestate 3");
//
//                Button goBack = new Button("Back to Main Menu");
//                goBack.setAlignment(Pos.CENTER);
//                goBack.setPrefSize(360, 10);
//                goBack.setStyle("-fx-font-size:20");
//                goBack.setTextFill(Color.DARKBLUE);
//
//                listPane.add(gamesList, 0, 0);
//                listPane.add(goBack, 0, 1);
//
//                EventHandler<ActionEvent> eventGoBack = new EventHandler<ActionEvent>() {
//                    public  void handle(ActionEvent e)
//                    {
//                  scoreLabel.setTextFill(Color.WHITE);      primaryStage.setScene( primaryScene );
//                    }
//                };
//                goBack.setOnAction(eventGoBack);
//
//                Scene continueGameScene = new Scene(listPane, 360, 640);
//                primaryStage.setScene(continueGameScene);
                savedGamesList();
            }
        };
        btn2.setOnAction(eventBtn2);
        EventHandler<ActionEvent> eventBtn3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                exitGame();
            }
        };
        btn3.setOnAction(eventBtn3);
        mainMenuScene=primaryScene;
    }
}
