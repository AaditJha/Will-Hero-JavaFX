package application;

import java.io.Serializable;

import javafx.geometry.Point2D;

public abstract class RigidBody implements Serializable {
	public transient static final double GRAVITY = 0.002;
	private transient final double mass;
	private transient Point2D position, velocity;
	
	public RigidBody(double mass, Point2D position) {
		this.mass = mass;
		this.velocity = new Point2D(0, 0);
		this.position = position;
	}
	
	public double getMomentumX() {
		return mass*velocity.getX();
	}
	
	public void updateVelocityY() {
		velocity = velocity.add(new Point2D(0, GRAVITY));
	}
	
	public void updateVelocityX(double DRAG) {
		velocity = velocity.add(new Point2D(DRAG, 0));
	}
	
	public void addVelocity(Point2D vec) {
		velocity = velocity.add(vec);
	}
	
	public void subtractVeclocity(Point2D vec) {
		velocity = velocity.subtract(vec);
	}
	public void setPosition(Point2D position) {
		this.position = position;
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
	
	public void setVelocity(Point2D vec) {
		velocity = vec;
	}

	public void collide(RigidBody body2) {
		double mass2 = body2.getMass();
		double velocity1X = ((mass - mass2)/(mass+mass2))*getVelocity().getX() + 
				(2*mass2*body2.getVelocity().getX()/(mass+mass2));
		double velocity2X = (2*mass*getVelocity().getX()/(mass+mass2) + 
				((mass2 - mass)*body2.getVelocity().getX())/(mass+mass2));
		
		setVelocity(new Point2D(velocity1X, getVelocity().getY()));
		body2.setVelocity(new Point2D(velocity2X, body2.getVelocity().getY()));
	}
	
	public void updatePositionX() {
		Point2D tempVelocity = new Point2D(velocity.getX(),0);
		position = position.add(tempVelocity);
	}

	public double getMass() {
		return mass;
	}
	
}
