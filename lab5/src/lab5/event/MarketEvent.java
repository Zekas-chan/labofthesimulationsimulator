package lab5.event;

import lab5.Kund;
import lab5.classtemplates.event.Event;
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

	/**
	 * Utför nästa event.
	 */
	public void execute() {
	}
	
	/**
	 * När ett event inträffar har tid passerat, mängden tid avgör hur länge kassorna har stått overksamma (tiden gånger antalet overksamma kassor).
	 * @param time Mängden tid
	 */
	protected void idleRegisters(double time) {
		marketState.tidOverksamKassa += time * marketState.ledigaKassor;
	}
	
	
	/**
	 * När ett event inträffat har tid passerat. Mängden tid spenderad i kö är den tiden gånger antalet personer i kön.
	 * @param time Mängden tid.
	 */
	protected void registerQueue(double time) {
		marketState.tidKassaKö += time * marketState.kassaKö.size();
	}

}