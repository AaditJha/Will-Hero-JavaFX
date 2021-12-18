package application;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class WorldObject implements Spawnable, Collidable, Animated {
	
	public Node node;
	public WorldObject(WorldVec2 boundingBoxCenter, Node node) {
		this.node = node;
		node.setLayoutX(boundingBoxCenter.getX());
		node.setLayoutY(boundingBoxCenter.getY());
	}
	
	public void relocate(WorldVec2 newLoc) {
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

}
