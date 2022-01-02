package application;


import javafx.collections.ObservableList;
import javafx.geometry.Point2D;

public interface Spawnable {
	public void despawn(ObservableList<WorldObject> spawnables);
	void spawn(ObservableList<WorldObject> spawnables, double viewingOrder);
}
