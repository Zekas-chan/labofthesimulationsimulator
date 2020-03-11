package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.state.MarketState;

public class AnkomstEvent extends Event{
	//private MarketState marketstate;

	public AnkomstEvent(Kund kund) {
		// kanske ha med att kolla max antal kunder här
		
		super.time = kund.ankomstTid + super.marketState.globalTime; //nuvarande tid + tiden det tar innan det händer
		super.kund = kund;
		
	}

	public void execute() {
		kund.currentEvent = new PlockEvent(kund);
		
		//Skapa nytt ankomstevent / ny kund
		Kund k = new Kund();
		eventQueue.add(k);
		
	}
	
	public static void main(String[] args) {
		

	}

}