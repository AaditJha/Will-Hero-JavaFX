package application;

import java.util.Observable;

import application.view.PlayerView;
import javafx.collections.ObservableList;

public class OrcsController {

	private Orcs orcs;
	private OrcsView view;
	
	public OrcsController(Orcs orcs, OrcsView view) {
		this.orcs = orcs;
		this.view = view;
		
		view.setController(this);
	}
	
	public Orcs getModel() { return this.orcs; }
	public OrcsView getView() { return this.view; }


}
