package application.controller;

import java.io.IOException;

import application.Game;
import application.GameLoop;
import application.Main;
import application.OrcsController;
import application.Player;
import application.view.GameView;
import application.view.PlayerView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private ObservableList<OrcsController> orcsControllers;
	
	private boolean spacePressed;
	
	public GameController() {
		orcsControllers = FXCollections.observableArrayList();
		player = new Player(PLAYER_POS);
		playerView = new PlayerView(PLAYER_POS, playerSpriteURL);
		playerController = new PlayerController(player, playerView);
		gameView = new GameView();
		game = new Game(playerController,orcsControllers);
		spacePressed = false;
	}
	
	public void setStage(Stage primaryStage) {
		try {
			gameView.setStageScene(primaryStage, playerController,orcsControllers);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				gameView.update(playerController,PLAYER_POS,orcsControllers);
			}
		};
		GameLoop backgroundLoop = new GameLoop() {
			
			@Override
			public void tick(float frameDuration) {
				gameView.checkCollision(playerController,orcsControllers);
			}
		};
		loop.start();
		backgroundLoop.start();
	}
	
}
