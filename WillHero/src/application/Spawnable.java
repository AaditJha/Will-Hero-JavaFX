package application;


import javafx.collections.ObservableList;

public interface Spawnable {
	public void spawn(ObservableList<WorldObject> spawnables);
	public void despawn(ObservableList<WorldObject> spawnables);
}
