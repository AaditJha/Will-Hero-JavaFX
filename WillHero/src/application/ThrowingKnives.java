package application;

import application.controller.GameController;
import application.view.GameView;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class ThrowingKnives extends Weapon {
	private static final double range = 320.0;
	private static final Image knives = new Image("file:Assets/Sprites/ThrowingKnife1.png");
	private ObservableList<ImageView> thrownKnives;
	private Pane root;
	
	public ThrowingKnives() {
		super(100,new ImageView(knives));
		thrownKnives = FXCollections.observableArrayList();
	}
	
	public void setRoot(Pane root) {
		this.root = root;
	}
	
	@Override
	public void useWeapon(Point2D playerPos) {
		ImageView newKnife = new ImageView(knives);
		thrownKnives.add(newKnife);
		newKnife.setScaleX(0.2);
		newKnife.setScaleY(0.2);
		newKnife.relocate(GameController.getPlayerPos().getX(), playerPos.getY()+50);
		TranslateTransition transition = new TranslateTransition(Duration.millis(1000), newKnife);
		root.getChildren().add(0,newKnife);
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

	public void damageOrc(OrcsController orcsController, int idx) {
		System.out.println("MARA");
	}
}
