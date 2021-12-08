package application.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class MainMenuSceneController implements Initializable {
	
	//Change this if you want to play with a different key than default that is carriage return.
	private final String keyToPlayWith = "\r";
	private final Image volumeOn = new Image("file:Assets/Icons/volumeOn.png");
	private final Image volumeOff = new Image("file:Assets/Icons/volumeOff.png");
	
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
	private Button loadGameButton, quitGameButton;
	
	
	@FXML
	private ImageView dummyPlayer, loadGameButtonImg, quitGameButtonImg, mainTitle, settingImageView;
	
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
	
	@FXML
	public void startNewGame(KeyEvent event) {
		if(event.getCharacter().equals(keyToPlayWith)) System.out.print("NEW GAME STARTED"+(int)event.getCharacter().charAt(0)+" ");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		initializeClouds();
		ParallelTransition parallelTransition = new ParallelTransition();
		animatePlayer(parallelTransition);
		animateClouds(parallelTransition);
		animateTitle(parallelTransition);
		parallelTransition.play();
		
		settings = new volumeSetting(volumeOn, volumeOff, settingImageView);
		try {
			initializeQuitGamePopup();
			initalizeLoadGamePopup();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void initalizeLoadGamePopup() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		AnchorPane loadGamBorderPane = fxmlLoader.load(getClass().getResource("LoadGameScene.fxml").openStream());
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
			if(quitGameSceneController.isQuitGameRequested()) {
				Platform.exit();
			}
		});
	}

	private void initializeClouds() {
		clouds = new ArrayList<ImageView>();
		double[] cloudSizes = {0.15,0.20,0.25,0.20};
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

	private void animatePlayer(ParallelTransition parallelTransition) {
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(600),dummyPlayer);
		translateTransition.setAutoReverse(true);
		translateTransition.setCycleCount(Animation.INDEFINITE);
		translateTransition.setByY(-50);
		translateTransition.setInterpolator(Interpolator.EASE_OUT);
		parallelTransition.getChildren().add(translateTransition);
	}
	
	private void animateClouds(ParallelTransition parallelTransition) {
		for(ImageView cloud: clouds) {
			TranslateTransition translateTransition = new TranslateTransition(Duration.millis(10000),cloud);
			translateTransition.setCycleCount(Animation.INDEFINITE);
			translateTransition.setFromX(360);
			translateTransition.setToX(-360);
			translateTransition.setInterpolator(Interpolator.LINEAR);
			parallelTransition.getChildren().add(translateTransition);
		}
	}

	private void animateTitle(ParallelTransition parallelTransition) {
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(2000),mainTitle);
		translateTransition.setCycleCount(Animation.INDEFINITE);
		translateTransition.setByY(10);
		translateTransition.setAutoReverse(true);
		parallelTransition.getChildren().add(translateTransition);
	}

	
}

class volumeSetting {
	private ImageView graphic;
	private Image selectedImage, unselectedImage;
	private boolean selected;
	
	volumeSetting(Image selectedImage, Image unselectedImage, ImageView ref) {
		this.graphic = ref;
		this.selectedImage = selectedImage;
		this.unselectedImage = unselectedImage;
		this.selected = true;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void swapState() {
		if(this.isSelected()) {
			graphic.setImage(unselectedImage);
			selected = false;
		}
		else {
			graphic.setImage(selectedImage);
			selected = true;
		}
	}
}