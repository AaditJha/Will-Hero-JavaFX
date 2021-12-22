package application.view;

import java.util.Vector;

import application.CustomEvent;
import application.PlayerOutEvent;
import application.PlayerOutEventHandler;
import application.WorldObject;
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
	private final double ORIGINAL_SCALE = 0.35;

	public PlayerView(Point2D pos, String url) {
		super(pos, new ImageView(new Image(url)));
		getNode().setScaleX(ORIGINAL_SCALE);
		getNode().setScaleY(ORIGINAL_SCALE);
	}

	@Override
	public ImageView getNode() { return (ImageView) super.getNode(); }
	public double getPosX() { return getNode().getLayoutX(); }
	public double getPosY() { return getNode().getLayoutY(); }
	public void setPos(Point2D pos) { 
		getNode().relocate(pos.getX(), pos.getY());
	}
	
	public void setGraphic(String url) {
		getNode().setImage(new Image(url));
	}
	
	
	@Override
	public void stopAnimation() {
		//TODO
	}
}
