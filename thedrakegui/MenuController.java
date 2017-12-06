package thedrakegui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author severste
 */
public class MenuController implements Initializable {

    @FXML
    private Button closeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void onEnd(ActionEvent event) {
        System.out.println("End");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void onPCOpponent(ActionEvent event) {
        System.out.println("Pc opponent");
    }

    public void onInternetOpponent(ActionEvent event) {
        System.out.println("Internet opponent");

    }

    public void onTwoPlayers(ActionEvent event) {
        System.out.println("Two players");

    }

}
