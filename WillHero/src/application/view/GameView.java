package application.view;

import application.CustomEvent;
import application.Game;
import application.LevelGenerator;
import application.Main;
import application.PlayerOutEvent;
import application.PlayerOutEventHandler;
import application.Spawnable;
import application.WorldObject;
import application.WorldVec2;
import application.controller.PlayerController;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameView {
	private ObservableList<WorldObject> worldObjects;
	private Pane root;
	
	public GameView() {
		worldObjects = FXCollections.observableArrayList();
		root = new Pane();
		root.setMaxSize(Main.DIMENSIONS.getX(),Main.DIMENSIONS.getY());
		root.setPrefSize(Main.DIMENSIONS.getX(),Main.DIMENSIONS.getY());
		root.setMinSize(Main.DIMENSIONS.getX(),Main.DIMENSIONS.getY());
		root.setStyle("-fx-background-color:  linear-gradient(from 25% 25% to 100% 100%, #2980B9, #6DD5FA)");
	}

	public void setStageScene(Stage primaryStage, PlayerController playerController) {
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		LevelGenerator.generate(worldObjects);
		for(WorldObject worldObject: worldObjects) {
			root.getChildren().add(worldObject.getNode());
		}
		root.getChildren().add(playerController.getView().getNode());
		playerController.jump();
	}
}