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
		Node playerNode = playerController.getView().getNode();
		

//		System.out.println(playerNode.getLayoutX()+playerNode.getTranslateX()+" "+(getNode().getLayoutX()+getNode().getTranslateX()));
		if(getNode().getLayoutX()+getNode().getTranslateX() < playerNode.getLayoutX()+playerNode.getTranslateX()) {
			System.out.println("MARA");
		}
		else {
			orcsController.getModel().collide(playerController.getModel());
		}
		
//		System.out.println(playerNode.getLayoutY()+playerNode.getTranslateY()+" "+(getNode().getLayoutY()+getNode().getTranslateY()));
		
//		if(playerNode.getLayoutY() < getNode().getLayoutY()+getNode().getTranslateY()) {
//			System.out.println(playerNode.getLayoutX()+" "+(getNode().getLayoutX()+getNode().getTranslateX()));
//		}
		
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
