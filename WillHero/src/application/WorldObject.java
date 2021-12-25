package application;

import application.controller.PlayerController;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public class WorldObject implements Spawnable, Collidable, Animated {
	
	public Node node;
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
	public void spawn(ObservableList<WorldObject> spawnables) {
		spawnables.add(this);
	}

	@Override
	public void despawn(ObservableList<WorldObject> spawnables) {
		spawnables.remove(this);
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
