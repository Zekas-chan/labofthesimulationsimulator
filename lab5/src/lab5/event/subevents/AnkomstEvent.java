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
	// private MarketState marketstate;
	/**
	 * Konstruerar ett ankomstevent.
	 * 
	 * @param kund Referens till kunden??
	 */
	public AnkomstEvent(MarketState ms, EventQueue eq) {
		super.marketState = ms;
		super.eventQueue = eq;

		// kanske ha med att kolla max antal kunder här
		Kund k = new Kund();
		k.id = marketState.getID();
		k.currentEvent = this;
		super.time = k.ankomstTid; // nuvarande tid + tiden det tar innan det händer
		super.kund = k;

		eventQueue.add(this);
	}

	/**
	 * Gör följande operationer när anropad: Skapar ett nytt PlockEvent. Avancerar
	 * den globala tiden. Om snabbköpet är öppet och butiken inte är full så skapas
	 * en ny kund som tilldelas ett unikt ID. Om butiken inte är öppen eller om den
	 * är full så 'missas' kunden och respektive statistikvariabel förändras. (det
	 * här är fel, om snabbköpet stängt ska kunden inte missas.) Slutligen körs
	 * nästa event och kön omorganiseras.
	 */
	public void execute() {

		if (marketState.öppet && marketState.kunderIButiken.size() < marketState.maxAntalKunder) {
			// När en kund anländer i butiken läggs den till i "kundeributiken" listan
			marketState.kunderIButiken.add(this.kund);

			new AnkomstEvent(marketState, eventQueue);
		}

		// om det är fullt i butiken eller affären är stängd så ökas antal missade
		// kunder
		else {
			marketState.antalMissadeKunder++;
		}

		eventQueue.remove(this);
		kund.currentEvent = new PlockEvent(kund, super.marketState, super.eventQueue);
		marketState.globalTime += super.time();
	}

	/**
	 * @return Returnerar namnet på detta event.
	 */
	public String toString() {
		return "Ankomst";
	}

	public static void main(String[] args) {

	}

}