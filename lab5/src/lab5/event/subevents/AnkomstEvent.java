package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.event.EventQueue;
import lab5.event.MarketEvent;
import lab5.state.MarketState;

/**
 * Ett event som skapar en kund när det körs.
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class AnkomstEvent extends MarketEvent {
	
	/**
	 * Konstruerar ett AnkomstEvent.
	 * @param ms Referens till MarketState
	 * @param eq Referens till EventQueue
	 */
	public AnkomstEvent(MarketState ms, EventQueue eq) {
		marketState = ms;
		eventQueue = eq;

		Kund k = new Kund(ms);
		k.id = marketState.getID();
		k.currentEvent = this;
		super.time = ms.globalTime + k.ankomstTid; //när det händer
		kund = k;

		eventQueue.add(this);
		//System.out.println("Ankomsttid: "+k.ankomstTid); //debug
	}

	/**
	 * Gör följande operationer när anropad: Skapar ett nytt PlockEvent. Avancerar
	 * den globala tiden. Om snabbköpet är öppet och butiken inte är full så skapas
	 * en ny kund som tilldelas ett unikt ID. Om butiken inte är öppen eller om den
	 * är full så 'missas' kunden och respektive statistikvariabel förändras. (det
	 * här är fel, om snabbköpet är stängt ska kunden inte missas.) Slutligen körs
	 * nästa event och kön omorganiseras.
	 */
	public void execute() {
		//
		registerQueue(time - marketState.globalTime);
		
		//
		idleRegisters(time - marketState.globalTime);
		// Uppdaterar vyn
		marketState.incomingEvent(this);
		
		//Eventet inträffar och tiden sätts till denna tid
		marketState.globalTime = super.time();
		
		if(canEnter()) {
			// När en kund anländer i butiken läggs den till i "kundeributiken" listan
			marketState.kunderIButiken.add(this.kund);
			
			//En ny unik kund har anlänt till butiken
			marketState.unikaKunder++;
			
			kund.currentEvent = new PlockEvent(kund, super.marketState, super.eventQueue);
		}else {
			if(marketState.kunderIButiken.size() == marketState.maxAntalKunder) {
			marketState.unikaKunder++;
			marketState.antalMissadeKunder++;
			}
		}
		
		//Tar bort det här eventet ur kön
		eventQueue.remove(this);
		
		//Genererar ett nytt ankomstevent om butiken är öppen
		if(marketState.öppet) {
			new AnkomstEvent(marketState, eventQueue);
		}
	}
	
	private boolean canEnter() {
		if(marketState.öppet && marketState.kunderIButiken.size() < marketState.maxAntalKunder) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * @return Returnerar namnet på detta event.
	 */
	public String toString() {
		return "Ankomst   ";
	}

}