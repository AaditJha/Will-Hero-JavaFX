package application;

import application.controller.PlayerController;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Chest extends WorldObject {
	protected final static Image chestOpen = new Image("file:Assets/Sprites/WeaponCrateOpen.png");
	protected final static Image chestClose = new Image("file:Assets/Sprites/WeaponCrate.png");
	private SequentialTransition sequentialTransition;
	
	public Chest(Point2D pos) {
		super(pos,new ImageView(chestClose));
		super.getNode().setScaleX(0.20);
		super.getNode().setScaleY(0.20);
		sequentialTransition = new SequentialTransition(super.getNode());
		sequentialTransition.setCycleCount(Animation.INDEFINITE);
		sequentialTransition.getChildren().addAll(getTranslateTransition(-3,400),getTranslateTransition(-1,200));
		sequentialTransition.play();
	}
	
	private TranslateTransition getTranslateTransition(double byY, double duration) {
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(duration),super.getNode());
		translateTransition.setAutoReverse(true);
		translateTransition.setCycleCount(2);
		translateTransition.setByY(byY);
		translateTransition.setInterpolator(Interpolator.EASE_OUT);
		return translateTransition; 
	}
	
	public void stopAnim() {
		sequentialTransition.stop();
	}

	@Override
	public boolean isCollidable() {
		return false;
	}

	public void playerInteracted(PlayerController playerController, IntegerProperty totalCoinsCollected) {
		// TODO Auto-generated method stub
		return;
	}
	

	
}
