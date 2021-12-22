package application;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class GameLoop extends AnimationTimer{

	private long pauseStart;
	private long prevFrameTime; 
	private long animStart;
	private DoubleProperty animDur = new SimpleDoubleProperty(0L);
	
	private boolean paused;
	private boolean active;
	private boolean toPause;
	private boolean toPlay;
	private boolean toRestart;

	public boolean isPaused() { return this.paused; }
	public boolean isActive() { return this.active; }
	public DoubleProperty animDurProperty() { return this.animDur; }
	
	public void pause() {
		if(!paused) {
			toPause = true;
		}
	}
	
	public void play() {
		if(paused) {
			toPlay = true;
		}
	}
	
	@Override
	public void start() {
		super.start();
		active = true;
		toRestart = true;
	}
	
	@Override
	public void stop() {
		super.stop();
		pauseStart = 0;
		active = false;
		paused = false;
		toPlay = false;
		toPause = false;
		animDur.set(0);
	}
	
	@Override
	public void handle(long timeNow) {
		if(toPause) {
			paused = true;
			pauseStart = timeNow;
			toPause = false;
		}
		
		if(toPlay) {
			paused = false;
			toPlay = false;
			animStart += (timeNow - pauseStart);
		}
		
		if(toRestart) {
			paused = false;
			animStart = timeNow;
			toRestart = false;
		}
		
		if(!paused) {
			long timePassed = timeNow - animStart;
			animDur.set(timePassed/1e9);
			float frameDuration = (float)((timeNow - prevFrameTime) / 1e9);
			prevFrameTime = timeNow;
			tick(frameDuration);
		}
	}
	
	public abstract void tick(float frameDuration);
	
}
