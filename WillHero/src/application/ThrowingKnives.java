package application;

import java.io.File;

import application.controller.GameController;
import application.view.GameView;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class ThrowingKnives extends Weapon {
	private transient static final double range = 250.0;
	private transient static final Image knives = new Image("file:Assets/Sprites/ThrowingKnife1.png");
	private transient ObservableList<ImageView> thrownKnives;
	private transient Pane root;
	private transient Media media = new Media(new File("src/application/sounds/knivesout.wav").toURI().toString());
	private transient MediaPlayer mediaplayer = new MediaPlayer(media);
	
	public ThrowingKnives() {
		super(new ImageView(knives));
		thrownKnives = FXCollections.observableArrayList();
	}
	
	public void setRoot(Pane root) {
		this.root = root;		
		getLabel().relocate(230, 545);
		root.getChildren().add(getLabel());
	}
	
	@Override
	public void useWeapon(Point2D playerPos) {
		if(!GameController.muted) {
			mediaplayer.setVolume(0.5);
			mediaplayer.play();
		}
		ImageView newKnife = new ImageView(knives);
		thrownKnives.add(newKnife);
		newKnife.setScaleX(0.2);
		newKnife.setScaleY(0.2);
		newKnife.relocate(GameController.getPlayerPos().getX(), playerPos.getY()+50);
		TranslateTransition transition = new TranslateTransition(Duration.millis(1000), newKnife);
		root.getChildren().add(newKnife);
		newKnife.setViewOrder(1.0);
		transition.setByX(range);
		transition.play();
		transition.setOnFinished((e)->{
			thrownKnives.remove(newKnife);
			root.getChildren().remove(newKnife);
		});
	}

	public ObservableList<ImageView> getList() {
		return thrownKnives;
	}

	public boolean damageOrc(OrcsController orcsController, int idx) {
		if(orcsController.getModel().damage(getDamage())) {
			return true;
		}
		root.getChildren().remove(thrownKnives.get(idx));
		thrownKnives.remove(idx);
		return false;
	}
}
