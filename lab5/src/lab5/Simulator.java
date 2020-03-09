package lab5;

import lab5.event.EventQueue;
import lab5.event.subevents.StartEvent;
import lab5.state.MarketState;

/**
 * Runs the simulation
 * 
 * @author 
 *
 */
public class Simulator {
	public MarketState marketState;
	public EventQueue eventQueue;
	public StartEvent startEvent;
	public static void main(String[] args) {
		Simulator s = new Simulator();
		s.run();
	}
	
	/**
	 * This method runs the simulation
	 */
	
	public void run() {
		
		marketState = new MarketState(360, 5, 0, 0, 0, 0, 50);
		eventQueue = new EventQueue();
		startEvent = new StartEvent(marketState);
		while(marketState.isRunning()) {
			
			
		}
		
				
	}


}
