package application;

import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GlobalData implements Serializable{
	private static final long serialVersionUID = 1L;
	private int highscoreInt, totalCoinCount;
	
	public GlobalData(int highscoreInt, int totalCoinCount) {
		this.highscoreInt = highscoreInt;
		this.totalCoinCount = totalCoinCount;
	}

	public int getHighScore() {
		return highscoreInt;
	}

	public int getTotalCoinCount() {
		return totalCoinCount;
	}

	public void setHighScore(int highscoreInt) {
		this.highscoreInt = highscoreInt;
	}

	public void setTotalCoinCount(int totalCoinCount) {
		this.totalCoinCount = totalCoinCount;
	}
	
	
	
	
	
	
}
