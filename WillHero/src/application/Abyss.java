package application;

import javafx.scene.Node;

public final class Abyss {
	private static final double ABYSS_DEPTH = 500.0;
	private Abyss() {
	}
	public static boolean hasFallen(Node node) {
		return (node.getLayoutY() > ABYSS_DEPTH);
	}
}
