package application;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class GreenOrcView extends OrcsView{
	private static final Image greenOrc = new Image("file:Assets/Sprites/GreenOrc.png");
	private static final Image greenOrcDead = new Image("file:Assets/Sprites/GreenOrcDead.png");
	private static final int COINS_GIVEN = 5;
	
	public GreenOrcView(Point2D boundingBoxCenter) {
		super(boundingBoxCenter, new ImageView(greenOrc));
		getNode().setScaleX(0.25);
		getNode().setScaleY(0.25);
	}
	
	
	@Override
	public void deathAnim(IntegerProperty totalCoinsCollected) {
		((ImageView)getNode()).setImage(greenOrcDead);
		SequentialTransition st = new SequentialTransition();
		TranslateTransition transition = new TranslateTransition(Duration.millis(500),getNode());
		transition.setByY(-20);
		TranslateTransition transition2 = new TranslateTransition(Duration.millis(1000),getNode());
		transition2.setByY(400);
		st.getChildren().addAll(transition,transition2);
		st.play();
		st.setOnFinished(e->{
			((AnchorPane)getNode().getParent()).getChildren().remove(getNode());
			if(totalCoinsCollected.get() < 9999)totalCoinsCollected.set(totalCoinsCollected.get()+COINS_GIVEN);
		});
	}
	
}
