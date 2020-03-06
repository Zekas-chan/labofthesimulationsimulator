package lab5.event;

import java.util.Observable;

/**
 * Represents different events in a concrete way
 * 
 * @author 
 *
 */
public abstract class Event extends Observable{
	
	public Event() {}
	
	public abstract void execute();

}