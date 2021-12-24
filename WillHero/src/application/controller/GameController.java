package application.controller;

import application.Game;
import application.GameLoop;
import application.Main;
import application.Player;
import application.view.GameView;
import application.view.PlayerView;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class GameController {
	private final Point2D PLAYER_POS = new Point2D(50, Main.DIMENSIONS.getHeight()/2);
	private final String playerSpriteURL = "file:Assets/Sprites/Player.png";
	
	private GameView gameView;
	private Game game;

	private Player player;
	private PlayerView playerView;
	private PlayerController playerController;
	
	private boolean spacePressed;
	
	public GameController() {
		player = new Player(1.0,PLAYER_POS);
		playerView = new PlayerView(PLAYER_POS, playerSpriteURL);
		playerController = new PlayerController(player, playerView);
		gameView = new GameView();
		game = new Game(playerController);
		spacePressed = false;
	}
	
	public void setStage(Stage primaryStage) {
		gameView.setStageScene(primaryStage, playerController);
	}
	
	public void startNewGame() {
		
		playerView.getNode().getScene().setOnKeyPressed((e)->{
			if(e.getCode().equals(KeyCode.SPACE) && !spacePressed) {
				spacePressed = true;
			}
		});
		
		playerView.getNode().getScene().setOnKeyReleased((e)->{
			if(e.getCode().equals(KeyCode.SPACE) && spacePressed) {
				spacePressed = false;
			}
		});
		
		System.out.println("NEW GAME");
		GameLoop loop = new GameLoop() {
			
			@Override
			public void tick(float frameDuration) {
				game.update(frameDuration,spacePressed);
				gameView.update(playerController,PLAYER_POS);
			}
		};
		GameLoop backgroundLoop = new GameLoop() {
			
			@Override
			public void tick(float frameDuration) {
				gameView.checkCollision(playerController);
			}
		};
		loop.start();
		backgroundLoop.start();
	}
	
}
