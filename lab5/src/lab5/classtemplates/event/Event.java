package lab5.classtemplates.event;

import java.util.Observable;

import lab5.Kund;
import lab5.state.MarketState;

/**
 * Represents different events in a concrete way
 * 
 * @author 
 *
 */
public abstract class Event extends Observable{
	private int executionTime;
	public MarketState marketState;
	public Event() {}
	public void timeChange (int elapsedTime){
		time = time - elapsedTime;
	}
	public abstract void execute();

}
