package lab5;

import lab5.event.EventQueue;
import lab5.event.subevents.StartEvent;
import lab5.state.MarketState;

/**
 * Runs the simulation
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class Simulator {
	public MarketState marketState;
	public EventQueue eventQueue;
	public StartEvent startEvent;
	public MarketView vy;
	
	public static void main(String[] args) {
		Simulator s = new Simulator();
		s.run();
	}
	
	/**
	 * This method runs the simulation
	 */
	public void run() {
		eventQueue = new EventQueue();
		marketState = new MarketState(600, 1, 0, 0, 10, eventQueue); //(360, 5, 0, 0, 0, 0, 50);
		vy = new MarketView(marketState, false);
		startEvent = new StartEvent(marketState, eventQueue);
		System.out.println(marketState.antalGenomfördaKöp);
				
	}


}
