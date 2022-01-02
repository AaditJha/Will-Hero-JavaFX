package application;

import javafx.event.Event;
import javafx.event.EventType;

public class GameOverEvent extends CustomEvent {
	public static final EventType<CustomEvent> EVENT_TYPE = new EventType<CustomEvent>(CUSTOM_EVENT_TYPE,
			"GameOverEvent");

	public GameOverEvent() {
		super(EVENT_TYPE);
	}
	
	@Override
	public void invokeHandler(GamePausedEventHandler handler) {
		handler.onGamePausedEvent();
	}

}
