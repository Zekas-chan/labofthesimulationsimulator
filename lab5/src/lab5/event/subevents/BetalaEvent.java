package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.event.EventQueue;
import lab5.event.MarketEvent;
import lab5.state.MarketState;
import lab5.Simulator;
/**
 * Ett event som representerar en kund som betalar för sina varor.
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class BetalaEvent extends MarketEvent{
	
	
	/**
	 * Konstruerar ett nytt BetalaEvent.
	 * @param kund En referens till den unika kund som betalar.
	 */
	public BetalaEvent(Kund kund, MarketState ms, EventQueue eq) {
		super.time = ms.globalTime + kund.betalningsTid;
		super.kund = kund;
		super.marketState = ms;
		super.eventQueue = eq;
		eventQueue.add(this);
		//System.out.println("Betalningstid: "+kund.betalningsTid); //debug
	}
	
	/**
	 * Utför en mängd operationer när den körs:
	 * Tar bort den unika kunden som eventet skapades med från butiken.
	 * Inkrementerar antalet genomförda köp.
	 * Avancerar den globala tiden.
	 * Ökar States spårning av tid spenderad i kön (0, om kunden inte köat).
	 * Minskar antalet lediga kassor? (det här kan inte vara rätt, antalet ska minska när kunden betalar, när execute körs är kunden klar och den ska öka igen)
	 * Nollställer kundens nuvarande event.
	 * Samt kör nästa event och sorterar om kön i händelseordning.
	 */
	public void execute() {
		// Uppdaterar vyn
		marketState.incomingEvent(this);
		
		//När ett event körts så adderas tiden till den globala körstiden
		marketState.globalTime = super.time();
		
		//Tar bort detta event från kön.
		eventQueue.remove(this);
		
		//Mängden lediga kassor ökar.
		marketState.ledigaKassor++;
		
		//Kunden är nu klar i butiken och tas bort.
		marketState.kunderIButiken.remove(kund);
		
		//Betalningen är klar, antalet genomförda köp ökar
		marketState.antalGenomfördaKöp++;
		
		//Mängden tid kunden stod i kö läggs till i statistiken.
		marketState.tidKassaKö += kund.queueTimer;
		
		//Väntande kunders kötid ökas.
		queueTime();
		
		//Nästa kund i kön läggs nu till i EventQueue och börjar betala. Om det inte finns någon kund i kön ökar antalet lediga kassor, annars förblir det konstant.
		marketState.ledigaKassor = nextInQueue() ? marketState.ledigaKassor : marketState.ledigaKassor++;
			
	}
	
	private void queueTime() {
		for(int i = 0; i < marketState.kassaKö.size(); i++) {
			marketState.kassaKö.get(i).queueTimer += kund.betalningsTid;
		}
	}
	
	private boolean nextInQueue() {
		if(marketState.kassaKö.size() > 0) {
			new BetalaEvent(marketState.kassaKö.get(0),super.marketState, super.eventQueue);
			marketState.kassaKö.remove(0);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * @return Returnerar namnet på detta event.
	 */
	public String toString() {
		return "Betalning";
	}

}
