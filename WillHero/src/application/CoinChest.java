package application;

import java.io.File;
import java.util.Random;

import application.controller.GameController;
import application.controller.PlayerController;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class CoinChest extends Chest {

	private boolean interacted;
	public CoinChest(Point2D pos) {
		super(pos);
		interacted = false;
	}
	private Media chestOpenAudio = new Media(new File("src/application/sounds/chestopen.wav").toURI().toString());
	
	@Override
	public void playerInteracted(PlayerController playerController, IntegerProperty totalCoinsCollected) {
		if(interacted) return;
		interacted = true;
		if(!GameController.muted) {
			MediaPlayer mediaPlayer = new MediaPlayer(chestOpenAudio);
			mediaPlayer.setVolume(0.5);
			mediaPlayer.play();
		}
		if(totalCoinsCollected.get() < 9999)totalCoinsCollected.set(totalCoinsCollected.get()+100);
		ImageView temp = (ImageView) super.getNode();
		temp.setImage(chestOpen);
		stopAnim();
	}
}
