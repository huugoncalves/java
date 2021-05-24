package Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private static Stage stage;
    private static Scene homeScene,postsScene,albunsScene,todosScene;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;

        Parent fxmlHome = FXMLLoader.load(getClass().getResource("../View/HomePage.fxml"));
        homeScene = new Scene(fxmlHome);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        homeScene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        homeScene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        Parent fxmlPosts = FXMLLoader.load(getClass().getResource("../View/PostsPage.fxml"));
        postsScene = new Scene(fxmlPosts);
        postsScene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        postsScene.setOnMouseDragged(event -> {
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
        });
        Parent fxmlAlbuns = FXMLLoader.load(getClass().getResource("../View/AlbunsPage.fxml"));
        albunsScene = new Scene(fxmlAlbuns);
        albunsScene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        albunsScene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        Parent fxmlTodos = FXMLLoader.load(getClass().getResource("../View/TodosPage.fxml"));
        todosScene = new Scene(fxmlTodos);
        todosScene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        todosScene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    public static void changeScreen(String src){
        switch (src){
            case "home":
                    stage.setScene(homeScene);
                break;
            case "posts":
                    stage.setScene(postsScene);
                break;
            case "albuns":
                    stage.setScene(albunsScene);
                break;
            case "todos":
                    stage.setScene(todosScene);
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
