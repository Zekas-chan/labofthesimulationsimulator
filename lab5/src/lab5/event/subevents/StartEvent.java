package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.event.EventQueue;
import lab5.event.MarketEvent;
import lab5.state.MarketState;

public class StartEvent extends MarketEvent{

	public static void main(String[] args) {
		

	}

	public void execute() {
		super.marketState.eq.add(new Kund()); //Skapar en kund och med det, en ankomstevent, vilket läggs till i EventQueue
	}
	public StartEvent(MarketState ms, EventQueue eq) {
		super.marketState = ms;
		super.eventQueue = eq;
		super.time = 0;
		
		super.runNextEvent();
		eventQueue.reorganize();
	}
}
