package lab5.event.subevents;

import lab5.Kund;
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
		
		//När en kund anländer i butiken läggs den till i "kundeributiken" listan
		marketState.kunderIButiken.add(this.kund);
		
		System.out.println("ankexe");
			//När ett event körts så lägg adderas tiden till den globala körstiden
		System.out.println("försökerskapakund");
		//Skapa nytt ankomstevent / ny kund
		
		//Körs om butiken är öppen och det inte är fullt i butiken
		
		System.out.println(marketState.öppet);
		System.out.println(marketState.kunderIButiken.size());
		System.out.println(marketState.maxAntalKunder);
		
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
			System.out.println(marketState.antalMissadeKunder + " antalmissadekunder");
		}
		
		System.out.println("fortfarande i execute i ank");
		
		kund.currentEvent = new PlockEvent(kund);
		marketState.globalTime += super.time();	
		eventQueue.remove(this);
		super.runNextEvent();
	}
	
	public static void main(String[] args) {
		

	}

}