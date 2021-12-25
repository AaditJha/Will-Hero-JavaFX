package application;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BossOrcView extends OrcsView{
	private static final Image greenOrc = new Image("file:Assets/Sprites/BossOrc.png");
	
	
	public BossOrcView(Point2D boundingBoxCenter) {
		super(boundingBoxCenter, new ImageView(greenOrc));
		getNode().setScaleX(0.5);
		getNode().setScaleY(0.5);
	}
	
}
