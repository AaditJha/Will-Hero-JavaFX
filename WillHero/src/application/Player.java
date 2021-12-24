package application;

import javafx.geometry.Point2D;

public class Player extends RigidBody {
	private static final double DRAG = -0.005;
	private static final double THRESHOLD = 0.001;
	private static final double VERT_JUMP_VEL = -0.6;

	public Player(double mass, Point2D pos) {
		super(mass, pos);
		super.setVelocity(new Point2D(0, VERT_JUMP_VEL));
		// TODO Auto-generated constructor stub
	}

	public void jump(float frameDuration) {
		super.updatePosition();
		super.updateVelocityY();
	}
	
	public void jumpUp() {
		super.setVelocity(new Point2D(super.getVelocity().getX(), VERT_JUMP_VEL));
	}
	
	public void move(float frameDuration) {
		super.setVelocity(new Point2D(1, 0));
	}

	public Point2D getPosition() {
		return super.getPosition();
	}

	public boolean addDrag() {
		super.updatePosition();
		super.updateVelocityX(DRAG);
		if(super.getVelocity().getX() < THRESHOLD) {
			super.subtractVeclocity(new Point2D(super.getVelocity().getX(), 0));
			return false;
		}
		return true;
	}
	
}
