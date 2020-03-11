package lab5.classtemplates.event;

import java.util.ArrayList;
import java.util.Observable;

import lab5.Kund;
import lab5.event.EventQueue;
import lab5.event.subevents.BetalaEvent;
import lab5.state.MarketState;

/**
 * Represents different events in a concrete way
 * 
 * @author 
 *
 */
public abstract class Event extends Observable{
	private int executionTime;
	public EventQueue eventQueue;
	
	public int time;
	
	public void timeChange (int elapsedTime){
		time -= elapsedTime;
	}
	
	public abstract void execute();

	public int time() {
		
		return this.time;
	}
	
	protected abstract void runNextEvent();

}
