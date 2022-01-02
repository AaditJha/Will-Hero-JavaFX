package application;

import javafx.event.EventHandler;

public abstract class GamePausedEventHandler implements EventHandler<CustomEvent>{
	public abstract void onGamePausedEvent();
	
	@Override
	public void handle(CustomEvent event) {
		event.invokeHandler(this);
	}
}
