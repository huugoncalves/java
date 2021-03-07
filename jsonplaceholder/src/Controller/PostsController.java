package Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class PostsController {

    @FXML protected Button btBack;
    @FXML protected Button btExit;
    @FXML private WebView webView_Posts;
    @FXML private void initialize(){
        WebEngine engine = webView_Posts.getEngine();
        engine.load("http://jsonplaceholder.typicode.com/posts");
    }

    public void btBackHome(ActionEvent e) {
        Main.changeScreen("home");
    }
    public void exitAction(ActionEvent e) {
        Platform.exit();
    }
}
