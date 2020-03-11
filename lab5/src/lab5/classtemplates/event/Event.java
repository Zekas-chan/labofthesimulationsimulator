package lab5.classtemplates.event;

import java.util.Observable;

import lab5.Kund;
import lab5.event.EventQueue;
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
	public EventQueue eventQueue;
	
	public int time;
	public Kund kund;
	
	/*
	public Event(MarketState marketState) {
		this.marketState = marketState;
	}*/
	
	public void timeChange (int elapsedTime, MarketState marketState){
		//executionTime = time - elapsedTime;
	}
	
	public abstract void execute();

	public int time() {
		
		return this.time;
	}

}
