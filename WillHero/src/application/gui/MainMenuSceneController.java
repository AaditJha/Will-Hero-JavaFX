package application.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.GameOverEvent;
import application.GamePausedEvent;
import application.GlobalData;
import application.controller.GameController;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.util.Pair;

public class MainMenuSceneController implements Initializable {

	private static final int REVIVAL_COST = 500;
	private GlobalData globalData;
	private IntegerProperty highscoreInt, totalCoinCount;
	private final Image volumeOn = new Image("file:Assets/Icons/volumeOn.png");
	private final Image volumeOff = new Image("file:Assets/Icons/volumeOff.png");
	private SequentialTransition st;
	private TranslateTransition titleAnim;
	private GameController gameController;
	private boolean gameRunning, restartPrompted;
	private LoadGameSceneController loadGameSceneController;
	private Popup pauseMenuPopup;
	public static ArrayList<GameController> savedGameList;
	private boolean boostGiven;
	private Media music = new Media(new File("src/application/sounds/music.wav").toURI().toString());
	private MediaPlayer mediaPlayer;
	@FXML
	private transient Rectangle reviveTimeBar; 

	@FXML
	private transient HBox bottomBar;

	@FXML
	private transient Label coinCountLabel, highScoreCount;

	@FXML
	private transient Button loadGameButton, quitGameButton;

	@FXML
	private transient Group mainTitle, highScore, weaponA, weaponB, endGameProgressBar, reviveMenu, boost;
	
	@FXML
	private transient Group yesRevive, noRevive;

	@FXML
	private transient Polygon topBar;

	@FXML
	private transient AnchorPane mainPane;

	@FXML
	private transient Text restartText;

	@FXML
	private transient ImageView settingImageView, ProgressBarPlayer, progressBarPrincess, pauseMenuButton;;

	private transient volumeSetting settings;

	private transient Popup quitGamePopup, loadGamePopup;

    @FXML
    void displayPauseMenu(MouseEvent event) {
		Window parentWindow = mainPane.getScene().getWindow();
 		pauseMenuPopup.show(parentWindow,parentWindow.getX(),parentWindow.getY()+29);
    }

	@FXML
	void swapWeapon(MouseEvent event) {
		if (gameController.bothWeaponUnlocked(true) && weaponB == event.getSource()) {
			gameController.swapWeapon();
		} else if (gameController.bothWeaponUnlocked(false) && weaponA == event.getSource()) {
			gameController.swapWeapon();
		}
	}
	
	@FXML
	void giveBoost(MouseEvent event) {
		if(boostGiven)return;
		boostGiven = true;
		totalCoinCount.set(totalCoinCount.get()-500);
		gameController.setInvincible(true);
		FadeTransition ft = new FadeTransition(Duration.millis(5000), boost);
		ft.setToValue(0);
		ft.play();
		ft.setOnFinished(e->{
			boost.setOpacity(1);
			boost.setVisible(false);
			gameController.setInvincible(false);
		});
	}

	@FXML
	public void openSettings(MouseEvent event) {
		settings.swapState();
		GameController.muted = !settings.isSelected();
		if(gameController.muted) {
			mediaPlayer.pause();
		}
		else {
			mediaPlayer.play();
		}
	}

	@FXML
	public void openLoadGameMenu(MouseEvent event) {
		Window parentWindow = mainPane.getScene().getWindow();
		loadGamePopup.show(parentWindow, parentWindow.getX(), parentWindow.getY() + 29);
	}

	@FXML
	public void openQuitGameMenu(MouseEvent event) throws IOException {
		Window parentWindow = mainPane.getScene().getWindow();
		quitGamePopup.show(parentWindow, parentWindow.getX(), parentWindow.getY() + 29);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		try {
//			deserializeGame(-1);
//		} catch (ClassNotFoundException | IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		try {
			initializeQuitGamePopup();
			initalizeLoadGamePopup();
			initializePauseMenuPopup();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		pauseMenuButton.setVisible(false);
		try {
			globalData = deSerializeGlobals();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mediaPlayer = new MediaPlayer(music);
		mediaPlayer.setVolume(0.1);
		mediaPlayer.setCycleCount(Animation.INDEFINITE);
		mediaPlayer.play();
		savedGameList = new ArrayList<>();
		for(int i = 0; i < 4; i++)savedGameList.add(null);
		boostGiven = false;
		gameController = new GameController();
		highscoreInt = new SimpleIntegerProperty(globalData.getHighScore());
		totalCoinCount = new SimpleIntegerProperty(globalData.getTotalCoinCount());
		coinCountLabel.textProperty().bind(totalCoinCount.asString());
		highScoreCount.textProperty().bind(highscoreInt.asString());
		boost.setVisible(false);
		weaponA.setVisible(false);
		weaponB.setVisible(false);
		restartText.setVisible(false);
		endGameProgressBar.setVisible(false);
		reviveMenu.setVisible(false);
		gameController.setStage(mainPane);
		this.gameRunning = false;
		loadGameSceneController.setRunning(gameRunning);
		bottomBar.setAlignment(Pos.CENTER);
		bottomBar.setSpacing(100.0);
		titleAnim = new TranslateTransition(Duration.millis(2000), mainTitle);
		titleAnim.setCycleCount(Animation.INDEFINITE);
		titleAnim.setByY(15);
		titleAnim.setAutoReverse(true);
		titleAnim.play();
		mainPane.addEventFilter(GameOverEvent.EVENT_TYPE, e -> {
			weaponA.setVisible(false);
			weaponB.setVisible(false);
			gameController.endGame();
			if (gameController.getScore() != 120 && !gameController.getModel().isRevived() && totalCoinCount.get() + gameController.getTotalCoinsCollected().get() >= REVIVAL_COST) {
				reviveMenu.setVisible(true);
				ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(2.0), reviveTimeBar);
				scaleTransition.setToX(0);
				scaleTransition.play();
				yesRevive.addEventFilter(MouseEvent.MOUSE_CLICKED, e2->{
					gameController.revivePlayer();
					scaleTransition.stop();
					endGameProgressBar.setScaleX(0);
					reviveMenu.setVisible(false);
					gameRunning = true;
					loadGameSceneController.setRunning(gameRunning);
					weaponA.setVisible(true);
					weaponB.setVisible(true);
					gameController.startGame();
				});
				noRevive.addEventFilter(MouseEvent.MOUSE_CLICKED, e2 -> {
					scaleTransition.stop();
					gameOverScreen();
					restartPrompted = true;
				});
				scaleTransition.setOnFinished(e2 -> {
						restartPrompted = true;
						gameOverScreen();
				});
			}
			else {
				gameOverScreen();
				restartPrompted = true;
			}
		});

		mainPane.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
			if (e.getCode().equals(KeyCode.SPACE)) {
				if(gameRunning && restartPrompted) {
					restartGame();
				}
				else if (!gameRunning) {
					if(totalCoinCount.get() > 500) {
						boost.setVisible(true);
					}
						pauseMenuButton.setVisible(true);
						settingImageView.setVisible(false);
						gameRunning = true;
						loadGameSceneController.setRunning(gameRunning);
						coinCountLabel.textProperty().bind(gameController.getTotalCoinsCollected().asString());
						titleAnim.stop();
						ParallelTransition parallelTransition = new ParallelTransition();

						titleAnim.setDuration(Duration.millis(800));
						titleAnim.setByX(-500);
						titleAnim.setCycleCount(1);
						titleAnim.setByY(0);

						TranslateTransition bottomTransition = new TranslateTransition(Duration.millis(500), bottomBar);
						bottomTransition.setByY(500);

						TranslateTransition highScoreTransition = new TranslateTransition(Duration.millis(500), highScore);
						highScoreTransition.setByY(-250);

						TranslateTransition topTransition = new TranslateTransition(Duration.millis(500), topBar);
						topTransition.setByX(125);

						parallelTransition.getChildren().addAll(titleAnim, bottomTransition, topTransition,
								highScoreTransition);
						parallelTransition.play();
						parallelTransition.setOnFinished(e2 -> {
							weaponA.setVisible(true);
							weaponB.setVisible(true);
							gameController.startGame();
						});
					}
				
			}
		});
		settings = new volumeSetting(volumeOn, volumeOff, settingImageView);
		GameController.muted = !settings.isSelected();

	}

	private void restartGame() {
		mediaPlayer.stop();
		restartPrompted = false;
		gameRunning = false;
		loadGameSceneController.setRunning(gameRunning);
        Parent root;
		try {
			int finalCoins = totalCoinCount.get() + gameController.getTotalCoinsCollected().get();
			int finalScore = gameController.getScore();
			if(finalCoins < 9999)totalCoinCount.set(finalCoins);
			if(finalScore > highscoreInt.get())highscoreInt.set(finalScore);
			try {
				serializeGlobals();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Stage primaryStage = (Stage) mainPane.getScene().getWindow();
			root = FXMLLoader.load((getClass().getResource("MainMenuScene.fxml")));
	        primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void gameOverScreen() {
		boost.setVisible(false);
		reviveMenu.setVisible(false);
		reviveTimeBar.setScaleX(1);
		restartText.setVisible(true);
		endGameProgressBar.setVisible(true);
		double dist = ((double) gameController.getScore() / 120) * 205;
		double speed = 0.05;
		TranslateTransition tPrincess = new TranslateTransition(Duration.millis(800), progressBarPrincess);
		tPrincess.setByY(-10);
		tPrincess.setAutoReverse(true);
		tPrincess.setCycleCount(Animation.INDEFINITE);
		tPrincess.play();
		st = new SequentialTransition();
		TranslateTransition t1 = new TranslateTransition(Duration.millis(400), ProgressBarPlayer);
		t1.setByY(-10);
		TranslateTransition t2 = new TranslateTransition(Duration.millis(dist / speed), ProgressBarPlayer);
		t2.setByX(dist);
		TranslateTransition t3 = new TranslateTransition(Duration.millis(400), ProgressBarPlayer);
		t3.setByY(10);
		st.getChildren().addAll(t1, t2, t3);
		st.playFromStart();
	}

	private void initalizeLoadGamePopup() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoadGameScene.fxml"));
		AnchorPane loadGamBorderPane = (AnchorPane) fxmlLoader.load();
		loadGamePopup = new Popup();
		loadGamePopup.getContent().add(loadGamBorderPane);
		loadGamePopup.onShowingProperty().set((e) -> {
			mainPane.setEffect(new GaussianBlur(5.0));
			mainPane.fireEvent(new GamePausedEvent());
		});
		loadGameSceneController = (LoadGameSceneController) fxmlLoader.getController();
		loadGameSceneController.setPopup(loadGamePopup);
		loadGameSceneController.setController(this);
		loadGamePopup.onHidingProperty().set((e) -> {
			mainPane.setEffect(null);
			mainPane.fireEvent(new GamePausedEvent());
		});
	}

	private void initializeQuitGamePopup() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		AnchorPane quitGamBorderPane = fxmlLoader.load(getClass().getResource("QuitGameScene.fxml").openStream());
		quitGamePopup = new Popup();
		quitGamePopup.getContent().add(quitGamBorderPane);
		quitGamePopup.onShowingProperty().set((e) -> {
			mainPane.setEffect(new GaussianBlur(5.0));
			mainPane.fireEvent(new GamePausedEvent());
		});
		QuitGameSceneController quitGameSceneController = (QuitGameSceneController) fxmlLoader.getController();
		quitGameSceneController.setPopup(quitGamePopup);
		quitGamePopup.onHidingProperty().set((e) -> {
			mainPane.setEffect(null);
			mainPane.fireEvent(new GamePausedEvent());
		});
	}
	
	private void initializePauseMenuPopup() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PauseMenuScene.fxml"));
		AnchorPane pauseMenuAnchorPane = (AnchorPane) fxmlLoader.load();
		pauseMenuPopup = new Popup();
		pauseMenuPopup.getContent().add(pauseMenuAnchorPane);
		pauseMenuPopup.onShowingProperty().set((e)->{
			mainPane.setEffect(new GaussianBlur(5.0));
		});
		PauseMenuSceneController pauseMenuSceneController = (PauseMenuSceneController) fxmlLoader.getController();
		pauseMenuSceneController.setPopup(pauseMenuPopup,quitGamePopup,loadGamePopup);
		pauseMenuPopup.onHidingProperty().set((e)->{
			mainPane.setEffect(null);
		});
	}

	private void serializeGlobals() throws IOException, ClassNotFoundException {
		globalData.setHighScore(highscoreInt.get());
		globalData.setTotalCoinCount(totalCoinCount.get());
		FileOutputStream fileOutputStream = new FileOutputStream("src/application/database/globalData.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(globalData);
		objectOutputStream.flush();
		objectOutputStream.close();
	}
	
	private GlobalData deSerializeGlobals() throws IOException, ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream("src/application/database/globalData.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		GlobalData globalDataRet = (GlobalData)objectInputStream.readObject();
		objectInputStream.close();
		return globalDataRet;
	}
	
	public Pair<Integer, Integer> serializeGame(int selectedSlot) throws IOException, ClassNotFoundException {
		savedGameList.set(selectedSlot, gameController);
		FileOutputStream fileOutputStream = new FileOutputStream("src/application/database/saveGameData.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(savedGameList);
		objectOutputStream.flush();
		objectOutputStream.close();
		return new Pair<Integer, Integer>(gameController.getScore(), gameController.getTotalCoinsCollected().get());
	}
	
	public void deserializeGame(int selectedSlot) throws IOException, ClassNotFoundException{
		FileInputStream fileInputStream = new FileInputStream("src/application/database/saveGameData.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		savedGameList = (ArrayList<GameController>)objectInputStream.readObject();
		if(selectedSlot >= 0) gameController = savedGameList.get(selectedSlot);
		objectInputStream.close();
	}
	
}