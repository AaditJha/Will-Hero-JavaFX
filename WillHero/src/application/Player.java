package application;

import java.io.Serializable;

import application.view.GameView;
import javafx.geometry.Point2D;

public class Player extends RigidBody implements Serializable{
	private transient static final double mass = 1.0;
	private transient static final double DRAG = -0.005;
	private transient static final double THRESHOLD = 0.001;
	private transient static final double VERT_JUMP_VEL = -0.5;

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

	public void setPos(Point2D playerPos) {
		setPosition(playerPos);
	}
	
}
