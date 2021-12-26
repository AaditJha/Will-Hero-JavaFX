package application;

import application.view.GameView;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public class Weapon {
	private static final int MAX_LEVEL = 3;
	private int damage;
	private int upgradeLevel;
	private Node node;
	
	public Weapon(int damage, Node node) {
		this.damage = damage;
		this.upgradeLevel = 0;
		this.node = node;
	}
	
	public Node getNode() {
		return this.node;
	}

	public void useWeapon(Point2D playerPos) {
			return;
	}
	
}
