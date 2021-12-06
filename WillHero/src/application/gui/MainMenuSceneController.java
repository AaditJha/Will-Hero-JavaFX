package application.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

public class MainMenuSceneController implements Initializable {
	
	//Change this if you want to play with a different key than default that is carriage return.
	private final String keyToPlayWith = "\r";
	
	@FXML
	private AnchorPane mainPane, cloudsContainerPane;
	
	@FXML
	private ArrayList<ImageView> clouds;
	
	@FXML
	private Rectangle bottomBar;
	
	@FXML
	private Polygon topBar;
	
	@FXML
	private Label coinCountLabel, highScoreCount;
	
	@FXML
	private Button settingsButton, loadGameButton, quitGameButton;
	
	@FXML
	private ImageView dummyPlayer, settingsButtonImg, loadGameButtonImg, quitGameButtonImg;
	
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		initializeClouds();
	}

	private void initializeClouds() {
		clouds = new ArrayList<ImageView>();
		double[] cloudSizes = {0.20,0.25,0.30,0.20};
		int[] startingPoints = {
				0,	-140,
			  -80,	  40,
			 -200,	-180,
			  100,   140
		};
		final int cloudCount = 4;
		for(int i = 1; i <= cloudCount; i++) {
			ImageView tempCloud = new ImageView("file:Assets/Background/Clouds"+i+".png");
			tempCloud.setScaleX(cloudSizes[i-1]);
			tempCloud.setScaleY(cloudSizes[i-1]);
			tempCloud.relocate(startingPoints[2*(i-1)], startingPoints[2*i-1]);
			clouds.add(tempCloud);
		}
		cloudsContainerPane.getChildren().addAll(clouds);
	}
	
}