package application;

import application.controller.PlayerController;
import application.view.PlayerView;

public class Game {
	private boolean innerSpaceControl, playerMoving;
	private PlayerController playerController;
	
	public Game(PlayerController playerController) {
		this.playerController = playerController;
		this.playerMoving = false;
		this.innerSpaceControl = false;
	}

	public void update(float frameDuration,boolean spacePressed) {
		playerController.getModel().jump(frameDuration);
		if(playerMoving) {
			playerMoving = playerController.getModel().addDrag();
			if(!playerMoving) playerController.getView().unsquish();
		}
		if(spacePressed && !innerSpaceControl) {
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
	
	
	
}
