package lab5.event.subevents;

import lab5.event.EventQueue;
import lab5.event.MarketEvent;
import lab5.state.MarketState;

/**
 * Startar simuleringen.
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class StartEvent extends MarketEvent {
	final static int stopTime = 999;
	/**
	 * Eventet inträffar och orsakar följande:
	 * 
	 * Vyn uppdateras
	 * 
	 * Ett nytt AnkomstEvent skapas och läggs till i kön.
	 * 
	 * Ett nytt StängerEvent skapas och läggs till i kön.
	 */
	public void execute() {
		// Uppdaterar vyn
		super.marketState.incomingEvent(this);

		// Ett nytt AnkomstEvent skapas och läggs till i kön.
		new AnkomstEvent(super.marketState, super.eventQueue);

		// Ett nytt StängerEvent skapas och läggs till i kön.
		new StängerEvent(super.marketState.getSnabbKöpsÖppettider(), super.marketState, super.eventQueue);
		
		//Ett nytt StopEvent läggs till i kön.
		new StopEvent(stopTime, marketState, eventQueue);
		
		// Eventet är klart och tas bort från kön.
		eventQueue.remove(this);
		
		super.runNextEvent();

	}

	/**
	 * Konstruerar ett startevent och tar två referenser som parametrar.
	 * 
	 * @param ms Referens till ett MarketState
	 * @param eq Referens till en EventQueue
	 */
	public StartEvent(MarketState ms, EventQueue eq) {
		super.marketState = ms;
		super.eventQueue = eq;
		super.time = 0;
		eq.add(this);
	}

	/**
	 * Returnerar namnet på detta event.
	 */
	public String toString() {
		return "START";
	}
}
