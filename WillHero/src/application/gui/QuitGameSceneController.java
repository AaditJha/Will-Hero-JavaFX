package application.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class QuitGameSceneController implements Initializable {

	@FXML
	private Button yesButton, noButton;
	
	private boolean quitGameRequested;
	
	private Popup popup;
	
	public boolean isQuitGameRequested() {
		return quitGameRequested;
	}
	
	public void setPopup(Popup popup) {
		this.popup = popup;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		quitGameRequested = false;
	}
	
	public void resumeGame(MouseEvent event) {
		popup.hide();
	}
	
	public void quitGame(MouseEvent event) {
		quitGameRequested = true;
		popup.hide();
	}

}
