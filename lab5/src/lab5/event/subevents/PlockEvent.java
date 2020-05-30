package lab5.event.subevents;

import lab5.Kund;
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

		super.time = ms.globalTime + kund.getPlockTid();
		super.kund = kund;

		super.marketState = ms;
		super.eventQueue = eq;

		eventQueue.add(this);
		// System.out.println("Plocktid: "+kund.plockTid); //debug
	}

	/**
	 * Skapar ett nytt BetalaEvent när den körs, lägger till samma unika kund som
	 * den skapades med i det Eventet, avancerar den globala simuleringstiden samt
	 * kör nästa event i händelsekön.
	 */
	public void execute() {
		// Event inträffar, tiden för kassakön ökar
		registerQueue(time - marketState.globalTime);

		// Event träffar, tiden för overksamma kassor ökar OM butiken fortfarande är
		// öppen.
		idleRegisters(time - marketState.globalTime);

		// Uppdaterar vyn
		marketState.incomingEvent(this);

		// Eventet inträffar och tiden sätts till denna tid
		marketState.globalTime = super.time(); // När ett event körts så lägg adderas tiden till den globala körstiden

		// Tar bort detta event ur kön.
		eventQueue.remove(this);

		// Om det finns lediga kassor så skapas ett nytt BetalaEvent med denna kund,
		// annars läggs kunden till i kön.
		if (!registersFull()) {
			kund.currentEvent = new BetalaEvent(kund, super.marketState, super.eventQueue);
			marketState.decLedigaKassor();
		} else {
			marketState.getKassaKö().add(this.kund);
			marketState.incUnikaKöandeKunder();
		}

	}

	/*
	 * Evaluerar huruvida alla kassor är upptagna eller inte.
	 */
	private boolean registersFull() {
		if (marketState.getledigaKassor() == 0) {
			return true;
		} else {
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
