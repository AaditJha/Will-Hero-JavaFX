package application;

import java.util.Random;

import application.controller.GameController;
import application.controller.PlayerController;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class WeaponsChest extends Chest {
	private boolean interacted;
	private boolean chestRandomizer;
	private Media chestOpenMedia = new Media("src/application/sounds/chestopen.wav");
	public WeaponsChest(Point2D pos, int chestRandomizer) {
		super(pos);
		interacted = false;
		this.chestRandomizer = (chestRandomizer == 0)?false:true;
	}

	@Override
	public void playerInteracted(PlayerController playerController, IntegerProperty totalCoinsCollected) {
		if(interacted) return;
		interacted = true;
		if(!GameController.muted) {
			MediaPlayer mediaPlayer = new MediaPlayer(chestOpenMedia);
			mediaPlayer.play();
		}
		if(totalCoinsCollected.get() < 9999)totalCoinsCollected.set(totalCoinsCollected.get()+5);
		ImageView temp = (ImageView) super.getNode();
		temp.setImage(chestOpen);
		playerController.getModel().getHelmet().equipWeapon(chestRandomizer);
		stopAnim();
	}

}
