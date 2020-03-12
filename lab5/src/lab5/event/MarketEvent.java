package lab5.event;

import java.util.ArrayList;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.event.subevents.BetalaEvent;
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

	public static void main(String[] args) {

	}

	/**
	 * Kör nästa event och drar bort tiden ifrån resten
	 */
	public void runNextEvent() {
		
		System.out.println(marketState.öppet + "ms öppet i MarketEvent");

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
		System.out.println(eventQueue.getList().get(0) + " först i listan, från MArketEvent");
		
		//this.execute(eventQueue.getList().get(0));
		
		eventQueue.getList().get(0).execute();
		
		System.out.println("tryen gjord i tryen");
		
		//drar bort körtiden på eventent från dem andra eventen i kön
		for (int i = 0; i < eventQueue.getList().size(); i++) {
			eventQueue.getList().get(i).timeChange(elapsedTime);
		}
	}

	public void harKassa(boolean harKassa) {
		this.harKassa = harKassa;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}