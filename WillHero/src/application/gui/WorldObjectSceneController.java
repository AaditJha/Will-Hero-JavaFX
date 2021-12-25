package application.gui;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;

public class WorldObjectSceneController {

    @FXML
    private ImageView chest;

    @FXML
    private Group obstacleWindMillGroup;

    @FXML
    private ImageView windmillFan1;

    @FXML
    private ImageView windmillFan2;

    @FXML
    private ImageView windmillFan3;

    @FXML
    private ImageView windmillPivot;

    public Group getWindmill() {
    	return obstacleWindMillGroup;
    }
    
    public ImageView getChest() {
    	return chest;
    }

	public ImageView getWindmillFan1() {
		return windmillFan1;
	}

	public ImageView getWindmillFan2() {
		return windmillFan2;
	}

	public ImageView getWindmillFan3() {
		return windmillFan3;
	}
	
	public ImageView getWindmillPivot() {
		return windmillPivot;
	}
    
}
