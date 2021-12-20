package application.controller;

import application.Player;
import application.view.PlayerView;

public class PlayerController {
	private Player player;
	private PlayerView view;
	
	public PlayerController(Player player, PlayerView view) {
		this.player = player;
		this.view = view;
	}
	
	public Player getModel() { return this.player; }
	public PlayerView getView() { return this.view; } 
	
	public void move() {
		System.out.println("MOVING");
	}

	public void jump() {
		System.out.println("JUMPING");	
	}
	
	
}
