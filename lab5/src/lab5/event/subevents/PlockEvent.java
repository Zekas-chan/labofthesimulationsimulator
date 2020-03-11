package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;

public class PlockEvent extends Event{
	
	public PlockEvent(Kund kund) {
		super.time = kund.plockTid;
		super.kund = kund;
	}
	public static void main(String[] args) {
		

	}


	public void execute() {
		kund.currentEvent = new BetalaEvent(kund);
		marketState.globalTime += super.time();		//När ett event körts så lägg adderas tiden till den globala körstiden
		
		super.runNextEvent();
		eventQueue.reorganize();
	}

}
