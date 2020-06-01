package lab5.classtemplates.event;

import java.util.Observable;
import lab5.classtemplates.state.State;
import lab5.event.EventQueue;

/**
 * Representerar generella Events.
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public abstract class Event extends Observable {
	public EventQueue eventQueue;
	public State state;

	public double time;

	/*
	 * En metod som utför Eventets händelser (påverkan på tillståndet).
	 */
	public abstract void execute();

	/**
	 * Hämtar tiden för när eventet händer.
	 * 
	 * @return Tiden som återstår.
	 */
	public double time() {
		return this.time;
	}

	/**
	 * Kör nästa event i kön.
	 */
	protected abstract void runNextEvent();

}
