package application.gui;

import java.io.IOException;

import application.GamePausedEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PauseMenuSceneController {
	private Popup popup;
	
	@FXML
	private ImageView playButton;
	@FXML
	private ImageView loadSaveButton;
	@FXML
	private ImageView quitButton;
	@FXML
	private ImageView restartButton;

	private Popup quitGamePopup;

	private Popup loadSavePopup;

	
	public void setPopup(Popup popup, Popup quitGamePopup, Popup loadSavePopup) {
		this.popup = popup;
		this.quitGamePopup = quitGamePopup;
		this.loadSavePopup = loadSavePopup;
	}

	@FXML
	public void resumeGame(MouseEvent event) {
		popup.hide();
	}

	@FXML
	public void loadSaveGame(MouseEvent event) {
		loadSavePopup.show(popup.getOwnerWindow());
		popup.hide();
	}

	@FXML
	public void quitGame(MouseEvent event) throws IOException {
		quitGamePopup.show(popup.getOwnerWindow());
		popup.hide();
	}

	@FXML
	public void restartGame(MouseEvent event) throws IOException {
		Stage primaryStage = (Stage)popup.getOwnerWindow();
        Parent root = FXMLLoader.load((getClass().getResource("MainMenuScene.fxml")));
        primaryStage.setScene(new Scene(root));
		primaryStage.show();
		popup.hide();
	}

}
