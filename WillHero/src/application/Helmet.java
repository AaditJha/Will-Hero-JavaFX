package application;

import javafx.scene.image.Image;

public class Helmet {
	private Image playerHelmet;
	private Weapon weaponA, weaponB;
	
	public Helmet() {
		playerHelmet = new Image("file:Assets/Sprites/Player.png");
		weaponA = new ThrowingKnives();
		weaponB = new Lance();
	}
	public Image getPlayerHelmetIMG() {
		return this.playerHelmet;
	}
	public Weapon getEquippedWeapon() {
		return weaponA;
	}
	public ThrowingKnives getThrowingKnife() {
		// TODO Auto-generated method stub
		return (ThrowingKnives) weaponA;
	}
	public Lance getLance() {
		// TODO Auto-generated method stub
		return (Lance) weaponB;
	}
}
