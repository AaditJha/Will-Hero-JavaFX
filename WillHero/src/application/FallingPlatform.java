package application;

import application.controller.PlayerController;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class FallingPlatform extends WorldObject {
	private static boolean called;
	private final static Image fallingPlatform = new Image("file:Assets/Background/tile.png");
	public static final int SIZE = 10;
	private static FallingPlatform fallingPlatforms[] = new FallingPlatform[10];
	
	private FallingPlatform(Point2D pos) {
		super(pos,new ImageView(fallingPlatform));
		Node node = getNode();
		node.setScaleX(0.2);
		node.setScaleY(0.2);
		called = false;
	}
	
	public static FallingPlatform[] initPlatformRange() {
		for(int i = 0; i < SIZE; i++) {
			fallingPlatforms[i] = new FallingPlatform(new Point2D(680 + 40*i, 425));
		}
		return fallingPlatforms;
	}
	
	@Override
	public void playerInteracted(PlayerController playerController) {
		double playerVelocityX = playerController.getModel().getVelocity().getX();
		if(playerVelocityX == 0)playerController.getModel().jumpUp();
		else playerController.getModel().setVelocity(new Point2D(playerVelocityX, 0.0));
		
		if(FallingPlatform.called) return;
 		ParallelTransition parallelTransition = new ParallelTransition();
		for(int i = 0; i < SIZE; i++) {
			TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1500), fallingPlatforms[i].getNode());
			translateTransition.setByY(500);
			translateTransition.setDelay(Duration.millis(400+200*i));
			parallelTransition.getChildren().add(translateTransition);
		}
		parallelTransition.play();
		FallingPlatform.called = true;
		parallelTransition.setOnFinished((e)->{
		FallingPlatform.called = false;
		});
	}
	
}
