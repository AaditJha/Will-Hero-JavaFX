package application.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.controller.GameController;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class MainMenuSceneController implements Initializable {
	
	private final Image volumeOn = new Image("file:Assets/Icons/volumeOn.png");
	private final Image volumeOff = new Image("file:Assets/Icons/volumeOff.png");
	
	private TranslateTransition titleAnim;
	private final GameController gameController = new GameController();
	private boolean gameRunning;
	
	@FXML
	private HBox bottomBar;

	@FXML
	private Label coinCountLabel, highScoreCount;

	@FXML
	private Button loadGameButton, quitGameButton;

	@FXML
	private Group mainTitle, highScore;
	
	@FXML
	private Polygon topBar;
	
	@FXML
	private AnchorPane mainPane;

	@FXML
	private ImageView settingImageView;

	private volumeSetting settings;
	
	private Popup quitGamePopup, loadGamePopup;
	
	@FXML
	public void openSettings(MouseEvent event) {
		settings.swapState();
	}
	
	@FXML
	public void openLoadGameMenu(MouseEvent event) {
		Window parentWindow = mainPane.getScene().getWindow();
 		loadGamePopup.show(parentWindow,parentWindow.getX(),parentWindow.getY()+29);
	}
	
	@FXML
	public void openQuitGameMenu(MouseEvent event) throws IOException {
		Window parentWindow = mainPane.getScene().getWindow();
		quitGamePopup.show(parentWindow,parentWindow.getX(),parentWindow.getY()+29);
	}

//	@FXML
//	public void startNewGame(KeyEvent event) throws IOException {
////		System.out.println("NEW GAME STARTED");
//		
//		if(event.getCharacter().equals(keyToPlayWith)) { 
//			System.out.println(event.getEventType().getName());
//			if(gameRunning) {
//				
//			}
//			else {
//				
//				gameController.startNewGame();
//			}
//			//				primaryStage.setScene(null);
//			//				primaryStage.show();
//		}
//	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		mainPane.addEventFilter(KeyEvent.KEY_PRESSED, e->{
			if(e.getCode().equals(KeyCode.SPACE)) {
				if(!gameRunning) {
					gameRunning = true;
					System.out.println("NEW GAME STARsafTED");
					titleAnim.stop();
					ParallelTransition parallelTransition = new ParallelTransition();

					titleAnim.setDuration(Duration.millis(800));
					titleAnim.setByX(-500);
					titleAnim.setCycleCount(1);
					titleAnim.setByY(0);

					TranslateTransition bottomTransition = new TranslateTransition(Duration.millis(800), bottomBar);
					bottomTransition.setByY(500);

					TranslateTransition highScoreTransition = new TranslateTransition(Duration.millis(400), highScore);
					highScoreTransition.setByY(-250);

					TranslateTransition topTransition = new TranslateTransition(Duration.millis(500), topBar);
					topTransition.setByX(125);

					parallelTransition.getChildren().addAll(titleAnim,bottomTransition,topTransition,highScoreTransition);
					parallelTransition.play();	
				}
			}
			
		});
		
		gameController.setStage(mainPane);
		this.gameRunning = false;
		bottomBar.setAlignment(Pos.CENTER);
		bottomBar.setSpacing(100.0);
		titleAnim = new TranslateTransition(Duration.millis(2000),mainTitle);
		titleAnim.setCycleCount(Animation.INDEFINITE);
		titleAnim.setByY(15);
		titleAnim.setAutoReverse(true);
		titleAnim.play();
		

//		Stage primaryStage = (Stage)mainPane.getScene().getWindow();
//		gameController.startNewGame();
		
		settings = new volumeSetting(volumeOn, volumeOff, settingImageView);
		try {
			initializeQuitGamePopup();
			initalizeLoadGamePopup();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void initalizeLoadGamePopup() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoadGameScene.fxml"));
		AnchorPane loadGamBorderPane = (AnchorPane) fxmlLoader.load();
		loadGamePopup = new Popup();
		loadGamePopup.getContent().add(loadGamBorderPane);
		loadGamePopup.onShowingProperty().set((e)->{
			mainPane.setEffect(new GaussianBlur(5.0));
		});
		LoadGameSceneController loadGameSceneController = (LoadGameSceneController) fxmlLoader.getController();
		loadGameSceneController.setPopup(loadGamePopup);
		loadGamePopup.onHidingProperty().set((e)->{
			mainPane.setEffect(null);
		});
	}

	private void initializeQuitGamePopup() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		AnchorPane quitGamBorderPane = fxmlLoader.load(getClass().getResource("QuitGameScene.fxml").openStream());
		quitGamePopup = new Popup();
		quitGamePopup.getContent().add(quitGamBorderPane);
		quitGamePopup.onShowingProperty().set((e)->{
			mainPane.setEffect(new GaussianBlur(5.0));
		});
		QuitGameSceneController quitGameSceneController = (QuitGameSceneController) fxmlLoader.getController();
		quitGameSceneController.setPopup(quitGamePopup);
		quitGamePopup.onHidingProperty().set((e)->{
			mainPane.setEffect(null);
		});
	}
	
}