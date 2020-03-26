package lab5.event.subevents;

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
		// Event inträffar, tiden för kassakön ökar
		registerQueue(time - marketState.globalTime);
				
		// Event träffar, tiden för overksamma kassor ökar OM butiken fortfarande är
		// öppen.
		idleRegisters(time - marketState.globalTime);
		
		// Uppdaterar vyn
		marketState.incomingEvent(this);
		
		// Eventet inträffar och tiden sätts till denna tid
		marketState.globalTime = super.time();
		
		//Tar bort detta event ur kön.
		eventQueue.remove(this);
		
		//Butiken har stängt, när alla väntande händelser är klara stoppas simuleringen.
		new StopEvent(999, marketState, eventQueue);
		
		//Butiken är inte längre öppen.
		marketState.öppet = false;
		
		
	}

	/**
	 * Returnerar namnet på detta event.
	 */
	public String toString() {
		return "Stängning";
	}

}
