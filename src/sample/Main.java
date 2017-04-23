package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static String mainScreen = "LoginScreen";
    public static String screen1File = "screen/LoginScreen.fxml";
    public static String dashboardScreen = "DashBoardScreen";
    public static String dashboardScreenFile = "screen/DashBoard.fxml";

    public static String screen3ID = "registerScreen";
    public static String screen3File = "Screen3.fxml";
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("CS2340 Water App");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(Main.mainScreen, Main.screen1File);
        mainContainer.loadScreen(Main.dashboardScreen, Main.dashboardScreenFile);
        mainContainer.loadScreen(Main.screen3ID, Main.screen3File);

        mainContainer.setScreen(Main.mainScreen);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
//
//    public static String screen1ID = "main";
//    public static String screen1File = "Screen1.fxml";
//    public static String screen2ID = "screen2";
//    public static String screen2File = "Screen2.fxml";
//    public static String screen3ID = "screen3";
//    public static String screen3File = "Screen3.fxml";
//
//
//    @Override
//    public void start(Stage primaryStage) {
//
//        ScreensController mainContainer = new ScreensController();
//        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
//        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
//        mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
//
//        mainContainer.setScreen(ScreensFramework.screen1ID);
//
//        Group root = new Group();
//        root.getChildren().addAll(mainContainer);
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }



    public static void main(String[] args) {
        launch(args);
    }
}
