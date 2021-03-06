package application;

import java.io.Serializable;

import application.controller.PlayerController;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WorldObject implements Spawnable, Collidable, Animated, Serializable {
	
	public transient Node node;
	public WorldObject(Point2D boundingBoxCenter, Node node) {
		this.node = node;
		node.setLayoutX(boundingBoxCenter.getX());
		node.setLayoutY(boundingBoxCenter.getY());
	}
	
	public boolean isCollidable() {
		return true;
	}
	
	public void setNode(Node node) {
		this.node = node;
	}
	
	public void relocate(Point2D newLoc) {
		node.setLayoutX(newLoc.getX());
		node.setLayoutY(newLoc.getY());
	}
	
	public Node getNode() {
		return this.node;
	}
	
	@Override
	public boolean isColliding(Collidable collidable) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void spawn(ObservableList<WorldObject> spawnables, double viewingOrder) {
		spawnables.add(this);
		getNode().setViewOrder(viewingOrder);
	}

	@Override
	public void despawn(ObservableList<WorldObject> spawnables) {
		AnchorPane root = (AnchorPane) node.getParent();
		spawnables.remove(this);
		if(root != null)root.getChildren().remove(node);
	}

	@Override
	public void playAnimation() {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void stopAnimation() {
		// TODO Auto-generated method stub
		return;
	}

	public void playerInteracted(PlayerController playerController) {

		double playerVelocityX = playerController.getModel().getVelocity().getX();
		if(playerVelocityX == 0)playerController.getModel().jumpUp();
		else playerController.getModel().setVelocity(new Point2D(playerVelocityX, 0.0));
	}

}
