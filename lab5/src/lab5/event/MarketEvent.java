package lab5.event;

import java.util.ArrayList;

import lab5.Kund;
import lab5.classtemplates.event.Event;
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

	public static void main(String[] args) {

	}

	/**
	 * Kör nästa event och drar bort tiden ifrån resten
	 */
	public void runNextEvent() {
		
		//Kollar hur många betalaevent som är i kön och tilldelar dem n första en kassa
		
		int kassor = marketState.ledigaKassor;
		for (int i = 0; i < eventQueue.getList().size(); i++) {
			
			if (eventQueue.getList().get(i) instanceof BetalaEvent && kassor > 0) {
			
				((BetalaEvent) eventQueue.getList().get(i)).geKassa();
				kassor--;
			}
			
		}
		
		
		//Kör execute på nästa event i kön
		//System.out.println("Nu kör vi reorganize");
		//eventQueue.reorganize();
		
		//this.execute(eventQueue.getList().get(0));
		if (!eventQueue.isEmpty()) {
			
			int elapsedTime = eventQueue.getList().get(0).time();
			eventQueue.getList().get(0).execute();
			
			//drar bort körtiden på eventent från dem andra eventen i kön
			for (int i = 0; i < eventQueue.getList().size(); i++) {
				eventQueue.getList().get(i).timeChange(elapsedTime);
			}
			
			if(marketState.run) {
				runNextEvent();
			}
			
		}
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}