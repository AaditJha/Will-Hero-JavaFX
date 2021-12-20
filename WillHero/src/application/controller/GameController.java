package application.controller;

import application.Game;
import application.Player;
import application.view.GameView;
import application.view.PlayerView;
import javafx.animation.Timeline;

public class GameController {
	private GameView view;
	private Game game;

	private Player player;
	private PlayerView playerView;
	private PlayerController playerController;
	
	public GameController(GameView view, Game model, PlayerController playerController) {
		this.view = view;
		this.game = model;
		this.player = playerController.getModel();
		this.playerView = playerController.getView();
		this.playerController = playerController;
	}
	
	public void startNewGame() {
		System.out.println("NEW GAME");
	}
	
}
