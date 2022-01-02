package application.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.controller.GameController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.util.Pair;

public class LoadGameSceneController implements Initializable {

	private final String highlighted = new String("-fx-stroke: #f8f357; -fx-stroke-width: 2");
	private final String normal = new String("-fx-stroke: #000000; -fx-stroke-width: 1");
	private final Image gameSnapShot = new Image("file:Assets/GameSnapshots/demo.png");
	private MainMenuSceneController controller;
	
	private int selectedSlot;
	private boolean running;
    @FXML
    private ImageView image1, image2, image3, image4;
    
    @FXML
    private Label coins1, coins2, coins3, coins4;
    
    @FXML
    private Group grp1, grp2, grp3, grp4;
    
    @FXML
    private Label score1, score2, score3, score4;
	
    @FXML
    private Rectangle rect1, rect2, rect3, rect4;
    
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
    	if(!running)return;
    	Pair<Integer, Integer> scoreAndCoins;
    	try {
			scoreAndCoins = controller.serializeGame(selectedSlot-1);
			updateGUI(scoreAndCoins);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@FXML
    void loadGame(MouseEvent event) {
		return;
//		try {
//			controller.deserializeGame(selectedSlot-1);
//		} catch (ClassNotFoundException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }


    private void updateGUI(Pair<Integer, Integer> scoreAndCoins) {
    	if(selectedSlot == 1) {
    		image1.setImage(gameSnapShot);
    		score1.setText(scoreAndCoins.getKey().toString());
    		coins1.setText(scoreAndCoins.getValue().toString());
    	}
    	else if(selectedSlot == 2) {
    		image2.setImage(gameSnapShot);
    		score2.setText(scoreAndCoins.getKey().toString());
    		coins2.setText(scoreAndCoins.getValue().toString());
    		
    	}
    	else if(selectedSlot == 3) {
    		image3.setImage(gameSnapShot);
    		score3.setText(scoreAndCoins.getKey().toString());
    		coins3.setText(scoreAndCoins.getValue().toString());	
    	}
    	else {
    		image4.setImage(gameSnapShot);
    		score4.setText(scoreAndCoins.getKey().toString());
    		coins4.setText(scoreAndCoins.getValue().toString());
    	}
	}
	
    @FXML
    void resumeGame(MouseEvent event) {
    	popup.hide();
    }

    @FXML
    void highlight(MouseEvent event) {
    	Group selected = (Group)event.getSource();
	    if(selected.equals(grp1)) {
	    	rect1.setStyle(highlighted);
	    	rect2.setStyle(normal);
	    	rect3.setStyle(normal);
	    	rect4.setStyle(normal);
	    	selectedSlot = 1;
	    }
	    else if(selected.equals(grp2)) {
	    	rect1.setStyle(normal);
	    	rect2.setStyle(highlighted);
	    	rect3.setStyle(normal);
	    	rect4.setStyle(normal);
	    	selectedSlot = 2;
	    }
	    else if(selected.equals(grp3)) {
	    	rect1.setStyle(normal);
	    	rect2.setStyle(normal);
	    	rect3.setStyle(highlighted);
	    	rect4.setStyle(normal);
	    	selectedSlot = 3;
	    }
	    else {
	    	rect1.setStyle(normal);
	    	rect2.setStyle(normal);
	    	rect3.setStyle(normal);
	    	rect4.setStyle(highlighted);
	    	selectedSlot = 4;
	    }
    }

    
	public void setRunning(boolean gameRunning) {
		running = gameRunning;
	}
	
	public void setController(MainMenuSceneController controller) {
		this.controller = controller;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		selectedSlot = 1;
    	rect1.setStyle(highlighted);
    	for(GameController controller: controller.savedGameList) {
    		if(controller!=null) {
    			updateGUI(new Pair<Integer, Integer>(controller.getSerializableScore(), controller.getCoins()));
    		}
    	}
    	
	}

}
