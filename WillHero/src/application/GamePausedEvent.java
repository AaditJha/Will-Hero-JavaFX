package application;

import javafx.event.Event;
import javafx.event.EventType;

public class GamePausedEvent extends CustomEvent {
	public static final EventType<CustomEvent> EVENT_TYPE = new EventType<CustomEvent>(CUSTOM_EVENT_TYPE,
			"GamePausedEvent");

	public GamePausedEvent() {
		super(EVENT_TYPE);
	}
	
	@Override
	public void invokeHandler(GamePausedEventHandler handler) {
		handler.onGamePausedEvent();
	}

}
