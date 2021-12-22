package application;

import javafx.geometry.Point2D;

public class Player extends RigidBody {

	public Player(double mass, Point2D pos) {
		super(mass, pos);
		super.updateVelocity(new Point2D(0, -0.5));
		// TODO Auto-generated constructor stub
	}

	public void jump(float frameDuration) {
		super.updatePosition();
		super.updateVelocity();
	}
	
	public void jumpUp() {
		super.updateVelocity(new Point2D(0, -1*super.getVelocity().getY()));
		super.updateVelocity(new Point2D(0, -0.5));
	}
	
	public void move(float frameDuration) {
		super.updateVelocity(new Point2D(1, 0));
	}

	public Point2D getPosition() {
		return super.getPosition();
	}

	public boolean addDrag() {
		super.updatePosition();
		super.updateVelocity(new Point2D(-0.005,0));
		if(super.getVelocity().getX() < 0.001) {
			super.updateVelocity(new Point2D(-1*super.getVelocity().getX(), 0));
			return false;
		}
		return true;
	}
	
}
