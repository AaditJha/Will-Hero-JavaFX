package application.controller;

import java.io.File;
import java.io.Serializable;

import application.Lance;
import application.Player;
import application.view.PlayerView;
import javafx.geometry.Point2D;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayerController implements Serializable {
	private Player player;
	private PlayerView view;
	private transient static Media deathSound = new Media(new File("src/application/sounds/playerdied.wav").toURI().toString());
	public transient boolean invincible;
	
	public PlayerController(Player player, PlayerView view) {
		this.player = player;
		this.view = view;
		invincible = false;
	}
	
	public Player getModel() { return this.player; }
	public PlayerView getView() { return this.view; } 
	
	public void move() {
		System.out.println("MOVING");
	}

	public void jump() {
		System.out.println("JUMPING");	
	}

	public void killed(boolean deathFromFall) {
		if(!GameController.muted) {
			MediaPlayer mediaPlayer = new MediaPlayer(deathSound);
			mediaPlayer.setVolume(0.5);
			mediaPlayer.play();	
		}
		if(getModel().getHelmet().getEquippedWeapon()!=null && getModel().getHelmet().getEquippedWeapon() instanceof Lance) {
			getModel().getHelmet().hideWeaponWhileDying();
		}
		if(deathFromFall)getView().dieFromFalling();
		else getView().dieFromOrc();
	}

	public void setView(PlayerView playerView) {
		this.view = playerView;
	}

	public void setModel(Player player) {
		this.player = player;
	}
	
}
