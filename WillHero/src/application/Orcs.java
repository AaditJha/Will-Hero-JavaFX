package application;

import javafx.geometry.Point2D;

public class Orcs extends RigidBody {
	private static final double DRAG = -0.005;
	private static final double THRESHOLD = 0.001;
	private static final double VERT_JUMP_VEL = -0.25;
	private static final double VERT_JUMP_VEL2 = -0.6;
	private final int COINS_GIVEN;
	private int jumpCount;
	
	public Orcs(double mass, Point2D pos, int coins) {
		super(mass, pos);
		this.COINS_GIVEN = coins;
		super.setVelocity(new Point2D(0, VERT_JUMP_VEL));
		jumpCount = 0;
	}
	
	public void jump(float frameDuration) {
		super.updatePosition();
		super.updateVelocityY();
	}
	
	public void jumpUp() {
		jumpCount = (jumpCount+1)%3;
		if(jumpCount == 2)super.setVelocity(new Point2D(super.getVelocity().getX(), VERT_JUMP_VEL2));
		else super.setVelocity(new Point2D(super.getVelocity().getX(), VERT_JUMP_VEL));
	}
	
	public Point2D getPosition() {
		return super.getPosition();
	}

	public void addDrag() {
		super.updatePosition();
		super.updateVelocityX(DRAG);
		super.updateVelocityY();
		if(super.getVelocity().getX() < THRESHOLD) {
			super.setVelocity(new Point2D(0, 0));
		}
	}
}
