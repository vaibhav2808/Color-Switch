package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class GameManager implements Serializable {
    private Game game;
    private int highScore;
    private transient Scene mainMenuScene,settingsScene;
    private transient final Stage theStage;
    private Label HS;
    private transient GameSounds gameSounds;
    private SerializableColor[] theme=SerializableColor.defaultColors;
    public GameManager(Stage primaryStage) throws FileNotFoundException {
        Scanner in = new Scanner( new BufferedReader( new FileReader("./HighScore.txt")));
        if(in.hasNext()){
            highScore=in.nextInt();
        }
        gameSounds=GameSounds.getInstance();
        this.theStage=primaryStage;
        createMainMenuScreen(primaryStage);
        createSettingsScreen(primaryStage);
        primaryStage.setScene(mainMenuScene);
    }

    public void setGame(Game g){
        this.game = g;
    }
    public void setHighScore(int h){
        if(h>highScore)
        this.highScore = h;
        HS.setText("High Score: " + getHighScore());
    }
    public Game getGame(){
        return game;
    }
    public int getHighScore(){
        return highScore;
    }

    public void startNewGame(){
        try {
            game=new Game(theStage,this,theme);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        playGame(theStage);
    }

    public void savedGamesList() {
        GridPane listPane = new GridPane();
        ListView<String> gamesList = new ListView<>();
        gamesList.setPrefWidth(360);
        gamesList.setPrefHeight(600);
        gamesList.setStyle("-fx-font-size:20");
        File[] saved = new File("../savedGames").listFiles();
        if (saved != null)
            for (File f : saved) {
                gamesList.getItems().add(f.getName());
            }

        Button goBack = new Button("Back to Main Menu");
        goBack.setAlignment(Pos.CENTER);
        goBack.setPrefSize(360, 10);
        goBack.setStyle("-fx-font-size:20");
        goBack.setTextFill(Color.DARKBLUE);

        gamesList.setOnMouseClicked(e -> {
            String path = gamesList.getSelectionModel().getSelectedItem();
            continuePreviousGame("../savedGames/" + path);
        });

        listPane.add(gamesList, 0, 0);
        listPane.add(goBack, 0, 1);

        EventHandler<ActionEvent> eventGoBack = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                gameSounds.play(GameSounds.BUTTON_SOUND);
                displayMainMenu();
            }
        };
        goBack.setOnAction(eventGoBack);

        Scene continueGameScene = new Scene(listPane, 360, 640);
        theStage.setScene(continueGameScene);
    }

    public void continuePreviousGame(String path){
        ObjectInputStream in;
        try{
            in=new ObjectInputStream(new FileInputStream(path));
            GameManager newmanager=(GameManager)in.readObject();
            this.game= newmanager.getGame();
            game.setManager(this);
            game.deserialise(theStage,theme);
            playGame(theStage);
            in.close();
            File file=new File(path);
            file.delete();
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void saveGame(){
        //should store path
        game.serialise();
        TextInputDialog textInputDialog=new TextInputDialog();
        textInputDialog.setHeaderText(null);
        textInputDialog.setContentText("Enter name for saved file");
        textInputDialog.showAndWait();
        textInputDialog.setResizable(true);
        textInputDialog.setOnShown(e->{
            Platform.runLater(()->{
                textInputDialog.getDialogPane().getScene().getWindow().sizeToScene();
                textInputDialog.setResizable(false);
            });
        });
        String path="../savedGames/"+textInputDialog.getEditor().getText();
        if(path.equals("../savedGames/"))
            return;
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
        try {
            PrintWriter out = new PrintWriter( new FileWriter("./HighScore.txt"));
            out.println(highScore);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        exit(0);
    }

    public void playGame(Stage theStage){
        game.play(theStage);
    }

    public void displayMainMenu(){
        theStage.setScene(mainMenuScene);
    }

    public void displaySettingsMenu(){
        theStage.setScene(settingsScene);
    }

    private void createMainMenuScreen(Stage primaryStage) throws FileNotFoundException {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: BLACK");
        final Scene primaryScene = new Scene(root, 360, 640);

        GridPane paneLvl1 = new GridPane();
        paneLvl1.setAlignment(Pos.CENTER);
        paneLvl1.setVgap(10);
        root.getChildren().add(paneLvl1);
        
        Image settingsLogo = new Image( new FileInputStream("src/assets/Settings.png") );
        ImageView imageViewSettingsLogo = new ImageView(settingsLogo);
        imageViewSettingsLogo.setPreserveRatio(true);
        imageViewSettingsLogo.setFitHeight(20);
        Button settingsBtn = new Button("", imageViewSettingsLogo);
        settingsBtn.setAlignment(Pos.TOP_LEFT);
        settingsBtn.setStyle("-fx-background-color: BLACK; -fx-border-color: white");
        paneLvl1.add(settingsBtn, 0 ,0);
        
        EventHandler<ActionEvent> eventSettingsBtn = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                    System.out.println("Settings");
                    gameSounds.play(GameSounds.BUTTON_SOUND);
                    displaySettingsMenu();
            }
        };
        settingsBtn.setOnAction(eventSettingsBtn);

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
        paneLvl1.add(paneLvl2a, 0, 3);
        
        GridPane paneLvl2c = new GridPane();
        paneLvl2c.setAlignment(Pos.CENTER);
        paneLvl2c.setVgap(10);
        HS = new Label("High Score: " + getHighScore() );
        HS.setAlignment(Pos.CENTER);
        HS.setTextFill(Color.WHITE);
        HS.setStyle("-fx-font-size:15");
        paneLvl2c.add(HS, 0 ,2);
        paneLvl1.add(paneLvl2c, 0, 4);

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
                gameSounds.play(GameSounds.BUTTON_SOUND);
                System.out.println("Start game");
                startNewGame();
            }
        };
        btn1.setOnAction(eventBtn1);
        EventHandler<ActionEvent> eventBtn2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                gameSounds.play(GameSounds.BUTTON_SOUND);
                savedGamesList();
            }
        };
        btn2.setOnAction(eventBtn2);
        EventHandler<ActionEvent> eventBtn3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                gameSounds.play(GameSounds.BUTTON_SOUND);
                exitGame();
            }
        };
        btn3.setOnAction(eventBtn3);
        mainMenuScene=primaryScene;
    }

    private void createSettingsScreen(Stage primaryStage) throws FileNotFoundException {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: BLACK");
        final Scene primaryScene = new Scene(root, 360, 640);

        GridPane paneLvl1 = new GridPane();
        paneLvl1.setAlignment(Pos.CENTER);
        paneLvl1.setVgap(70);
        root.getChildren().add(paneLvl1);

        BorderPane paneLvl2a = new BorderPane();
        Image backLogo = new Image( new FileInputStream("src/assets/GoBack.jpg") );
        ImageView imageViewBackLogo = new ImageView(backLogo);
        imageViewBackLogo.setPreserveRatio(true);
        imageViewBackLogo.setFitHeight(20);
        Button backBtn = new Button("", imageViewBackLogo);
        backBtn.setAlignment(Pos.TOP_LEFT);
        backBtn.setStyle("-fx-background-color: BLACK; -fx-border-color: white");
        paneLvl2a.setTop(backBtn);
        paneLvl1.add(paneLvl2a, 0, 0);

        EventHandler<ActionEvent> eventBackBtn = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                // code
                gameSounds.play(GameSounds.BUTTON_SOUND);
                displayMainMenu();
            }
        };
        backBtn.setOnAction(eventBackBtn);

        TilePane paneLvl2b = new TilePane();
        paneLvl2b.setAlignment(Pos.CENTER);
        Label soundLabel = new Label("Sound: ");
        soundLabel.setStyle("-fx-font-size:20");
        soundLabel.setTextFill(Color.WHITE);
        Image soundLogo = new Image( new FileInputStream("src/assets/SoundBlack.png") );
        ImageView imageViewSoundLogo = new ImageView(soundLogo);
        imageViewSoundLogo.setPreserveRatio(true);
        imageViewSoundLogo.setFitHeight(40);
        ToggleButton soundBtn = new ToggleButton("", imageViewSoundLogo);
        paneLvl2b.getChildren().addAll(soundLabel, soundBtn);
        paneLvl1.add(paneLvl2b, 0, 1);

        soundBtn.setOnAction(e->{
            if ( soundBtn.isSelected() ){
//            sound on
                gameSounds.play(GameSounds.BUTTON_SOUND);
                GameSounds.toggle(true);
            }
            else{
//            sound off
                GameSounds.toggle(false);
            }
        });


        GridPane paneLvl2c = new GridPane();
        paneLvl2c.setAlignment(Pos.CENTER);
        paneLvl2c.setVgap(20);
        Label themeLabel = new Label("Theme:");
        themeLabel.setStyle("-fx-font-size:20");
        themeLabel.setTextFill(Color.WHITE);
        paneLvl2c.add(themeLabel, 0, 0);
        paneLvl1.add(paneLvl2c, 0, 2);

        TilePane paneLvl3a = new TilePane();
        paneLvl3a.setAlignment(Pos.CENTER);
        paneLvl3a.setHgap(5);
        RadioButton rBtn1 = new RadioButton();
        Label a1 = new Label();
        a1.setStyle("-fx-background-color: RED");
        a1.setPrefSize(20, 10);
        Label a2 = new Label();
        a2.setStyle("-fx-background-color: BLUE");
        a2.setPrefSize(20, 10);
        Label a3 = new Label();
        a3.setStyle("-fx-background-color: GREEN");
        a3.setPrefSize(20, 10);
        Label a4 = new Label();
        a4.setStyle("-fx-background-color: YELLOW");
        a4.setPrefSize(20, 10);
        paneLvl3a.getChildren().addAll(a1, a2, a3, a4, rBtn1);
        paneLvl2c.add(paneLvl3a, 0, 1);

        TilePane paneLvl3b = new TilePane();
        paneLvl3b.setAlignment(Pos.CENTER);
        paneLvl3b.setHgap(5);
        RadioButton rBtn2 = new RadioButton();
        Label b1 = new Label();
        b1.setStyle("-fx-background-color: #ABC3C9");
        b1.setPrefSize(20, 10);
        Label b2 = new Label();
        b2.setStyle("-fx-background-color: #E0DCD3");
        b2.setPrefSize(20, 10);
        Label b3 = new Label();
        b3.setStyle("-fx-background-color: #CCBE9F");
        b3.setPrefSize(20, 10);
        Label b4 = new Label();
        b4.setStyle("-fx-background-color: #382119");
        b4.setPrefSize(20, 10);
        paneLvl3b.getChildren().addAll(b1, b2, b3, b4, rBtn2);
        paneLvl2c.add(paneLvl3b, 0, 2);

        TilePane paneLvl3c = new TilePane();
        paneLvl3c.setAlignment(Pos.CENTER);
        paneLvl3c.setHgap(5);
        RadioButton rBtn3 = new RadioButton();
        Label c1 = new Label();
        c1.setStyle("-fx-background-color: #601A4A");
        c1.setPrefSize(20, 10);
        Label c2 = new Label();
        c2.setStyle("-fx-background-color: #EE442F");
        c2.setPrefSize(20, 10);
        Label c3 = new Label();
        c3.setStyle("-fx-background-color: #63ACBE");
        c3.setPrefSize(20, 10);
        Label c4 = new Label();
        c4.setStyle("-fx-background-color: #F9F4EC");
        c4.setPrefSize(20, 10);
        paneLvl3c.getChildren().addAll(c1, c2, c3, c4, rBtn3);
        paneLvl2c.add(paneLvl3c, 0, 3);

        TilePane paneLvl3d = new TilePane();
        paneLvl3d.setAlignment(Pos.CENTER);
        paneLvl3d.setHgap(5);
        RadioButton rBtn4 = new RadioButton();
        Label d1 = new Label();
        d1.setStyle("-fx-background-color: #F5793A");
        d1.setPrefSize(20, 10);
        Label d2 = new Label();
        d2.setStyle("-fx-background-color: #A95AA1");
        d2.setPrefSize(20, 10);
        Label d3 = new Label();
        d3.setStyle("-fx-background-color: #85C0F9");
        d3.setPrefSize(20, 10);
        Label d4 = new Label();
        d4.setStyle("-fx-background-color: #0F2080");
        d4.setPrefSize(20, 10);
        paneLvl3d.getChildren().addAll(d1, d2, d3, d4, rBtn4);
        paneLvl2c.add(paneLvl3d, 0, 4);

        ToggleGroup themeGroup = new ToggleGroup();
        rBtn1.setToggleGroup(themeGroup);
        rBtn2.setToggleGroup(themeGroup);
        rBtn3.setToggleGroup(themeGroup);
        rBtn4.setToggleGroup(themeGroup);
        settingsScene=primaryScene;

        themeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton)themeGroup.getSelectedToggle();
                if (rb != null) {
                    if(rb.equals(rBtn1))
                        theme=SerializableColor.defaultColors;
                    else if(rb.equals(rBtn2))
                        theme=SerializableColor.elegantColors;
                    else if(rb.equals(rBtn3))
                        theme=SerializableColor.zestyColors;
                    else
                        theme=SerializableColor.retroColors;
                }
            }
        });
    }
    
    public Stage getTheStage(){
        return theStage;
    }
}
