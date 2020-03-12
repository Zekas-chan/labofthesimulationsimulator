package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.event.MarketEvent;

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
	public PlockEvent(Kund kund) {
		super.time = kund.plockTid;
		super.kund = kund;
	}

	public static void main(String[] args) {

	}

	/**
	 * Skapar ett nytt BetalaEvent när den körs, lägger till samma unika kund som
	 * den skapades med i det Eventet, avancerar den globala simuleringstiden samt
	 * kör nästa event i händelsekön.
	 */
	public void execute() {
		kund.currentEvent = new BetalaEvent(kund);
		marketState.globalTime += super.time();		//När ett event körts så lägg adderas tiden till den globala körstiden
		eventQueue.remove(this);
		super.runNextEvent();
		eventQueue.reorganize();
	}

}
