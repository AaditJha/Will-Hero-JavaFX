package application;
	
import application.controller.GameController;
import application.controller.PlayerController;
import application.view.GameView;
import application.view.PlayerView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Main extends Application {
	public static final Dimension2D DIMENSIONS = new Dimension2D(360, 640);
	
	private final GameController gameController;
	private boolean gameRunning;
	
	public Main() {
		System.out.println("RUNNING");
		this.gameController = new GameController();
		this.gameRunning = false;
	}

	
	
	@Override
	public void start(Stage primaryStage) {
		try {

//			Parent root = FXMLLoader.load(getClass().getResource("gui/GameplayScene.fxml"));
//			Scene scene = new Scene(root);
//			primaryStage.setScene(scene);
			primaryStage.setTitle("Will Hero");
			Image icon = new Image("file:Assets/Icons/main_icon.ico");
			primaryStage.setResizable(false);
			primaryStage.getIcons().addAll(icon);
			gameController.setStage(primaryStage);
			primaryStage.show();
			
			primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent key) {
					if(key.getCode().equals(KeyCode.SPACE) && !gameRunning) {
						gameRunning = true;
						System.out.println("NEW GAME STARTED");
						gameController.startNewGame();
					}
				}
			});
//			EventHandler<ActionEvent> act = new EventHandler<ActionEvent>() {
//
//				@Override
//				public void handle(ActionEvent arg0) {
//
//					System.out.println("HELLO");
//				}
//				
//			};
//			Timeline checker = new Timeline();
//			checker.setCycleCount(Animation.INDEFINITE);
//			checker.getKeyFrames().add(new KeyFrame(Duration.millis(10), act));
////			checker.play();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() {
		System.out.println("CLEAN UP");
		System.exit(0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
