package application.controller;

import java.io.IOException;

import application.Game;
import application.GameLoop;
import application.Helmet;
import application.Main;
import application.OrcsController;
import application.Player;
import application.view.GameView;
import application.view.PlayerView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameController {
	private static final Point2D PLAYER_POS = new Point2D(50, Main.DIMENSIONS.getHeight()/2);
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
		playerView = new PlayerView(PLAYER_POS,player.getHelmet().getPlayerHelmetIMG());
		playerController = new PlayerController(player, playerView);
		gameView = new GameView();
		game = new Game(playerController,orcsControllers);
		spacePressed = false;
	}
	
	public static Point2D getPlayerPos() {
		return PLAYER_POS;
	}
	
	public void setStage(AnchorPane root) {
		try {
			gameView.setStageScene(root, playerController,orcsControllers);
			GameLoop loop = new GameLoop() {
				
				@Override
				public void tick(float frameDuration) {
					game.update(frameDuration,spacePressed);
					gameView.update(playerController,PLAYER_POS,orcsControllers);
					gameView.checkCollision(playerController,orcsControllers);
				}
			};
			
			loop.start();
			
			root.addEventFilter(KeyEvent.KEY_PRESSED, e->{
	            if (e.getCode().equals(KeyCode.SPACE)) {
	            	spacePressed = (true | spacePressed);
	            }
	        });
			
			root.addEventFilter(KeyEvent.KEY_RELEASED, e->{
	            if (e.getCode().equals(KeyCode.SPACE)) {
	            	spacePressed = false;
	            }
	        });
			
			root.addEventFilter(MouseEvent.MOUSE_CLICKED, e->{
				if(loop.isActive())loop.pause();
				if(loop.isPaused())loop.play();
			});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
