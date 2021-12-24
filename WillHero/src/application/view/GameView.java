package application.view;


import application.CustomEvent;
import application.Game;
import application.LevelGenerator;
import application.Main;
import application.PlayerOutEvent;
import application.PlayerOutEventHandler;
import application.Spawnable;
import application.WorldObject;
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
		root.setMaxSize(Main.DIMENSIONS.getWidth(), Main.DIMENSIONS.getHeight());
		root.setPrefSize(Main.DIMENSIONS.getWidth(),Main.DIMENSIONS.getHeight());
		root.setMinSize(Main.DIMENSIONS.getWidth(),Main.DIMENSIONS.getHeight());
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
		worldObjects.add(playerController.getView());
	}

	public void update(PlayerController playerController, Point2D PLAYER_POS) {
		playerController.getView().relocate(playerController.getModel().getPosition());
		Node playerNode = playerController.getView().getNode();
		Point2D diff = PLAYER_POS.subtract(new Point2D(playerNode.getLayoutX(), playerNode.getLayoutY()));
		double moveBy = diff.getX();
		for(WorldObject worldObject: worldObjects) {
			worldObject.getNode().setTranslateX(moveBy);
		}
	}

	public void checkCollision(PlayerController playerController) {
		Node playerNode = playerController.getView().getNode();
		for(WorldObject worldObject: worldObjects) {
				if(!worldObject.equals(playerController.getView()) && worldObject.getNode().getBoundsInParent().intersects(playerNode.getBoundsInParent())) {
					if(worldObject.isCollidable()) {
						double playerVelocityX = playerController.getModel().getVelocity().getX();
						if(playerVelocityX == 0)playerController.getModel().jumpUp();
						else playerController.getModel().setVelocity(new Point2D(playerVelocityX, 0.0));
					}
					else {
						worldObject.playerInteracted();
					}
				}
		}
	}
}