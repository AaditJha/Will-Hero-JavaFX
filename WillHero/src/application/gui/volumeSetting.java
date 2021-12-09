package application.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class volumeSetting {
	private ImageView graphic;
	private Image selectedImage, unselectedImage;
	private boolean selected;
	
	volumeSetting(Image selectedImage, Image unselectedImage, ImageView ref) {
		this.graphic = ref;
		this.selectedImage = selectedImage;
		this.unselectedImage = unselectedImage;
		this.selected = true;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void swapState() {
		if(this.isSelected()) {
			graphic.setImage(unselectedImage);
			selected = false;
		}
		else {
			graphic.setImage(selectedImage);
			selected = true;
		}
	}
}