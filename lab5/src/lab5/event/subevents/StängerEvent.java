package lab5.event.subevents;

import lab5.classtemplates.random.UniformRandomStream;
import lab5.event.EventQueue;
import lab5.event.MarketEvent;
import lab5.state.MarketState;

/**
 * Ett event som stänger butiken när dess execute körs.
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class StängerEvent extends MarketEvent {

	/**
	 * Konstruerar ett StängerEvent.
	 * 
	 * @param time Tiden vid vilken butiken ska stänga.
	 */
	public StängerEvent(double time, MarketState ms, EventQueue eq) {
		super.time = time;
		super.marketState = ms;
		super.eventQueue = eq;
		eventQueue.add(this);
	}

	/**
	 * Stänger butiken när den körs.
	 */
	public void execute() {	
		marketState.globalTime += super.time();
		eventQueue.remove(this);
		new StopEvent(999, marketState, eventQueue);
		marketState.öppet = false;
		
		// Uppdaterar vyn
		marketState.incomingEvent(this);
	}

	/**
	 * Returnerar namnet på detta event.
	 */
	public String toString() {
		return "Stängning";
	}

}
