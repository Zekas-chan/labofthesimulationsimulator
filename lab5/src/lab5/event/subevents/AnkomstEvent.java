package lab5.event.subevents;

import lab5.Kund;
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
	 * 
	 * @param ms Referens till ett MarketState
	 * @param eq Referens till en EventQueue
	 */
	public AnkomstEvent(MarketState ms, EventQueue eq) {
		marketState = ms;
		eventQueue = eq;

		// Ny kund kommer anlända; en ny kund skapas.
		Kund k = new Kund(ms);
		k.id = marketState.getID(); // tilldela identifierare
		k.currentEvent = this;
		super.time = ms.globalTime + k.ankomstTid;
		kund = k;

		eventQueue.add(this);
		// System.out.println("Ankomsttid: "+k.ankomstTid); //debug
	}

	/**
	 * Gör en rad operationer när metoden körs:
	 * 
	 * Statistikvariabler för overksamma kassor och kassakötid ökar.
	 * 
	 * Vyn uppdateras.
	 * 
	 * Simuleringstiden är nu detta events tid (med andra ord, händelsen sker nu).
	 * 
	 * Om butiken är öppen och inte full läggs en kund in i den och ett PlockEvent
	 * med denna kund skapas. Annars ökas statistiken för missade kunder.
	 * 
	 * Sist skapas ett nytt AnkomstEvent.
	 */
	public void execute() {
		// Event inträffar, tiden för kassakön ökar
		registerQueue(time - marketState.globalTime);

		// Event träffar, tiden för overksamma kassor ökar OM butiken fortfarande är
		// öppen.
		if (marketState.isÖppet()) {
			idleRegisters(time - marketState.globalTime);
		}

		// Uppdaterar vyn
		marketState.incomingEvent(this);

		// Eventet inträffar och tiden sätts till denna tid
		marketState.globalTime = super.time();

		// Om butiken är öppen och inte full läggs en kund till i butikens lista över
		// kunder.
		if (canEnter()) {
			// När en kund anländer i butiken läggs den till i "kundeributiken" listan
			marketState.getKunderIButiken().add(this.kund);

			// En ny unik kund har anlänt till butiken
			marketState.incUnikaKunder();
			
			//Kunden börjar handla: Ett nytt PlockEvent skapas.
			kund.currentEvent = new PlockEvent(kund, super.marketState, super.eventQueue);
		} else {
			// Om butiken är öppen OCH full så ökar antalet unika kunder samt antalet
			// missade kunder.
			if (marketState.getKunderIButiken().size() == marketState.maxAntalKunder) {
				marketState.incUnikaKunder();
				marketState.incMissadeKunder();
			}
		}

		// Tar bort det här eventet ur kön
		eventQueue.remove(this);

		// Genererar ett nytt ankomstevent om butiken är öppen
		if (marketState.isÖppet()) {
			new AnkomstEvent(marketState, eventQueue);
		}
	}

	/*
	 * Hjälpmetod för execute.
	 */
	private boolean canEnter() {
		if (marketState.isÖppet() && marketState.getKunderIButiken().size() < marketState.maxAntalKunder) {
			return true;
		} else {
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