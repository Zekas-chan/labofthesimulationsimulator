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
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public abstract class Event extends Observable{
	private int executionTime;
	public EventQueue eventQueue;
	
	public int time;
	/**
	 * Förändrar hur lång tid som återstår innan eventet ska hända.
	 * @param elapsedTime Mängden tid som förflutit.
	 */
	public void timeChange (int elapsedTime){
		time -= elapsedTime;
	}
	/*
	 * En metod som utför Eventets händelser.
	 */
	public abstract void execute();
	/**
	 * 
	 * @return
	 */
	/**
	 * Hämtar tiden som återstår innan eventet händer.
	 * @return Tiden som återstår.
	 */
	public int time() {
		
		return this.time;
	}
	/**
	 * Kör nästa event i kön.
	 */
	protected abstract void runNextEvent();

}
