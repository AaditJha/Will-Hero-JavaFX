package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("gui/MainScene.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
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

		System.exit(0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
