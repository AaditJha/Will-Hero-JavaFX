package application.view;

import java.util.Vector;

import application.CustomEvent;
import application.PlayerOutEvent;
import application.PlayerOutEventHandler;
import application.WorldObject;
import application.WorldVec2;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class PlayerView extends WorldObject{
	private final TranslateTransition jumpAnimation;
	private final ParallelTransition moveForward;
	private final double ORIGINAL_SCALE = 0.35;
	private final WorldVec2 PLAYER_POS;

	public PlayerView(WorldVec2 pos, String url) {
		super(pos, new ImageView(new Image(url)));
		PLAYER_POS = pos;
		this.jumpAnimation = new TranslateTransition();
		this.moveForward = new ParallelTransition();
		getNode().setScaleX(ORIGINAL_SCALE);
		getNode().setScaleY(ORIGINAL_SCALE);
		setAnim();
	}

	@Override
	public ImageView getNode() { return (ImageView) super.getNode(); }
	public double getPosX() { return getNode().getLayoutX(); }
	public double getPosY() { return getNode().getLayoutY(); }
	public void setPos(WorldVec2 pos) { 
		getNode().relocate(pos.getX(), pos.getY());
	}
	
	public void setGraphic(String url) {
		getNode().setImage(new Image(url));
	}
	
	public void setAnim() {
		jumpAnimation.setDuration(Duration.millis(600));
		jumpAnimation.setNode(getNode());
		jumpAnimation.setAutoReverse(true);
		jumpAnimation.setCycleCount(Animation.INDEFINITE);
		jumpAnimation.setByY(-50);
		jumpAnimation.setInterpolator(Interpolator.EASE_OUT);
		jumpAnimation.play();
		
		ScaleTransition squish = new ScaleTransition(Duration.millis(100),getNode());
		squish.setToY(0.65*ORIGINAL_SCALE);
		ScaleTransition stretch = new ScaleTransition(Duration.millis(400),getNode());
		stretch.setToY(ORIGINAL_SCALE);
		stretch.setDelay(Duration.millis(100));
		TranslateTransition move = new TranslateTransition(Duration.millis(400),getNode());
		move.setByX(60);
		moveForward.getChildren().addAll(squish,move,stretch);
	}
	
	public void move() {
		jumpAnimation.pause();
		moveForward.stop();
		moveForward.play();
//		System.out.println("WERE AT: "+getPosX());
		moveForward.setOnFinished(e->{
			getNode().setLayoutX(getNode().getLayoutX()+getNode().getTranslateX());
			getNode().setTranslateX(0);
			System.out.println("NOW AT: "+getPosX());
			if(getPosX() > 220) {
				System.out.println("EVENT FIRED");
				getNode().fireEvent(new PlayerOutEvent(getPosX()-PLAYER_POS.getX()));
			}
			jumpAnimation.play();
		});
		
	}
	
//	public ActionEvent 
	
	@Override
	public void stopAnimation() {
		jumpAnimation.stop();
	}
}
