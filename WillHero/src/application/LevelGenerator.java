package application;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class LevelGenerator {

	public static void generate(ObservableList<WorldObject> worldObjects, ObservableList<OrcsController> orcsControllers) {
		for(int i = 0; i < 100; i++) {
			if(i != 3 && i != 2) {
				WorldObject worldObject = new WorldObject(new Point2D(i*400+1, 450), new Rectangle(250,10,Paint.valueOf(Color.RED.toString())));
				worldObject.spawn(worldObjects);
			}
		}
		Point2D temp = new Point2D(480,325);
		RedOrc greenOrc = new RedOrc(temp);
		RedOrcView greenOrcView = new RedOrcView(temp);
		greenOrcView.spawn(worldObjects);
		
		OrcsController orcsController = new OrcsController(greenOrc, greenOrcView);
		
		orcsControllers.add(orcsController);
		
//		FallingPlatform[] fallingPlatforms = FallingPlatform.initPlatformRange();
//		
//		for(int i = 0; i < FallingPlatform.SIZE; i++) {
//			fallingPlatforms[i].spawn(worldObjects);
//		}
//
//		Chest tempChest = new Chest(new Point2D(480,325));
//		tempChest.spawn(worldObjects);
		
		
	}
	
	
}
