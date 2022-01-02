package application.view;


import java.io.IOException;

import application.Abyss;
import application.Chest;
import application.Coin;
import application.CustomEvent;
import application.FallingPlatform;
import application.Game;
import application.GreenOrcView;
import application.Lance;
import application.LevelGenerator;
import application.Main;
import application.OrcsController;
import application.OrcsView;
import application.PlayerOutEvent;
import application.PlayerOutEventHandler;
import application.Spawnable;
import application.ThrowingKnives;
import application.Weapon;
import application.WorldObject;
import application.controller.PlayerController;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
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
	private boolean innerSpaceControl;
	private ObservableList<WorldObject> worldObjects;
	private AnchorPane root;
	
	public GameView() {
		innerSpaceControl = false;
		worldObjects = FXCollections.observableArrayList();
	}

	public void setStageScene(AnchorPane rootPane, PlayerController playerController, ObservableList<OrcsController>orcsControllers) throws IOException {
		this.root = rootPane;
		LevelGenerator.generate(worldObjects,orcsControllers);
		for(WorldObject worldObject: worldObjects) {
			root.getChildren().add(worldObject.getNode());
		}
		root.getChildren().add(playerController.getView().getNode());
		worldObjects.add(playerController.getView());
		playerController.getModel().getHelmet().getThrowingKnife().setRoot(root);
		playerController.getModel().getHelmet().getLance().setRoot(root);
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

	public void checkCollision(PlayerController playerController, ObservableList<OrcsController>orcsControllers, IntegerProperty totalCoinsCollected, boolean spacePressed) {
		Node playerNode = playerController.getView().getNode();
		if(Abyss.hasFallen(playerNode))System.out.println("MARA");
		
		if(playerController.getModel().getHelmet().getEquippedWeapon()!= null) {
			
			if(playerController.getModel().getHelmet().getEquippedWeapon() instanceof ThrowingKnives) {
				ThrowingKnives throwingKnives = (ThrowingKnives) playerController.getModel().getHelmet().getEquippedWeapon();
				for(OrcsController orcsController: orcsControllers) {
				ObservableList<ImageView> throwingKnivesList = throwingKnives.getList();
				for(int i = 0; i < throwingKnivesList.size(); i++) {
					if(orcsController.getView().getNode().getBoundsInParent().intersects(throwingKnivesList.get(i).getBoundsInParent())) {
						throwingKnives.damageOrc(orcsController,i);
						}
					}
				}
			}
			else {
				Lance lance = (Lance) playerController.getModel().getHelmet().getEquippedWeapon();
				for(OrcsController orcsController: orcsControllers) {
					if(orcsController.getView().getNode().getBoundsInParent().intersects(lance.getNode().getBoundsInParent())) {
						if(!innerSpaceControl && spacePressed) {
							innerSpaceControl = true;
							lance.damageOrc(orcsController);
							break;
						}
						if(!spacePressed && innerSpaceControl)innerSpaceControl = false;
					}
				}
			}
		}


		for(WorldObject worldObject: worldObjects) {
			for(OrcsController orcsController: orcsControllers) {
				if(Abyss.hasFallen(orcsController.getView().getNode())) {
					System.out.println("ORC MARA");
				}
				if(!worldObject.equals(orcsController.getView()) && !worldObject.equals(playerController.getView()) &&
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
					
					if(worldObject instanceof Chest) {
						((Chest)worldObject).playerInteracted(playerController,totalCoinsCollected);
					}
					else if(worldObject instanceof Coin) {
						((Coin)worldObject).playerInteracted(playerController, totalCoinsCollected, worldObjects);
					}
					else {
						worldObject.playerInteracted(playerController);
					}
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