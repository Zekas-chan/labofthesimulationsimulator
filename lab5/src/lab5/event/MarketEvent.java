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
	protected boolean harKassa;

	/**
	 * Kör nästa event och drar bort tiden ifrån resten
	 */
	public void runNextEvent() {

		// Kollar hur många betalaevent som är i kön och tilldelar dem n första en kassa
		for (int i = 0; i < eventQueue.getList().size(); i++) {
			boolean exists = false;

			if (eventQueue.getList().get(i) instanceof BetalaEvent && marketState.ledigaKassor > 0) {

				((BetalaEvent) eventQueue.getList().get(i)).geKassa();
				marketState.ledigaKassor--;

				// Om kunden finns nånstans i kassakön tas den bort ur den listan
				for (int j = 0; j < marketState.kassaKö.size(); j++) {
					if (((BetalaEvent) eventQueue.getList().get(i)).kund == marketState.kassaKö.get(j)) {
						marketState.kassaKö.remove(((BetalaEvent) eventQueue.getList().get(i)).kund);
					}
				}

			}

			// när alla kassor är upptagna läggs resternade kunder in i kassakö-listan
			else if (eventQueue.getList().get(i) instanceof BetalaEvent && marketState.ledigaKassor == 0) {
				
				//Kollar om kunden redan ligger i kassakön
				for (int j = 0; j < marketState.kassaKö.size(); j++) {
					if ((((BetalaEvent) eventQueue.getList().get(i)).kund) == marketState.kassaKö.get(j)) {
						exists = true;
					}
				}
				
				if (!exists) {
					marketState.kassaKö.add(((BetalaEvent) eventQueue.getList().get(i)).kund);
				}
			}

		}
		
		

		// Kör execute på nästa event i kön
		if (!eventQueue.isEmpty()) {

			double elapsedTime = eventQueue.getList().get(0).time();
			eventQueue.getList().get(0).execute();

			// drar bort körtiden på eventent från dem andra eventen i kön - därför i börjar på 1
			for (int i = 1; i < eventQueue.getList().size(); i++) {
				eventQueue.getList().get(i).timeChange(elapsedTime);
			}

			if (marketState.run) {
				runNextEvent();
			}

		}
	}

	public void execute() {
	}
	
	public void newAnkomst(MarketState ms, EventQueue eq) {
		new AnkomstEvent(ms, eq);
	}

}