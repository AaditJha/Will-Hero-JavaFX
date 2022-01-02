package application;

import java.util.Random;

import application.controller.PlayerController;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class WeaponsChest extends Chest {
	private boolean interacted;
	public WeaponsChest(Point2D pos) {
		super(pos);
		interacted = false;
	}

	@Override
	public void playerInteracted(PlayerController playerController, IntegerProperty totalCoinsCollected) {
		if(interacted) return;
		interacted = true;
		if(totalCoinsCollected.get() < 9999)totalCoinsCollected.set(totalCoinsCollected.get()+5);
		ImageView temp = (ImageView) super.getNode();
		temp.setImage(chestOpen);
		Random rd = new Random();
		playerController.getModel().getHelmet().equipWeapon(rd.nextBoolean());
		stopAnim();
	}

}
