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
		
		super.time = ms.globalTime + kund.plockTid;
		super.kund = kund;

		super.marketState = ms;
		super.eventQueue = eq;

		eventQueue.add(this);
		//System.out.println("Plocktid: "+kund.plockTid); //debug
	}

	/**
	 * Skapar ett nytt BetalaEvent när den körs, lägger till samma unika kund som
	 * den skapades med i det Eventet, avancerar den globala simuleringstiden samt
	 * kör nästa event i händelsekön.
	 */
	public void execute() {
		// Uppdaterar vyn
		marketState.incomingEvent(this);
		
		marketState.globalTime = super.time(); // När ett event körts så lägg adderas tiden till den globala körstiden
		eventQueue.remove(this);
		
		if(!registersFull()) {
			kund.currentEvent = new BetalaEvent(kund, super.marketState, super.eventQueue);
			marketState.ledigaKassor--;
		}
		
		
	}
	
	private boolean registersFull() {
		if(marketState.ledigaKassor == 0) {
			marketState.kassaKö.add(this.kund);
			marketState.unikaKöandeKunder++;
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Returnerar namnet på detta event.
	 */
	public String toString() {
		return "Plock   ";
	}

}
