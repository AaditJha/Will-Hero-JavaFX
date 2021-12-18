package application;

import javafx.event.EventHandler;

public abstract class PlayerOutEventHandler implements EventHandler<CustomEvent>{
	public abstract void onPlayerOutEvent(double translateBy);
	
	@Override
	public void handle(CustomEvent event) {
		event.invokeHandler(this);
	}
}
