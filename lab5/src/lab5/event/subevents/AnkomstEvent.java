package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.state.MarketState;

public class AnkomstEvent extends Event{
	
	public int time;
	public Kund kund;
	//private MarketState marketstate;

	public AnkomstEvent(Kund kund, MarketState marketstate) {
		// kanske ha med att kolla max antal kunder här
		super(marketstate);
		this.time = kund.ankomstTid + super.marketState.globalTime; //nuvarande tid + tiden det tar innan det händer
		this.kund=kund;
		
	}

	public void execute() {
		kund.currentEvent = new PlockEvent(kund);
		
	}
	
	public static void main(String[] args) {
		

	}

}