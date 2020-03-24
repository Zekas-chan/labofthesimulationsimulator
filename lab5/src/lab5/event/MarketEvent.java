package lab5.event;

import java.util.ArrayList;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.event.subevents.AnkomstEvent;
import lab5.event.subevents.BetalaEvent;
import lab5.event.subevents.StopEvent;
import lab5.state.MarketState;

/**
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class MarketEvent extends Event {

	public MarketState marketState;

	public Kund kund;

	/**
	 * Kör nästa event och drar bort tiden ifrån resten
	 */
	public void runNextEvent() {
		// Kör execute på nästa event i kön (rekursivt)
		if (!eventQueue.isEmpty()) {
			
			eventQueue.getList().get(0).execute();

			if (marketState.run) {
				runNextEvent();
			}

		}
	}

	public void execute() {
	}
	
	public void idleRegisters(double time) {
		marketState.tidOverksamKassa += time * marketState.ledigaKassor;
	}
	
	
	/**
	 * När ett event inträffat har tid passerat. Mängden tid spenderad i kö är den tiden gånger antalet personer i kön.
	 * @param time Mängden tid.
	 */
	public void registerQueue(double time) {
		marketState.tidKassaKö += time * marketState.kassaKö.size();
	}

}