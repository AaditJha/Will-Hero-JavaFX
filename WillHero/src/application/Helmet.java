package application;

import javafx.scene.image.Image;

public class Helmet {
	private enum WeaponStatus {DISABLED, ACTIVE, UNACTIVE};
	
	private Image playerHelmet;
	private Weapon weaponA, weaponB;
	private WeaponStatus statusA, statusB;
	
	public Helmet() {
		statusA = WeaponStatus.DISABLED;
		statusB = WeaponStatus.DISABLED;
		playerHelmet = new Image("file:Assets/Sprites/Player.png");
		weaponA = new Lance();
		weaponB = new ThrowingKnives();
		weaponA.getNode().setVisible(false);
	}
	public Image getPlayerHelmetIMG() {
		return this.playerHelmet;
	}
	public Weapon getEquippedWeapon() {
		if(statusA == WeaponStatus.DISABLED && statusB == WeaponStatus.DISABLED)return null;
		if(statusA == WeaponStatus.ACTIVE) {
			weaponA.getNode().setVisible(true);
			return weaponA;
		}
		return weaponB;
	}
	
	public ThrowingKnives getThrowingKnife() {
		// TODO Auto-generated method stub
		return (ThrowingKnives) weaponB;
	}
	public Lance getLance() {
		// TODO Auto-generated method stub
		return (Lance) weaponA;
	}
	
	
	public void swapWeapon() {
		if(statusA == WeaponStatus.ACTIVE && statusB == WeaponStatus.UNACTIVE) {
			statusA = WeaponStatus.UNACTIVE;
			weaponA.getNode().setVisible(false);
			statusB = WeaponStatus.ACTIVE;
		}
		
		else if(statusB == WeaponStatus.ACTIVE && statusA == WeaponStatus.UNACTIVE) {
			statusB = WeaponStatus.UNACTIVE;
			statusA = WeaponStatus.ACTIVE;
		}
	}
	public void equipWeapon(boolean isWeaponA) {
		if(isWeaponA) {
			weaponA.getNode().setVisible(true);
			weaponA.upgrade();
			statusA = WeaponStatus.ACTIVE;
			if(statusB == WeaponStatus.ACTIVE)statusB = WeaponStatus.UNACTIVE;
		}
		else { 
			statusB = WeaponStatus.ACTIVE;
			if(statusA == WeaponStatus.ACTIVE) {
				statusA = WeaponStatus.UNACTIVE;
				weaponA.getNode().setVisible(false);
			}
			weaponB.upgrade();
		}
	}
	public boolean bothWeaponUnlocked(boolean activeWeaponA) {
		if(activeWeaponA && statusA == WeaponStatus.ACTIVE && statusB == WeaponStatus.UNACTIVE) return true;
		if(!activeWeaponA && statusB == WeaponStatus.ACTIVE && statusA == WeaponStatus.UNACTIVE) return true;
		return false;
	}
}
