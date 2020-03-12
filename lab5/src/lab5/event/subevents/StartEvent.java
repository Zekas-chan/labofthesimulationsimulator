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
		
		Kund k = new Kund();
		k.id = marketState.getID();
		System.out.println(k.id + " ID i starteevent");  //Spår
		eventQueue.add(k.currentEvent);
		
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
		System.out.println("kommerdutillrunnextevent?");
		super.runNextEvent();
		eventQueue.reorganize();
	}
}
