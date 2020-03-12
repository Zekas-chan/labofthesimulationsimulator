package lab5.event.subevents;

import lab5.event.MarketEvent;
/**
 * Ett event som stoppar simuleringen.
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class StopEvent extends MarketEvent{

	public static void main(String[] args) {
		

	}
	
	/**
	 * Konstruerar ett stoppevent.
	 * @param time Tidpunkten vid vilken simuleringens stopp inträffar.
	 */
	public StopEvent(int time) {
		super.time = time;
	}
	
	/**
	 * Stoppar simuleringen när den anropas.
	 */
	public void execute() {
		marketState.run = false;
		
	}

}
