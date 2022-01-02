package application;

import java.io.Serializable;

import application.controller.GameController;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Lance extends Weapon  {
	private transient static final Image lance = new Image("file:Assets/Sprites/Lance.png");
	private transient Pane root;
	private transient ParallelTransition anim;
	
	public Lance() {
		super(new ImageView(lance));
		getNode().setRotate(-5.0);
		getNode().setScaleX(0.3);
		getNode().setScaleY(0.3);
		
		anim = new ParallelTransition();
		TranslateTransition transition = new TranslateTransition(Duration.millis(100),getNode());
		transition.setByX(20);
		transition.setAutoReverse(true);
		transition.setCycleCount(2);
		ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), getNode());
		scaleTransition.setByX(0.1);
		scaleTransition.setByY(0.1);
		scaleTransition.setAutoReverse(true);
		scaleTransition.setCycleCount(2);
		anim.getChildren().addAll(transition,scaleTransition);
	}

	public void setRoot(Pane root) {
		this.root = root;
		root.getChildren().add(getNode());;
		getNode().relocate(GameController.getPlayerPos().getX()-80, GameController.getPlayerPos().getY()+50);
		getLabel().relocate(85, 545);
		root.getChildren().add(getLabel());
	}

	@Override
	public void useWeapon(Point2D playerPos) {
		anim.play();
	}
	
	public boolean damageOrc(OrcsController orcsController) {
		if(orcsController.getModel().damage(getDamage())) {
			return true;
		}
		return false;
	}

	public void updatePos(Point2D position) {
		getNode().relocate(getNode().getLayoutX(), position.getY()+50);
		
	}
	
}
