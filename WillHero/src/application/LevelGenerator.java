package application;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class LevelGenerator {

	public static void generate(ObservableList<WorldObject> worldObjects) {
		for(int i = 0; i < 100; i++) {
			WorldObject worldObject = new WorldObject(new Point2D(i*400+1, 420), new Rectangle(250,10,Paint.valueOf(Color.RED.toString())));
			worldObject.spawn(worldObjects);
		}
		
		
		
//		Chest tempChest = new Chest(new Point2D(480,325));
//		tempChest.spawn(worldObjects);
		
		
		
	}
	
	
}
