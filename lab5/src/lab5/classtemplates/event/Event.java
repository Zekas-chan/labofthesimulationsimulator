package lab5.classtemplates.event;

import java.util.Observable;

import lab5.Kund;

/**
 * Represents different events in a concrete way
 * 
 * @author 
 *
 */
public abstract class Event extends Observable{
	private int executionTime;
	public int time;
	public Kund kund;
	public Event() {}
	
	public abstract void execute();

}