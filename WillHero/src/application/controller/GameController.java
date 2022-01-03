package application.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Observable;

import application.Game;
import application.GameLoop;
import application.GameOverEvent;
import application.GamePausedEvent;
import application.Helmet;
import application.Main;
import application.OrcsController;
import application.Player;
import application.view.GameView;
import application.view.PlayerView;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameController implements Serializable {
	public static boolean muted;
	private static final long serialVersionUID = 1L;
	public transient static final Point2D PLAYER_POS = new Point2D(50, Main.DIMENSIONS.getHeight()/2);
	private GameView gameView;
	private Game game;
	private transient Label playerScore;
	private transient IntegerProperty totalCoinsCollected, playerPosScore;
	private int serializableTotalCoins, serializablePosScore;
	private Player player;
	private PlayerView playerView;
	private PlayerController playerController;
	private transient AnchorPane root;
	private transient ObservableList<OrcsController> orcsControllers;
	
	private boolean spacePressed;
	
	public GameController() {
		totalCoinsCollected = new SimpleIntegerProperty(0);
		orcsControllers = FXCollections.observableArrayList();
		player = new Player(PLAYER_POS);
		playerView = new PlayerView(PLAYER_POS,player.getHelmet().getPlayerHelmetIMG());
		playerController = new PlayerController(player, playerView);
		gameView = new GameView();
		game = new Game(playerController,orcsControllers);
		spacePressed = false;
		playerScore = new Label();
		playerPosScore = new SimpleIntegerProperty(0);
		playerScore.textProperty().bind(playerPosScore.asString());
	}
	
	public static Point2D getPlayerPos() {
		return PLAYER_POS;
	}
	
	public IntegerProperty getTotalCoinsCollected() {
		return this.totalCoinsCollected;
	}
	
	public int getCoins() {
		return this.serializableTotalCoins;
	}
	
//	public boolean isWeaponASelected() {
//		return player.getHelmet().isSelectedWeaponA();
//	}
	
	public Game getModel() {
		return game;
	}
	
	public void setStage(AnchorPane root) {
		this.root = root;
		try {
			serializablePosScore = playerPosScore.get();
			serializableTotalCoins = totalCoinsCollected.get();
			gameView.setStageScene(root, playerController,orcsControllers);
			playerScore.relocate(Main.DIMENSIONS.getWidth()/2, 80);
			playerScore.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold");
			playerScore.setEffect(new DropShadow(0.5,1.0,1.0,Color.BLACK));
			playerScore.setScaleX(5);
			playerScore.setScaleY(5);
			playerScore.setVisible(false);
			root.getChildren().add(playerScore);
			GameLoop loop = new GameLoop() {
				
				@Override
				public void tick(float frameDuration) {
					if(playerController.invincible) {
						game.update(frameDuration,true);
						gameView.update(playerController,PLAYER_POS,orcsControllers,playerPosScore);
						gameView.checkCollision(playerController,orcsControllers,totalCoinsCollected,true);
						serializablePosScore = playerPosScore.get();
						serializableTotalCoins = totalCoinsCollected.get();
					}
					else {
						game.update(frameDuration,spacePressed);
						gameView.update(playerController,PLAYER_POS,orcsControllers,playerPosScore);
						gameView.checkCollision(playerController,orcsControllers,totalCoinsCollected,spacePressed);
						serializablePosScore = playerPosScore.get();
						serializableTotalCoins = totalCoinsCollected.get();
					}
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
			
			root.addEventFilter(GamePausedEvent.EVENT_TYPE, e->{
				if(loop.isActive())loop.pause();
				if(loop.isPaused()) {
					loop.play();
				}
			});
			
			root.addEventFilter(GameOverEvent.EVENT_TYPE, e->{
				if(loop.isActive())loop.pause();
			});
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void swapWeapon() {
		player.getHelmet().swapWeapon();
	}

	public boolean bothWeaponUnlocked(boolean activeWeaponA) {
		return player.getHelmet().bothWeaponUnlocked(activeWeaponA);
	}

	public void endGame() {
		player.getHelmet().getThrowingKnife().getLabel().setVisible(false);
		player.getHelmet().getLance().getLabel().setVisible(false);
	}
	
	public void startGame() {
		playerScore.setVisible(true);
		player.getHelmet().getThrowingKnife().getLabel().setVisible(true);
		player.getHelmet().getLance().getLabel().setVisible(true);
	}

	public int getScore() {
		// TODO Auto-generated method stub
		return playerPosScore.get();
	}

	public int getSerializableScore() {
		return serializablePosScore;
	}
	
	public void revivePlayer() {
		getModel().setRevived();
		
		double reviveLocation = gameView.findNearestPlatform(playerController);
		gameView.translateBy(reviveLocation,playerController);
		playerView.setPos(PLAYER_POS);
		player.setPos(PLAYER_POS);
		root.fireEvent(new GamePausedEvent());
		player.getHelmet().unhideWeapon();
	}

	public void setInvincible(boolean status) {
		playerController.invincible = status;
	}

	
}

