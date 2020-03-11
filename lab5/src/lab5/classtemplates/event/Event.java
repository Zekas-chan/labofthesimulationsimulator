package lab5.classtemplates.event;

import java.util.Observable;

import lab5.Kund;
import lab5.event.EventQueue;
import lab5.event.subevents.BetalaEvent;
import lab5.state.MarketState;

/**
 * Represents different events in a concrete way
 * 
 * @author 
 *
 */
public abstract class Event extends Observable{
	private int executionTime;
	public MarketState marketState;
	public EventQueue eventQueue;
	
	public int time;
	public Kund kund;
	
	/* Behövs inte
	public Event(MarketState marketState) {
		this.marketState = marketState;
	}*/
	
	public void timeChange (int elapsedTime){
		time -= elapsedTime;
	}
	
	public abstract void execute();

	public int time() {
		
		return this.time;
	}
	
	/**
	 * Kör nästa event och drar bort tiden ifrån resten
	 */
	protected void runNextEvent() {
		
		int elapsedTime = eventQueue.getList().get(0).currentEvent.time();
		if(eventQueue.antalBetalaEvent()>= marketState.ledigaKassor) {
			for (int i = 0; i < eventQueue.getList().size(); i++) {
				if (eventQueue.getList().get(i).currentEvent instanceof BetalaEvent) {
					eventQueue.getList().get(i).currentEvent.harKassa;
					//flyttarutdetsenlunganerdigPhilip
				}
			}
		}
		
		
		for (int i = 0; i < eventQueue.getList().size(); i++) {
			eventQueue.getList().get(i).currentEvent.timeChange(elapsedTime);
		}
		
		eventQueue.getList().get(0).currentEvent.execute();	//kör eventet som ligger i kö
		
		

	}

}
