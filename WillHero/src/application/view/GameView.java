package application.view;


import java.io.IOException;

import application.CustomEvent;
import application.FallingPlatform;
import application.Game;
import application.GreenOrcView;
import application.LevelGenerator;
import application.Main;
import application.OrcsController;
import application.OrcsView;
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
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
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

	public void setStageScene(Stage primaryStage, PlayerController playerController, ObservableList<OrcsController>orcsControllers) throws IOException {

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		LevelGenerator.generate(worldObjects,orcsControllers);
		for(WorldObject worldObject: worldObjects) {
			root.getChildren().add(worldObject.getNode());
		}
		root.getChildren().add(playerController.getView().getNode());
		worldObjects.add(playerController.getView());
	}

	public void update(PlayerController playerController, Point2D PLAYER_POS, ObservableList<OrcsController> orcsControllers) {
		for(OrcsController orcsController: orcsControllers) {
			orcsController.getView().relocate(orcsController.getModel().getPosition());
		}
		playerController.getView().relocate(playerController.getModel().getPosition());
		Node playerNode = playerController.getView().getNode();
		Point2D diff = PLAYER_POS.subtract(new Point2D(playerNode.getLayoutX(), playerNode.getLayoutY()));
		double moveBy = diff.getX();
		for(WorldObject worldObject: worldObjects) {
			worldObject.getNode().setTranslateX(moveBy);
		}
	}

	public void checkCollision(PlayerController playerController, ObservableList<OrcsController>orcsControllers) {
		Node playerNode = playerController.getView().getNode();
		for(WorldObject worldObject: worldObjects) {
			for(OrcsController orcsController: orcsControllers) {
				if(!worldObject.equals(orcsController.getView()) && !worldObject.equals(playerNode) &&
						worldObject.getNode().getBoundsInParent().intersects(orcsController.getView().getNode().getBoundsInParent())) {
						orcsController.getModel().jumpUp();
//					double orcVelocityX = orcsController.getModel().getVelocity().getX();
//					if(orcVelocityX == 0)orcsController.getModel().jumpUp();
//					else orcsController.getModel().setVelocity(new Point2D(orcVelocityX, 0.0));
				}
			}
		}
		for(WorldObject worldObject: worldObjects) {
				if(!worldObject.equals(playerController.getView()) && worldObject.getNode().getBoundsInParent().intersects(playerNode.getBoundsInParent())) {
					worldObject.playerInteracted(playerController);
//					if(worldObject.isCollidable()) {
//						double playerVelocityX = playerController.getModel().getVelocity().getX();
//						if(playerVelocityX == 0)playerController.getModel().jumpUp();
//						else playerController.getModel().setVelocity(new Point2D(playerVelocityX, 0.0));
//						
//						if(worldObject instanceof FallingPlatform) {
//							worldObject.playerInteracted(playerController);
//						}
//					}
//					else {
//						worldObject.playerInteracted(playerController);
//					}
				}
		}
	}
}