package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML protected Button btPosts;
    @FXML protected Button btAlbuns;
    @FXML protected Button btTodos;
    @FXML protected Button btExit;

    public void btPostsPage(ActionEvent e) {
        Main.changeScreen("posts");
    }

    public void btAlbunsPage(ActionEvent e) {
        Main.changeScreen("albuns");
    }

    public void btTodosPage(ActionEvent e) {
        Main.changeScreen("todos");
    }

    public void btExitAction(ActionEvent e) {
        Platform.exit();
    }
}
