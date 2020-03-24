package lab5.event.subevents;

import lab5.event.EventQueue;
import lab5.event.MarketEvent;
import lab5.state.MarketState;

/**
 * Ett event som stoppar simuleringen.
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class StopEvent extends MarketEvent {

	/**
	 * Konstruerar ett stoppevent.
	 * 
	 * @param time Tidpunkten vid vilken simuleringens stopp inträffar.
	 */
	public StopEvent(double time, MarketState ms, EventQueue eq) {
		super.time = time;
		super.marketState = ms;
		super.eventQueue = eq;
		eventQueue.add(this);
	}

	/**
	 * Stoppar simuleringen när den anropas.
	 */
	public void execute() {
		marketState.finalEvent = marketState.globalTime;
		
		marketState.incomingEvent(this);
		
		marketState.globalTime = time;
		marketState.run = false;
		eventQueue.remove(this);
	}

	/**
	 * Returnerar namnet på detta event.
	 */
	public String toString() {
		return "STOP";
	}

}
