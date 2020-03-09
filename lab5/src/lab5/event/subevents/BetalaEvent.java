package lab5.event.subevents;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.state.MarketState;
import lab5.Simulator;

public class BetalaEvent extends Event{

	public int maxQueueTimer;
	public int time;
	public Kund kund;
	
	public BetalaEvent(Kund kund) {
		this.time = kund.betalningsTid;
		this.kund=kund;
		maxQueueTimer = kund.maxQueueTimer;
	}
	public static void main(String[] args) {
		

	}
	public void timeChange (int elapsedTime){
		if(marketState.öppnaKassor()) {
			time = time - elapsedTime;
		}
		else {
			maxQueueTimer = maxQueueTimer - elapsedTime;
		}
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		kund.currentEvent=null;
		if (maxQueueTimer <=0) {
			marketState.antalMissadeKunder++;
		}
		else {
			marketState.antalGenomfördaKöp++;
		}
		
	}

}
