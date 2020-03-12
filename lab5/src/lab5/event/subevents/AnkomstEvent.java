package lab5.event.subevents;

import lab5.Kund;
import lab5.Kund.KundID;
import lab5.classtemplates.event.Event;
import lab5.event.MarketEvent;
import lab5.state.MarketState;

public class AnkomstEvent extends MarketEvent{
	//private MarketState marketstate;

	public AnkomstEvent(Kund kund) {
		// kanske ha med att kolla max antal kunder här
		
		super.time = kund.ankomstTid; //nuvarande tid + tiden det tar innan det händer
		super.kund = kund;
		
		System.out.println("ankomstevent skapad");
	}

	public void execute() {
		System.out.println("ankexe");
			//När ett event körts så lägg adderas tiden till den globala körstiden
		System.out.println("försökerskapakund");
		//Skapa nytt ankomstevent / ny kund
		
		//Körs om butiken är öppen och det inte är fullt i butiken
		if (marketState.öppet && marketState.kunderIButiken.size() < marketState.maxAntalKunder) {
			System.out.println("pre ny kund");
			Kund k = new Kund();
			System.out.println(k + " : KUND");
			int i = marketState.getID();
			k.id = i;
			System.out.println(" ny kund");
			eventQueue.add(k.currentEvent);
			marketState.kunderIButiken.add(k);
			System.out.println("efternykund");
		}
		
		//om det är fullt i butiken eller affären är stängd så ökas antal missade kunder
		else {
			marketState.antalMissadeKunder++;
		}
		
		kund.currentEvent = new PlockEvent(kund);
		marketState.globalTime += super.time();	
		eventQueue.remove(this);
		super.runNextEvent();
		eventQueue.reorganize();
	}
	
	public static void main(String[] args) {
		

	}

}