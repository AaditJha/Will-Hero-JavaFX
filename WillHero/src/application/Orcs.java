package application;

import java.io.File;

import application.controller.GameController;
import javafx.geometry.Point2D;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Orcs extends RigidBody {
	private static final double DRAG = -0.005;
	private static final double THRESHOLD = 0.001;
	private static final double VERT_JUMP_VEL = -0.25;
	private static final double VERT_JUMP_VEL2 = -0.6;
	private final int COINS_GIVEN;
	private int jumpCount;
	private int health;
	private transient Media media = new Media(new File("src/application/sounds/orckilledbysword.wav").toURI().toString());
	private transient MediaPlayer mediaplayer = new MediaPlayer(media);
	
	public Orcs(double mass, Point2D pos, int coins, int health) {
		super(mass, pos);
		this.COINS_GIVEN = coins;
		super.setVelocity(new Point2D(0, VERT_JUMP_VEL));
		jumpCount = 0;
		this.health = health;
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

	public boolean damage(int damage) {
		health -= damage;
		if(health <= 0) {
			health = 0;
			if(!GameController.muted) {
				mediaplayer.setVolume(0.5);
				mediaplayer.play();
			}
			return true;
		}
		return false;
	}
}
