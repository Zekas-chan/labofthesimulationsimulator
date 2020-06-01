package lab5.event;

import lab5.classtemplates.event.Event;
import lab5.state.CustomerFactory;
import lab5.state.Kund;
import lab5.state.MarketState;

/**
 * Representerar generella butiksevent.
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class MarketEvent extends Event {
	public MarketState marketState;
	
	public Kund kund;
	
	protected CustomerFactory custfac;

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
		marketState.addTidOverksamKassa(time * marketState.getledigaKassor());
	}
	
	
	/**
	 * När ett event inträffat har tid passerat. Mängden tid spenderad i kö är den tiden gånger antalet personer i kön.
	 * @param time Mängden tid.
	 */
	protected void registerQueue(double time) {
		marketState.addTidKassaKö(time * marketState.getKassaKö().size());
	}

}