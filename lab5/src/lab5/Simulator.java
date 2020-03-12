package lab5;

import lab5.classtemplates.view.View;
import lab5.event.EventQueue;
import lab5.event.subevents.StartEvent;
import lab5.event.subevents.StopEvent;
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
	public StopEvent stopEvent;
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
		marketState = new MarketState(8600, 5, 0, 0, 20, eventQueue); //(360, 5, 0, 0, 0, 0, 50);
		startEvent = new StartEvent(marketState, eventQueue);
		stopEvent = new StopEvent(marketState.snabbKöpsÖppettider);
		System.out.println(marketState.antalGenomfördaKöp);
		
		vy = new MarketView(marketState, false);
				
	}


}
