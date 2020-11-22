package sample;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
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
}
