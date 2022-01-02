package application;

import java.util.Random;

import application.controller.PlayerController;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Platform extends WorldObject {

	public Platform(Point2D boundingBoxCenter, int type) {
		super(boundingBoxCenter, new ImageView());
		Image sprite = new Image("file:Assets/Background/Islands/island"+type+".png");
		((ImageView)getNode()).setImage(sprite);
		getNode().setScaleX(0.2);
		getNode().setScaleY(0.2);
	}
	
	@Override
	public void playerInteracted(PlayerController playerController) {
		if(playerController.getView().getNode().getLayoutY()<350) {
			double playerVelocityX = playerController.getModel().getVelocity().getX();
			if(playerVelocityX == 0)playerController.getModel().jumpUp();
			else playerController.getModel().setVelocity(new Point2D(playerVelocityX, 0.0));
		}
	}
	
}
