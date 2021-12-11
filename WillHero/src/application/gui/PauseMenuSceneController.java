package application.gui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	
	public void setPopup(Popup popup) {
		this.popup = popup;
	}

	@FXML
	public void resumeGame(MouseEvent event) {
		popup.hide();
	}

	@FXML
	public void loadSaveGame(MouseEvent event) {
		System.out.println("LOAD AND SAVE GAME MENU");
	}

	@FXML
	public void quitGame(MouseEvent event) throws IOException {
		System.out.println("QUIT GAME MENU");
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
