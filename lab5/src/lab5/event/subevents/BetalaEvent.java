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
	public boolean harKassa;
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
	}
	public static void main(String[] args) {
		

	}
	public void timeChange (int elapsedTime){
		if(marketState.öppnaKassor()) {
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
		marketState.kunderIButiken.remove(kund);
		marketState.antalGenomfördaKöp++;
		marketState.globalTime += super.time();		//När ett event körts så adderas tiden till den globala körstiden
		marketState.tidKassaKö += queueTimer;
		marketState.ledigaKassor--;
		eventQueue.remove(this);
		//super.runNextEvent();
	}

}
