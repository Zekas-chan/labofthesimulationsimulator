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

		int elapsedTime = eventQueue.getList().get(0).time();
		
		//Kollar hur många betalaevent som är i kön och tilldelar dem n första en kassa
		ArrayList<Event> betalaLista = new ArrayList<Event>();
		System.out.println("marketeventrunnext");
		if (eventQueue.antalBetalaEvent() >= marketState.ledigaKassor) {
			for (int i = 0; i < eventQueue.getList().size(); i++) {
				if (eventQueue.getList().get(i) instanceof BetalaEvent) {

					betalaLista.add(eventQueue.getList().get(i));

				}
			}

			for (int i = 0; i < marketState.ledigaKassor; i++) {
				((MarketEvent) betalaLista.get(i)).harKassa(true);
			}

		}
		
		//Kör execute på nästa event i kön
		System.out.println("kommervi till try");
		try {
			System.out.println(eventQueue.getList().get(0));
			//eventQueue.getList().get(0).execute(); // kör eventet som ligger i kö
			this.execute(eventQueue.getList().get(0));
			System.out.println("tryen gjord i tryen");
		}

		catch (NullPointerException e) {
			eventQueue.reorganize();
		}
		
		//drar bort körtiden på eventent från dem andra eventen i kön
		for (int i = 0; i < eventQueue.getList().size(); i++) {
			eventQueue.getList().get(i).timeChange(elapsedTime);
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