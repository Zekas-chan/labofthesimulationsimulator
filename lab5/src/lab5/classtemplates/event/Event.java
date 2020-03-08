package lab5.classtemplates.event;

import java.util.Observable;

/**
 * Represents different events in a concrete way
 * 
 * @author 
 *
 */
public abstract class Event extends Observable{
	private int executionTime;
	public Event() {}
	
	public abstract void execute();

}