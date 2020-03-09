package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;

public class AnkomstEvent extends Event{

	public AnkomstEvent(Kund kund) {
		// kanske ha med att kolla max antal kunder här
		time = kund.ankomstTid;
		super.kund=kund;
	}

	public void execute() {
		// TODO Auto-generated method stub
		kund.currentEvent = new PlockEvent(kund);
		
	}
	
	public static void main(String[] args) {
		

	}

}