package application;

import application.view.GameView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Weapon {
	private static final int MAX_LEVEL = 3;
	private int damage;
	private IntegerProperty upgradeLevel;
	private Label levelView;
	private Node node;
	
	public Weapon(Node node) {
		this.damage = 0;
		this.levelView = new Label();
		this.upgradeLevel = new SimpleIntegerProperty(0);
		this.node = node;
		this.levelView.setScaleX(1.5);
		this.levelView.setScaleY(1.5);
		this.levelView.setStyle("-fx-font-weight: bold; -fx-text-fill: #f8f357");
		this.levelView.textProperty().bind(upgradeLevel.asString());
		this.levelView.setVisible(false);
	}
	
	public Node getNode() {
		return this.node;
	}
	
	public Label getLabel() {
		return levelView;
	}

	public int getDamage() {
		return damage;
	}
	
	public void useWeapon(Point2D playerPos) {
			return;
	}

	public void upgrade() {
		if(upgradeLevel.get() < MAX_LEVEL) {
			upgradeLevel.set(upgradeLevel.get()+1);
			damage += 100;
		}
	}
}
