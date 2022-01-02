package application.view;


import java.io.IOException;
import java.io.Serializable;

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
import application.Platform;
import application.GamePausedEvent;
import application.GamePausedEventHandler;
import application.Spawnable;
import application.ThrowingKnives;
import application.Weapon;
import application.WorldObject;
import application.controller.GameController;
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

public class GameView implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean innerSpaceControl, bossGenerated;
	private transient ObservableList<WorldObject> worldObjects;
	private transient AnchorPane root;
	
	public GameView() {
		innerSpaceControl = false;
		bossGenerated = false;
		worldObjects = FXCollections.observableArrayList();
	}

	public void setStageScene(AnchorPane rootPane, PlayerController playerController, ObservableList<OrcsController>orcsControllers) throws IOException {
		this.root = rootPane;
		LevelGenerator.getInstance().generate(worldObjects,orcsControllers,rootPane);
		for(WorldObject worldObject: worldObjects) {
			root.getChildren().add(worldObject.getNode());
		}
		root.getChildren().add(playerController.getView().getNode());
		worldObjects.add(playerController.getView());
		playerController.getModel().getHelmet().getThrowingKnife().setRoot(root);
		playerController.getModel().getHelmet().getLance().setRoot(root);
	}

	public void update(PlayerController playerController, Point2D PLAYER_POS, ObservableList<OrcsController> orcsControllers, IntegerProperty playerPosScore) {
		for(OrcsController orcsController: orcsControllers) {
			orcsController.getView().relocate(orcsController.getModel().getPosition());
		}
		playerController.getView().relocate(playerController.getModel().getPosition());
		Node playerNode = playerController.getView().getNode();
		Point2D diff = PLAYER_POS.subtract(new Point2D(playerNode.getLayoutX(), playerNode.getLayoutY()));
		double moveBy = diff.getX();
		double dist = Math.abs(worldObjects.get(0).getNode().getTranslateX());
		playerPosScore.set((int) (dist/200));
		for(WorldObject worldObject: worldObjects) {
			worldObject.getNode().setTranslateX(moveBy);
		}
		if(playerPosScore.get() == 115 && !bossGenerated) {
			bossGenerated = true;
			LevelGenerator.getInstance().generateBossOrc(worldObjects, orcsControllers,root);
		}
	}

	public void checkCollision(PlayerController playerController, ObservableList<OrcsController>orcsControllers, IntegerProperty totalCoinsCollected, boolean spacePressed) {
		Node playerNode = playerController.getView().getNode();
		if(Abyss.hasFallen(playerNode)) {
			playerController.killed(true);
		}
		ObservableList<OrcsController> orcsToRemove = FXCollections.observableArrayList();
		for(OrcsController orcsController: orcsControllers) {
			if(Abyss.hasFallen(orcsController.getView().getNode())) {
				orcsToRemove.add(orcsController);
			}
		}
		for(OrcsController orcToRemove: orcsToRemove) {
			orcsControllers.remove(orcToRemove);
			orcToRemove.getView().despawn(worldObjects);
			root.getChildren().add(orcToRemove.getView().getNode());
			orcToRemove.getView().deathAnim(totalCoinsCollected);
		}
		orcsToRemove = FXCollections.observableArrayList();
		if(playerController.getModel().getHelmet().getEquippedWeapon()!= null) {
			if(playerController.getModel().getHelmet().getEquippedWeapon() instanceof ThrowingKnives) {
				ThrowingKnives throwingKnives = (ThrowingKnives) playerController.getModel().getHelmet().getEquippedWeapon();
				for(OrcsController orcsController: orcsControllers) {
				ObservableList<ImageView> throwingKnivesList = throwingKnives.getList();
				for(int i = 0; i < throwingKnivesList.size(); i++) {
					if(orcsController.getView().getNode().getBoundsInParent().intersects(throwingKnivesList.get(i).getBoundsInParent())) {
							if(throwingKnives.damageOrc(orcsController,i)) {
								orcsToRemove.add(orcsController);
							}
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
							if(lance.damageOrc(orcsController)){
								orcsToRemove.add(orcsController);
							}
							break;
						}
						if(!spacePressed && innerSpaceControl)innerSpaceControl = false;
					}
				}
			}
			for(OrcsController orcToRemove: orcsToRemove) {
				orcsControllers.remove(orcToRemove);
				orcToRemove.getView().despawn(worldObjects);
				root.getChildren().add(orcToRemove.getView().getNode());
				orcToRemove.getView().deathAnim(totalCoinsCollected);
			}
		}


		for(WorldObject worldObject: worldObjects) {
			for(OrcsController orcsController: orcsControllers) {
				if(!(worldObject instanceof OrcsView) && !worldObject.equals(orcsController.getView()) && !worldObject.equals(playerController.getView()) &&
						worldObject.getNode().getBoundsInParent().intersects(orcsController.getView().getNode().getBoundsInParent()) &&
						!(worldObject instanceof Coin)) {
						orcsController.getModel().jumpUp();
//					double orcVelocityX = orcsController.getModel().getVelocity().getX();
//					if(orcVelocityX == 0)orcsController.getModel().jumpUp();
//					else orcsController.getModel().setVelocity(new Point2D(orcVelocityX, 0.0));
				}
			}
		}
		
		ObservableList<WorldObject> coinToRemove = FXCollections.observableArrayList();
		for(WorldObject worldObject: worldObjects) {
				if(!worldObject.equals(playerController.getView()) && worldObject.getNode().getBoundsInParent().intersects(playerNode.getBoundsInParent())) {
					if(worldObject instanceof Chest) {
						((Chest)worldObject).playerInteracted(playerController,totalCoinsCollected);
					}
					else if(worldObject instanceof Coin) {
						((Coin)worldObject).playerInteracted(playerController, totalCoinsCollected);
						coinToRemove.add(worldObject);
					}
					else {
						worldObject.playerInteracted(playerController);
					}
				}
		}
		for(WorldObject coin: coinToRemove) {
			coin.despawn(worldObjects);
		}
	}

	public double findNearestPlatform(PlayerController playerController) {
		double location = 0, prevLocation = 0;
		for(WorldObject worldObject: worldObjects) {
			if(worldObject instanceof Platform) {
				prevLocation = location;
				location = worldObject.getNode().getLayoutX() - playerController.getView().getNode().getLayoutX();
				if(location >= 0.0 && location <= 50.0) {
					return worldObject.getNode().getLayoutX();
				}
			}
		}
		return location;
	}

	public void translateBy(double reviveLocation,PlayerController playerController) {
		
		for(WorldObject worldObject: worldObjects) {
			if(!(worldObject instanceof PlayerView)) {
				worldObject.getNode().setTranslateX(50);
			}
		}
	}
}