package lab5.event;

import java.util.ArrayList;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.event.subevents.BetalaEvent;
import lab5.state.MarketState;

public class MarketEvent extends Event {

	public MarketState marketState;

	public Kund kund;
	protected boolean harKassa;

	public static void main(String[] args) {

	}

	/**
	 * Kör nästa event och drar bort tiden ifrån resten
	 */
	public void runNextEvent() {

		int elapsedTime = eventQueue.getList().get(0).currentEvent.time();

		ArrayList<Kund> betalaLista = new ArrayList<Kund>();
		System.out.println("marketeventrunnext");
		if (eventQueue.antalBetalaEvent() >= marketState.ledigaKassor) {
			for (int i = 0; i < eventQueue.getList().size(); i++) {
				if (eventQueue.getList().get(i).currentEvent instanceof BetalaEvent) {

					betalaLista.add(eventQueue.getList().get(i));

				}
			}

			for (int i = 0; i < marketState.ledigaKassor; i++) {
				((MarketEvent) betalaLista.get(i).currentEvent).harKassa(true);
			}

		}

		for (int i = 0; i < eventQueue.getList().size(); i++) {
			eventQueue.getList().get(i).currentEvent.timeChange(elapsedTime);
		}
		System.out.println("kommervi till try");
		try {
			System.out.println(eventQueue.getList().get(0).currentEvent);
			//eventQueue.getList().get(0).currentEvent.execute(); // kör eventet som ligger i kö
			this.execute(eventQueue.getList().get(0).currentEvent);
			System.out.println("tryen gjord i tryen");
		}

		catch (NullPointerException e) {
			eventQueue.reorganize();
		}
		eventQueue.reorganize();

	}

	public void harKassa(boolean harKassa) {
		this.harKassa = harKassa;
	}

	public void execute(Event e) {
		System.out.println("execute");
		e.execute();
	}

}