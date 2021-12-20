package application;

public class WorldVec2 {
	private double posX, posY;

	public WorldVec2(double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public double getX() {
		return this.posX;
	}
	
	public double getY() {
		return this.posY;
	}
	
	public void add(WorldVec2 vec) {
		posX = posX + vec.getX();
		posY = posY + vec.getY();
	}
	
	public void subtract(WorldVec2 vec) {
		posX = posX - vec.getX();
		posY = posY - vec.getY();
	}
	
}
