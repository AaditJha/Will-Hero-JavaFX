package application;

import java.io.Serializable;

import application.controller.PlayerController;
import application.view.GameView;
import application.view.PlayerView;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;

public class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean innerSpaceControl, playerMoving, revived;
	private PlayerController playerController;
	private transient ObservableList<OrcsController> orcsControllers;
	
	public Game(PlayerController playerController, ObservableList<OrcsController> orcsControllers) {
		this.playerController = playerController;
		this.playerMoving = false;
		this.innerSpaceControl = false;
		this.revived = false;
		this.orcsControllers = orcsControllers;
	}

	public void update(float frameDuration,boolean spacePressed) {
		for(OrcsController orcsController: orcsControllers) {
			orcsController.getModel().jump(frameDuration);
			if(orcsController.getModel().getVelocity().getX() > 0.001) {
				orcsController.getModel().addDrag();
			}
		}
		playerController.getModel().jump(frameDuration);
		if(playerMoving) {
			if(!playerController.invincible)playerMoving = playerController.getModel().addDrag();
			if(!playerMoving) playerController.getView().unsquish();
		}
		if(spacePressed && (!innerSpaceControl ||playerController.invincible)) {
			playerController.getModel().move(frameDuration);
			playerController.getView().squish();
			playerMoving = true;
			innerSpaceControl = true;
		}
		if(!spacePressed && innerSpaceControl) {
			innerSpaceControl = false;
			playerController.getModel().jump(frameDuration);
		}
	}
	
	public boolean isRevived() {
		return revived;
	}

	public void setRevived() {
		revived = true;
	}
	
}
