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
//			if(i != 3 && i != 2) {
				WorldObject worldObject = new WorldObject(new Point2D(i*400+1, 450), new Rectangle(250,10,Paint.valueOf(Color.RED.toString())));
				worldObject.spawn(worldObjects);
//			}
		}
		Point2D temp = new Point2D(880,325);
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
		Chest tempChest = new WeaponsChest(new Point2D(380,325));
		tempChest.spawn(worldObjects);
		
		Chest tempChest2 = new WeaponsChest(new Point2D(580,325));
		tempChest2.spawn(worldObjects);
		
		Chest tempChest3 = new WeaponsChest(new Point2D(780,325));
		tempChest3.spawn(worldObjects);		
		Chest tempChest4 = new WeaponsChest(new Point2D(980,325));
		tempChest4.spawn(worldObjects);
		Chest tempChest5 = new WeaponsChest(new Point2D(1180,325));
		tempChest5.spawn(worldObjects);
		Chest tempChest6 = new WeaponsChest(new Point2D(1380,325));
		tempChest6.spawn(worldObjects);
		Chest tempChest7 = new WeaponsChest(new Point2D(1580,325));
		tempChest7.spawn(worldObjects);
		Chest tempChest8 = new WeaponsChest(new Point2D(1780,325));
		tempChest8.spawn(worldObjects);
		
		Coin tempCoin = new Coin(new Point2D(360,305));
		tempCoin.spawn(worldObjects);
	}
	
	
}
