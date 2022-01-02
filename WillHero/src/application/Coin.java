package application;

import application.controller.PlayerController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Coin extends WorldObject {

	private static final Image coin = new Image("file:Assets/Sprites/Coin.png");
	private static final int PRICE = 1;
	private boolean interacted;
	private TranslateTransition anim;
	
	public Coin(Point2D boundingBoxCenter) {
		super(boundingBoxCenter, new ImageView(coin));
		getNode().setScaleX(0.10);
		getNode().setScaleY(0.10);
		interacted = false;
		anim = new TranslateTransition(Duration.millis(500),getNode());
		anim.setCycleCount(Animation.INDEFINITE);
		anim.setByY(5);
		anim.setAutoReverse(true);
		anim.play();
	}

	public void playerInteracted(PlayerController playerController, IntegerProperty totalCoinsCollected, ObservableList<WorldObject> worldObjects) {
		if(interacted) return;
		anim.stop();
		interacted = true;
		if(totalCoinsCollected.get() < 9999)totalCoinsCollected.set(totalCoinsCollected.get()+PRICE);
		despawn(worldObjects);
	}
	
}
