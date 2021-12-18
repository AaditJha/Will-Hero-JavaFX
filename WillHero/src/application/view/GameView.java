package application.view;

import application.CustomEvent;
import application.LevelGenerator;
import application.PlayerOutEvent;
import application.PlayerOutEventHandler;
import application.Spawnable;
import application.WorldObject;
import application.WorldVec2;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameView {
	private final WorldVec2 DIMENSIONS;
	private final WorldVec2 PLAYER_POS;
	private ObservableList<WorldObject> worldObjects;
	private Pane root;
	
	public GameView() {
		DIMENSIONS = new WorldVec2(360, 640);
		worldObjects = FXCollections.observableArrayList();
		PLAYER_POS = new WorldVec2(50,DIMENSIONS.getY()/2);
		root = new Pane();
		root.setMaxSize(DIMENSIONS.getX(),DIMENSIONS.getY());
		root.setPrefSize(DIMENSIONS.getX(),DIMENSIONS.getY());
		root.setMinSize(DIMENSIONS.getX(),DIMENSIONS.getY());
		root.setStyle("-fx-background-color:  linear-gradient(from 25% 25% to 100% 100%, #2980B9, #6DD5FA)");
	}

	public void setStageScene(Stage primaryStage) {
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		PlayerView player = new PlayerView(PLAYER_POS, "file:Assets/Sprites/Player.png");
		player.spawn(worldObjects);
		LevelGenerator.generate(worldObjects);
		for(WorldObject worldObject: worldObjects) {
			root.getChildren().add(worldObject.getNode());
		}
		
		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent key) {
				System.out.println("Key pressed is: "+key.getCode().toString());
				if(key.getCode().equals(KeyCode.SPACE)) {
					player.move();
				}
			}
		});
		
		primaryStage.addEventFilter(CustomEvent.CUSTOM_EVENT_TYPE, new PlayerOutEventHandler() {
			
			@Override
			public void onPlayerOutEvent(double translateBy) {
				//Translate all objects here
			}
		});	
		
	}
	
	public void stopAnimations() {
		for(WorldObject worldObject: worldObjects) {
			worldObject.stopAnimation();
		}
	}
}