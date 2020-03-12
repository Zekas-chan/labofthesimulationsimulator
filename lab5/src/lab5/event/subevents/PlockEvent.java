package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.event.MarketEvent;

public class PlockEvent extends MarketEvent{
	
	public PlockEvent(Kund kund) {
		super.time = kund.plockTid;
		super.kund = kund;
		
		System.out.println("Plockeventskapas");
		
	}
	public static void main(String[] args) {
		

	}


	public void execute() {
		kund.currentEvent = new BetalaEvent(kund);
		marketState.globalTime += super.time();		//När ett event körts så lägg adderas tiden till den globala körstiden
		eventQueue.remove(this);
		super.runNextEvent();
		eventQueue.reorganize();
	}

}
