package application;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GreenOrcView extends OrcsView{
	private static final Image greenOrc = new Image("file:Assets/Sprites/GreenOrc.png");
	
	
	public GreenOrcView(Point2D boundingBoxCenter) {
		super(boundingBoxCenter, new ImageView(greenOrc));
		getNode().setScaleX(0.25);
		getNode().setScaleY(0.25);
	}
	
}
