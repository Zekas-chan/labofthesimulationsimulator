package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.state.MarketState;
import lab5.Simulator;

public class BetalaEvent extends Event{

	public int queueTimer;
	public boolean harKassa;
	
	public BetalaEvent(Kund kund) {
		super.time = kund.betalningsTid;
		super.kund = kund;
		queueTimer = kund.queueTimer;
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

	public void execute() {
		
		
		
		marketState.kunderIButiken.remove(kund);
		marketState.antalGenomfördaKöp++;
		marketState.globalTime += super.time();		//När ett event körts så adderas tiden till den globala körstiden
		marketState.tidKassaKö += queueTimer;
		marketState.ledigaKassor--;
		kund.currentEvent=null;
		super.runNextEvent();
		eventQueue.reorganize();
		
		
	}

}
