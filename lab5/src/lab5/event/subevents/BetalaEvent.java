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

	public int queueTimer;
	
	
	/**
	 * Konstruerar ett nytt BetalaEvent.
	 * @param kund En referens till den unika kund som betalar.
	 */
	public BetalaEvent(Kund kund, MarketState ms, EventQueue eq) {
		super.time = kund.betalningsTid;
		super.kund = kund;
		queueTimer = kund.queueTimer;
		super.marketState = ms;
		super.eventQueue = eq;
		eventQueue.add(this);
		super.harKassa = false;
	}
	
	/**
	 * När ett event före detta event utförs i kön reduceras tiden tills detta event kan utförs.
	 * @param elapsedTime Hur lång tid som förflutit.
	 */
	public void timeChange (int elapsedTime){		
		if(harKassa) {
			time = time - elapsedTime;
		}
		else {
			queueTimer = queueTimer + elapsedTime;
		}
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
		eventQueue.remove(this);
		marketState.ledigaKassor++;
		marketState.kunderIButiken.remove(kund);
		marketState.antalGenomfördaKöp++;
		marketState.globalTime += super.time();		//När ett event körts så adderas tiden till den globala körstiden
		marketState.tidKassaKö += queueTimer;
		//Här vill vi ha en metod i marketstate som påverkar lediga kassor
			//behöver hålla koll på max antal kassor i butiken
		
		//super.runNextEvent();
	}
	
	/**
	 * @return Returnerar namnet på detta event.
	 */
	public String toString() {
		return "Betalning";
	}
	/**
	 * @return Returnerar en boolean gällande huruvida kunden kan betala eller måste ställa sig i kö.
	 */
	public void geKassa() {
		super.harKassa = true;
	}

}
