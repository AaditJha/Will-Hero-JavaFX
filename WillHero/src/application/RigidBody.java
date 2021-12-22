package application;

import javafx.geometry.Point2D;

public abstract class RigidBody {
	public static final double GRAVITY = 0.002;
	
	private final double mass;
	private Point2D position, velocity;
	
	public RigidBody(double mass, Point2D position) {
		this.mass = mass;
		this.velocity = new Point2D(0, 0);
		this.position = position;
	}
	
	public double getMomentumX() {
		return mass*velocity.getX();
	}
	
	public void updateVelocity() {
		velocity = velocity.add(new Point2D(0, GRAVITY));
	}
	
	public void updateVelocity(Point2D vec) {
		velocity = velocity.add(vec);
	}
	
	public void updatePosition() {
		position = position.add(velocity);
	}

	public Point2D getPosition() {
		return position;
	}

	public Point2D getVelocity() {
		// TODO Auto-generated method stub
		return velocity;
	}
	
}
