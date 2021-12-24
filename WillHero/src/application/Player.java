package application;

import javafx.geometry.Point2D;

public class Player extends RigidBody {

	public Player(double mass, Point2D pos) {
		super(mass, pos);
		super.setVelocity(new Point2D(0, -0.5));
		// TODO Auto-generated constructor stub
	}

	public void jump(float frameDuration) {
		super.updatePosition();
		super.updateVelocityY();
	}
	
	public void jumpUp() {
		super.setVelocity(new Point2D(super.getVelocity().getX(), -0.5));
	}
	
	public void move(float frameDuration) {
		super.setVelocity(new Point2D(1, 0));
	}

	public Point2D getPosition() {
		return super.getPosition();
	}

	public boolean addDrag() {
		super.updatePosition();
		super.updateVelocityX(-0.005);
		if(super.getVelocity().getX() < 0.001) {
			super.subtractVeclocity(new Point2D(super.getVelocity().getX(), 0));
			return false;
		}
		return true;
	}
	
}
