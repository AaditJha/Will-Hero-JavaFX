package application;
	
import application.view.GameView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;


public class Main extends Application {
	private GameView gameView = new GameView();
	@Override
	public void start(Stage primaryStage) {
		try {

//			Parent root = FXMLLoader.load(getClass().getResource("gui/MainScene.fxml"));
//			Scene scene = new Scene(root);
//			primaryStage.setScene(scene);
			
			gameView.setStageScene(primaryStage);

			primaryStage.setTitle("Will Hero");
			Image icon = new Image("file:Assets/Icons/main_icon.ico");
			primaryStage.setResizable(false);
			primaryStage.getIcons().addAll(icon);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() {
		System.out.println("CLEAN UP");
		gameView.stopAnimations();
		System.exit(0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
