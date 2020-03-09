package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;

public class AnkomstEvent extends Event{
	
	public int time;
	public Kund kund;

	public AnkomstEvent(Kund kund) {
		// kanske ha med att kolla max antal kunder här
		this.time = kund.ankomstTid;
		this.kund=kund;
	}

	public void execute() {
		// TODO Auto-generated method stub
		kund.currentEvent = new PlockEvent(kund);
		
	}
	
	public static void main(String[] args) {
		

	}

}