package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class AlbunsController {
    @FXML protected Button btBack;
    @FXML protected Button btExit;
    @FXML private WebView webView_Albuns;
    @FXML private void initialize(){
        WebEngine engine = webView_Albuns.getEngine();
        engine.load("https://jsonplaceholder.typicode.com/albuns");
    }

    public void btBackHome(ActionEvent e) {
        Main.changeScreen("home");
    }
    public void exitAction(ActionEvent e) {
        Platform.exit();
    }
}
