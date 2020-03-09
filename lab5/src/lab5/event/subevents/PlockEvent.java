package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;

public class PlockEvent extends Event{
	
	public int time;
	public Kund kund;

	
	public PlockEvent(Kund kund) {
		this.time = kund.plockTid;
		this.kund=kund;
	}
	public static void main(String[] args) {
		

	}


	public void execute() {
		// TODO Auto-generated method stub
		kund.currentEvent = new BetalaEvent(kund);
		
	}

}
