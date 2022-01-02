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

public class RedOrcView extends OrcsView{
	private static final Image redOrc = new Image("file:Assets/Sprites/RedOrc.png");
	private static final Image redOrcDead = new Image("file:Assets/Sprites/RedOrcDead.png");
	private static final int COINS_GIVEN = 10;
	
	
	public RedOrcView(Point2D boundingBoxCenter) {
		super(boundingBoxCenter, new ImageView(redOrc));
		getNode().setScaleX(0.3);
		getNode().setScaleY(0.3);
	}
	
	@Override
	public void deathAnim(IntegerProperty totalCoinsCollected) {
		((ImageView)getNode()).setImage(redOrcDead);
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
