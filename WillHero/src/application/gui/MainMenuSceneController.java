package application.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class MainMenuSceneController {
	
	//Change this if you want to play with a different key than default that is carriage return.
	private final String keyToPlayWith = "\r";
	
	@FXML
	private AnchorPane mainPane, tappableRegion;
	
	@FXML
	private Rectangle bottomBar;
	
	@FXML
	private Polygon topBar;
	
	@FXML
	private Label coinCountLabel;
	
	@FXML
	private Button settingsButton, loadGameButton, quitGameButton;
	
	@FXML
	private ImageView dummyPlayer, settingsButtonImg, loadGameButtonImg, quitGameButtonImg, handCursorTap;
	
	@FXML
	public void openSettings(MouseEvent event) {
		System.out.print("SETTINGS");
	}
	
	@FXML
	public void openLoadGameMenu(MouseEvent event) {
		System.out.print("LOAD GAME MENU");
	}
	
	@FXML
	public void openQuitGameMenu(MouseEvent event) {
		System.out.print("QUIT GAME MENU");
	}
	
	@FXML
	public void startNewGame(KeyEvent event) {
		if(event.getCharacter().equals(keyToPlayWith)) System.out.print("NEW GAME STARTED"+(int)event.getCharacter().charAt(0)+" ");
	}
	
}