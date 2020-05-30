package lab5.event.subevents;

import lab5.Kund;
import lab5.event.EventQueue;
import lab5.event.MarketEvent;
import lab5.state.MarketState;


/**
 * Ett event som representerar en kund som betalar för sina varor.
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class BetalaEvent extends MarketEvent {

	/**
	 * Konstruerar ett nytt BetalaEvent.
	 * 
	 * @param kund En referens till den unika kund som betalar.
	 */
	public BetalaEvent(Kund kund, MarketState ms, EventQueue eq) {
		super.time = ms.globalTime + kund.getBetalTid();
		super.kund = kund;
		super.marketState = ms;
		super.eventQueue = eq;
		eventQueue.add(this);
		// System.out.println("Betalningstid: "+kund.betalningsTid); //debug
	}

	/**
	 * Utför en mängd operationer när den körs:
	 * Statistiker för kö och overksamma kassor uppdateras.
	 * 
	 * Tiden är nu detta events händelsetillfälle.
	 * 
	 * Vyn uppdateras.
	 * 
	 * 
	 */
	public void execute() {
		// Event inträffar, tiden för kassakön ökar
		registerQueue(time - marketState.globalTime);

		// Event träffar, tiden för overksamma kassor ökar OM butiken fortfarande är
		// öppen.
		idleRegisters(time - marketState.globalTime);

		// Variabeln för tidpunkten när senaste betalningen skedde sätts till detta
		// events tidpunkt.
		marketState.setFinalPaymentEvent(super.time());

		// Uppdaterar vyn
		marketState.incomingEvent(this);

		// Eventet inträffar och tiden sätts till denna tid
		marketState.globalTime = super.time();

		// Tar bort detta event från kön.
		eventQueue.remove(this);

		// Kunden är nu klar i butiken och tas bort.
		marketState.getKunderIButiken().remove(kund);

		// Betalningen är klar, antalet genomförda köp ökar
		marketState.incAntalGenomfördaKöp();

		// Mängden tid kunden stod i kö läggs till i statistiken.
		marketState.addTidKassaKö(kund.queueTimer);

		// Nästa kund i kön läggs nu till i EventQueue och börjar betala. Om det inte
		// finns någon kund i kön ökar antalet lediga kassor, annars förblir det
		// konstant.
		marketState.incLedigaKassor(nextInQueue() ? 0 : 1);

		// Om det finns kunder i kön skapas ett nytt betalaevent med den första kunden,
		// och kunden tas bort från kassakön.
		if (nextInQueue()) {
			new BetalaEvent(marketState.getKassaKö().get(0), super.marketState, super.eventQueue);
			marketState.getKassaKö().remove(0);
		}
	}

	/*
	 * Hjälpmetod för execute. Avgör om det finns kunder i kön.
	 */
	private boolean nextInQueue() {
		if (marketState.getKassaKö().size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return Returnerar namnet på detta event.
	 */
	public String toString() {
		return "Betalning";
	}

}
