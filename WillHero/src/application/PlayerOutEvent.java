package application;

import javafx.event.Event;
import javafx.event.EventType;

public class PlayerOutEvent extends CustomEvent {
	private final double valTranslate;
	public static final EventType<CustomEvent> EVENT_TYPE = new EventType<CustomEvent>(CUSTOM_EVENT_TYPE,
			"PlayerOutEvent");

	public PlayerOutEvent(double valTranslate) {
		super(EVENT_TYPE);
		this.valTranslate = valTranslate;
	}
	
	@Override
	public void invokeHandler(PlayerOutEventHandler handler) {
		handler.onPlayerOutEvent(valTranslate);
	}

}
