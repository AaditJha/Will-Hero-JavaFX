package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class LevelGenerator {
	private LevelGenerator() {
		return;
	}
	
	private static LevelGenerator instance = null;
	
	public static LevelGenerator getInstance() {
		if(instance == null) {
			instance = new LevelGenerator();
		}
		return instance;
	}

	public void generate(ObservableList<WorldObject> worldObjects, ObservableList<OrcsController> orcsControllers, AnchorPane rootPane) {		
		for(int i = 0; i < 28; i++) {
			if(i == 15 || i == 20 || i == 24) {
				FallingPlatform[] fallingPlatforms = FallingPlatform.initPlatformRange(i*800+320, 335);
				for(FallingPlatform fallingPlatform:fallingPlatforms) {
					fallingPlatform.spawn(worldObjects,1);
				}
			}
			else {
				Platform platform = new Platform(new Point2D(i*800, 180),1);
				platform.spawn(worldObjects,1);
			}
			if(i == 0) {
				Platform platform4 = new Platform(new Point2D(i*800-400, 120), 4);
				platform4.spawn(worldObjects,1);
			}
			else {
				Platform platform4 = new Platform(new Point2D(i*800-400, 70), 4);
				generateOrc(i*800-400, worldObjects, orcsControllers);
				platform4.spawn(worldObjects,1);
			}

		}	
		int[] pos = {3,22,9,16,11,17,8,20,15,14};
		for(int i = 0; i < 10; i++) {
			Random rd = new Random();
			int chestRandomCoin = rd.nextInt(3);
			if(chestRandomCoin == 2) {
				Chest coinChest = new CoinChest(new Point2D(pos[i]*800-400, 280));
				coinChest.spawn(worldObjects, 1);
			}
			else {
				Chest weaponsChest = new WeaponsChest(new Point2D(pos[i]*800-400, 280),chestRandomCoin);
				weaponsChest.spawn(worldObjects, 1);
			}
		}
		ArrayList<ImageView> staticPlatforms = FallingPlatform.initStaticPlatform(22120, 345);
		for(ImageView staticPlatform:staticPlatforms) {
			WorldObject worldObject = new WorldObject(new Point2D(staticPlatform.getLayoutX(),staticPlatform.getLayoutY()), staticPlatform);
			worldObject.spawn(worldObjects,1);
		}
		Platform platform4 = new Platform(new Point2D(30*800-200, 90), 3);
		platform4.spawn(worldObjects,1);
		
		Random coinLengthRanomizer = new Random();
		for(int i = 0; i < 5; i++) {
			int posY = 200;
			int coinLength = coinLengthRanomizer.nextInt(5)+1;
			boolean doubled = coinLengthRanomizer.nextBoolean();
			for(int j = 0; j < coinLength; j++) {
				Coin tempCoin = new Coin(new Point2D((double)(5*i)*800+240+40*j, posY));
				tempCoin.spawn(worldObjects, 1);
				if(doubled) {
					Coin tempCoin2 = new Coin(new Point2D((double)(5*i)*800+240+40*j, posY-40));
					tempCoin2.spawn(worldObjects, 1);
				}
			}
		}
	}
	
	public void generateOrc(double pos, ObservableList<WorldObject> worldObjects, ObservableList<OrcsController> orcsControllers) {
		Random orcRandomizer = new Random();
		boolean greenOrcRandom = orcRandomizer.nextBoolean();
		if(greenOrcRandom) {
			int orcAmounts = orcRandomizer.nextInt(3)+1;
			for(int j = 0; j < orcAmounts; j++) {
				Point2D temp = new Point2D((double)pos+40*j, 250);
				GreenOrc greenOrc = new GreenOrc(temp);
				GreenOrcView greenOrcView = new GreenOrcView(temp);
				greenOrcView.spawn(worldObjects, 1);
				orcsControllers.add(new OrcsController(greenOrc, greenOrcView));
			}
		}
		else {
			Point2D temp = new Point2D((double)pos, 250);
			RedOrc redOrc = new RedOrc(temp);
			RedOrcView redOrcView = new RedOrcView(temp);
			redOrcView.spawn(worldObjects, 1);
			orcsControllers.add(new OrcsController(redOrc, redOrcView));
		}
	}
	
	
	public void generateBossOrc(ObservableList<WorldObject> worldObjects, ObservableList<OrcsController> orcsControllers, AnchorPane root) {
		Point2D temp = new Point2D(200,50);
		BossOrc bossOrc = new BossOrc(temp);
		BossOrcView bossOrcView = new BossOrcView(temp);
		orcsControllers.add(new OrcsController(bossOrc, bossOrcView));
		root.getChildren().add(bossOrcView.getNode());
		bossOrcView.getNode().setViewOrder(1);
		bossOrcView.spawn(worldObjects, 1);
	}
	
}
