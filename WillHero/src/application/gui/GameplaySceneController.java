package application.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;

import javafx.scene.image.ImageView;

import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.Axis;

public class GameplaySceneController implements Initializable {
	

	private ArrayList<ImageView> clouds;
	
	@FXML
	private AnchorPane cloudsContainerPane;
	@FXML
	private ImageView dummyPlayer;
	@FXML
	private Polygon topBar;
	@FXML
	private Label coinCountLabel;
	@FXML
	private Group obstacleWindMillGroup;
	@FXML
	private ImageView windmillPivot;
	@FXML
	private ImageView windmillFan3;
	@FXML
	private ImageView windmillFan1;
	@FXML
	private ImageView windmillFan2;
	@FXML
	private ImageView dummyChest;
	@FXML
	private ImageView dummyOrc;
	@FXML
	private Label currentScoreLabel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		initializeClouds();
		ParallelTransition parallelTransition = new ParallelTransition();
		animatePlayer(parallelTransition);
		animateClouds(parallelTransition);
		
		windmillFan1.getTransforms().add(new Translate(windmillFan1.getFitWidth()/2,0));
//		windmillFan1.setTranslateX(windmillFan1.getFitWidth()/2);
//		windmillFan1.setTranslateY(0);		
		windmillFan2.getTransforms().add(new Translate(windmillFan2.getFitWidth()/2,0));
//		windmillFan2.setTranslateX(windmillFan2.getFitWidth()/2);
//		windmillFan2.setTranslateY(0);
		windmillFan3.getTransforms().add(new Translate(windmillFan3.getFitWidth()/2,0));
//		windmillFan3.setTranslateX(windmillFan3.getFitWidth()/2);
//		windmillFan3.setTranslateY(0);
		
		animateWindMill(parallelTransition,windmillFan1);
		animateWindMill(parallelTransition,windmillFan2);
		animateWindMill(parallelTransition,windmillFan3);
		animateOrc(parallelTransition);
		animateChest(parallelTransition);
		
		parallelTransition.play();
	}
	
	private void animateChest(ParallelTransition parallelTransition) {
		SequentialTransition sequentialTransition = new SequentialTransition(dummyChest);
		sequentialTransition.setCycleCount(Animation.INDEFINITE);
		
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400),dummyChest);
		translateTransition.setAutoReverse(true);
		translateTransition.setCycleCount(2);
		translateTransition.setByY(-3);
		translateTransition.setInterpolator(Interpolator.EASE_OUT);
		
		TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(200),dummyChest);
		translateTransition2.setAutoReverse(true);
		translateTransition2.setCycleCount(2);
		translateTransition2.setByY(-1);
		translateTransition2.setInterpolator(Interpolator.EASE_OUT);
		
		
		sequentialTransition.getChildren().addAll(translateTransition,translateTransition2);
		sequentialTransition.play();
		parallelTransition.getChildren().add(sequentialTransition);
	}

	private void animateOrc(ParallelTransition parallelTransition) {
		SequentialTransition sequentialTransition = new SequentialTransition(dummyOrc);
		sequentialTransition.setCycleCount(Animation.INDEFINITE);
		
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(700),dummyOrc);
		translateTransition.setAutoReverse(true);
		translateTransition.setCycleCount(2);
		translateTransition.setByY(-50);
		translateTransition.setInterpolator(Interpolator.EASE_OUT);
		
		TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(1400),dummyOrc);
		translateTransition2.setAutoReverse(true);
		translateTransition2.setCycleCount(2);
		translateTransition2.setByY(-100);
		translateTransition2.setInterpolator(Interpolator.EASE_OUT);
		
		
		sequentialTransition.getChildren().addAll(translateTransition,translateTransition2);
		sequentialTransition.play();
		parallelTransition.getChildren().add(sequentialTransition);
	}

	private void animateWindMill(ParallelTransition parallelTransition, Node fan) {
		
		RotateTransition rotateTransition = new RotateTransition(Duration.millis(5000),fan);
		rotateTransition.setByAngle(-360);
		rotateTransition.setAxis(Rotate.Z_AXIS);
		rotateTransition.setCycleCount(Animation.INDEFINITE);
		rotateTransition.setInterpolator(Interpolator.LINEAR);
		parallelTransition.getChildren().add(rotateTransition);
	}

	private void initializeClouds() {
		clouds = new ArrayList<ImageView>();
		double[] cloudSizes = {0.15,0.20,0.25,0.20};
		int[] startingPoints = {
				0,	-140,
			  -80,	  40,
			 -200,	-180,
			  100,   140
		};
		final int cloudCount = 4;
		for(int i = 1; i <= cloudCount; i++) {
			ImageView tempCloud = new ImageView("file:Assets/Background/Clouds"+i+".png");
			tempCloud.setScaleX(cloudSizes[i-1]);
			tempCloud.setScaleY(cloudSizes[i-1]);
			tempCloud.relocate(startingPoints[2*(i-1)], startingPoints[2*i-1]);
			clouds.add(tempCloud);
		}
		cloudsContainerPane.getChildren().addAll(clouds);
	}

	private void animatePlayer(ParallelTransition parallelTransition) {
		TranslateTransition translateTransition = new TranslateTransition(Duration.millis(600),dummyPlayer);
		translateTransition.setAutoReverse(true);
		translateTransition.setCycleCount(Animation.INDEFINITE);
		translateTransition.setByY(-50);
		translateTransition.setInterpolator(Interpolator.EASE_OUT);
		parallelTransition.getChildren().add(translateTransition);
	}
	
	private void animateClouds(ParallelTransition parallelTransition) {
		for(ImageView cloud: clouds) {
			TranslateTransition translateTransition = new TranslateTransition(Duration.millis(10000),cloud);
			translateTransition.setCycleCount(Animation.INDEFINITE);
			translateTransition.setFromX(360);
			translateTransition.setToX(-360);
			translateTransition.setInterpolator(Interpolator.LINEAR);
			parallelTransition.getChildren().add(translateTransition);
		}
	}

	

}
