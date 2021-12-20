package application;

public abstract class RigidBody {
	public static final double GRAVITY = 1;
	
	private final double mass;
	private WorldVec2 position, velocity;
	
	public RigidBody(double mass) {
		this.mass = mass;
	}
	
	public double getMomentumX() {
		return mass*velocity.getX();
	}
	
	public void updateVelocity() {
		velocity.subtract(new WorldVec2(0, GRAVITY));
	}
	
	public void updateVelocity(WorldVec2 vec) {
		velocity.add(vec);
	}
	
	public void updatePosition() {
		position.add(velocity);
	}
	
}
