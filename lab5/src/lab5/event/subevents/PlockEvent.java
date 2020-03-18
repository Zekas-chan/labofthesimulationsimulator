package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.event.EventQueue;
import lab5.event.MarketEvent;
import lab5.state.MarketState;

/**
 * Gör ett plockevent som representerar en kund som samlar varor.
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class PlockEvent extends MarketEvent {
	/**
	 * Konstruerar ett PlockEvent.
	 * 
	 * @param kund Referens till den unika kunden som handlar.
	 */
	public PlockEvent(Kund kund, MarketState ms, EventQueue eq) {
		super.time = kund.plockTid;
		super.kund = kund;

		super.marketState = ms;
		super.eventQueue = eq;

		eventQueue.add(this);

	}

	/**
	 * Skapar ett nytt BetalaEvent när den körs, lägger till samma unika kund som
	 * den skapades med i det Eventet, avancerar den globala simuleringstiden samt
	 * kör nästa event i händelsekön.
	 */
	public void execute() {		
		eventQueue.remove(this);
		kund.currentEvent = new BetalaEvent(kund, super.marketState, super.eventQueue);
		marketState.globalTime += super.time(); // När ett event körts så lägg adderas tiden till den globala körstiden
		
		// Uppdaterar vyn
		marketState.incomingEvent(this);
	}

	/**
	 * Returnerar namnet på detta event.
	 */
	public String toString() {
		return "Handlar   ";
	}

}
