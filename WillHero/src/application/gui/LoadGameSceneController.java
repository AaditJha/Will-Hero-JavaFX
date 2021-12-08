package application.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class LoadGameSceneController {
	
	private Popup popup;
	
	public void setPopup(Popup popup) {
		this.popup = popup;
	}
	
    @FXML
    private VBox gameSlotsVBox;

    @FXML
    private Button loadButton;

    @FXML
    private Button saveButton;

    @FXML
    void saveGame(MouseEvent event) {
    	System.out.println("SAVE");
    }

    @FXML
    void loadGame(MouseEvent event) {
    	System.out.println("LOAD");
    }

    @FXML
    void resumeGame(MouseEvent event) {
    	popup.hide();
    }

}
