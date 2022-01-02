package application;

import application.view.GameView;
import javafx.geometry.Point2D;

public class Player extends RigidBody {
	private static final double mass = 1.0;
	private static final double DRAG = -0.005;
	private static final double THRESHOLD = 0.001;
	private static final double VERT_JUMP_VEL = -0.5;

	private Helmet helmetWorn;
	
	public Player(Point2D pos) {
		super(mass, pos);
		this.helmetWorn = new Helmet();
		super.setVelocity(new Point2D(0, VERT_JUMP_VEL));
		// TODO Auto-generated constructor stub
	}

	public Helmet getHelmet() {
		return this.helmetWorn;
	}
	
	public void jump(float frameDuration) {
		if(helmetWorn.getEquippedWeapon() instanceof Lance) {
			((Lance)helmetWorn.getEquippedWeapon()).updatePos(getPosition());
		}
		super.updatePosition();
		super.updateVelocityY();
//		helmetWorn.getEquippedWeapon().updatePosition();
	}
	
	public void jumpUp() {
		super.setVelocity(new Point2D(super.getVelocity().getX(), VERT_JUMP_VEL));
	}
	
	public void move(float frameDuration) {
		if(helmetWorn.getEquippedWeapon()!= null)helmetWorn.getEquippedWeapon().useWeapon(getPosition());
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
