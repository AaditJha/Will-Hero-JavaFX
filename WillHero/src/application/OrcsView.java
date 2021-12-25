package application;

import application.controller.PlayerController;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public abstract class OrcsView extends WorldObject {
	

	private OrcsController orcsController;

	public OrcsView(Point2D boundingBoxCenter, Node node) {
		super(boundingBoxCenter, node);		
	}
	
	@Override
	public void playerInteracted(PlayerController playerController) {
		Point2D playerPos = playerController.getModel().getPosition();
		Point2D orcPos = new Point2D(getNode().getLayoutX(), getNode().getLayoutY());
		orcsController.getModel().collide(playerController.getModel());
		
		
//		System.out.println(playerPos+" "+orcPos);
		
//		if(playerPos.getX() < orcPos.getX()) {
//			System.out.println("LEFT");
//		}
//		else if(playerPos.getY() > orcPos.getY()) {
//			System.out.println("TOP");
//		}
		
//		double playerVelocityX = playerController.getModel().getVelocity().getX();
//		if(playerVelocityX == 0)playerController.getModel().jumpUp();
//		else playerController.getModel().setVelocity(new Point2D(playerVelocityX, 0.0));
		
	}

	public void setController(OrcsController orcsController) {
		this.orcsController = orcsController;
	}
	
}
