package application;

import java.util.ArrayList;

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
	private static ArrayList<FallingPlatform> fallingPlatforms = new ArrayList<FallingPlatform>();
	private static int count;
	
	private FallingPlatform(Point2D pos) {
		super(pos,new ImageView(fallingPlatform));
		Node node = getNode();
		node.setScaleX(0.2);
		node.setScaleY(0.2);
		called = false;
		count = 0;
	}
	
	public static ArrayList<ImageView> initStaticPlatform(double X, double Y) {
		ArrayList<ImageView> ret = new ArrayList<ImageView>();
		for(int i = 0; i< 5*SIZE; i++) {
			FallingPlatform temp = new FallingPlatform(new Point2D(X + 40*i, Y));
			ret.add((ImageView) temp.getNode());
		}
		return ret;
	}
	
	public static FallingPlatform[] initPlatformRange(double X, double Y) {
		FallingPlatform[] ret = new FallingPlatform[SIZE];
		for(int i = 0; i < SIZE; i++) {
			ret[i] = new FallingPlatform(new Point2D(X + 40*i, Y));
			fallingPlatforms.add(ret[i]);
		}
		return ret;
	}
	
	@Override
	public void playerInteracted(PlayerController playerController) {
		double playerVelocityX = playerController.getModel().getVelocity().getX();
		if(playerVelocityX == 0)playerController.getModel().jumpUp();
		else playerController.getModel().setVelocity(new Point2D(playerVelocityX, 0.0));
		
		if(FallingPlatform.called) return;
 		ParallelTransition parallelTransition = new ParallelTransition();
		for(int i = count*SIZE; i < (count+1)*SIZE; i++) {
			TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1500), fallingPlatforms.get(i).getNode());
			translateTransition.setByY(500);
			translateTransition.setDelay(Duration.millis(200+100*i));
			parallelTransition.getChildren().add(translateTransition);
		}
		parallelTransition.play();
		count++;
		FallingPlatform.called = true;
		parallelTransition.setOnFinished((e)->{
			FallingPlatform.called = false;
		});
	}
	
}
