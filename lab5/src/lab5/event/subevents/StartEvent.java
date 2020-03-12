package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.event.EventQueue;
import lab5.event.MarketEvent;
import lab5.state.MarketState;
/**
 * Startar simuleringen.
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class StartEvent extends MarketEvent{

	public static void main(String[] args) {
		

	}
	/**
	 * Lägger till ett nytt ankomstevent i kön. 
	 */
	public void execute() {
		super.eventQueue.add(new Kund()); //Skapar en kund och med det, en ankomstevent, vilket läggs till i EventQueue
	}
	/**
	 * Konstruerar ett startevent och tar två referenser som parametrar.
	 * @param ms Referens till ett MarketState
	 * @param eq Referens till en EventQueue
	 */
	public StartEvent(MarketState ms, EventQueue eq) {
		super.marketState = ms;
		super.eventQueue = eq;
		super.time = 0;
		this.execute();
		super.runNextEvent();
		eventQueue.reorganize();
	}
}
