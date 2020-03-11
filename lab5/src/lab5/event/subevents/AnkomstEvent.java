package lab5.event.subevents;

import lab5.Kund;
import lab5.Kund.KundID;
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
		marketState.globalTime += super.time();		//När ett event körts så lägg adderas tiden till den globala körstiden
		
		//Skapa nytt ankomstevent / ny kund
		if (marketState.öppet && marketState.kunderIButiken.size() < marketState.maxAntalKunder) {
			Kund k = new Kund();
			k.id = marketState.id.getID();
			eventQueue.add(k);
			marketState.kunderIButiken.add(k);
			
		}
		
		super.runNextEvent();
		eventQueue.reorganize();
	}
	
	public static void main(String[] args) {
		

	}

}