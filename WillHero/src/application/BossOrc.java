package application;

import javafx.geometry.Point2D;

public class BossOrc extends Orcs {
	private static final double mass = 1000;

	public BossOrc(Point2D pos) {
		super(mass, pos, 100);
	}
}
