package application;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RedOrcView extends OrcsView{
	private static final Image redOrc = new Image("file:Assets/Sprites/RedOrc.png");
	
	
	public RedOrcView(Point2D boundingBoxCenter) {
		super(boundingBoxCenter, new ImageView(redOrc));
		getNode().setScaleX(0.3);
		getNode().setScaleY(0.3);
	}
	
}
