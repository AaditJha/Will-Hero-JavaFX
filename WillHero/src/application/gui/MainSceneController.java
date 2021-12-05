package application.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainSceneController implements Initializable {
	@FXML
	private ImageView bgSplashScreen;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		SequentialTransition sequentialTransition = new SequentialTransition();
		
		FadeTransition fadeIn = getFadeTransition(0.0,1.0,2000);
		FadeTransition stayOn = getFadeTransition(1.0,1.0,1000);
		FadeTransition fadeOut = getFadeTransition(1.0,0.0,2000);
		
		sequentialTransition.getChildren().addAll(fadeIn,stayOn,fadeOut);
		
		sequentialTransition.setOnFinished((e)->{
			try {
				showMainMenu();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		sequentialTransition.play();
	}

	private void showMainMenu() throws IOException {
		
		Stage primaryStage = (Stage) bgSplashScreen.getScene().getWindow();
		bgSplashScreen.setImage(null);
        Parent root = FXMLLoader.load((getClass().getResource("MainMenuScene.fxml")));
        primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
	}

	private FadeTransition getFadeTransition(double from, double to, int duration) {
		FadeTransition transition = new FadeTransition(Duration.millis(duration),bgSplashScreen);
		transition.setFromValue(from);
		transition.setToValue(to);
		return transition;
	}

}
