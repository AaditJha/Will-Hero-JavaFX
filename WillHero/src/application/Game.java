package application;

import application.controller.PlayerController;
import application.view.PlayerView;

public class Game {
	private boolean innerSpaceControl, playerMoving;
	private Player player;
	
	public Game(Player player) {
		this.player = player;
		this.playerMoving = false;
		this.innerSpaceControl = false;
	}

	public void update(float frameDuration,boolean spacePressed) {
		if(!playerMoving)player.jump(frameDuration);
		else {
			playerMoving = player.addDrag();
		}
		if(spacePressed && !innerSpaceControl) {
			player.move(frameDuration);
			playerMoving = true;
			innerSpaceControl = true;
		}
		if(!spacePressed && innerSpaceControl) innerSpaceControl = false;
	}
	
	
	
}
