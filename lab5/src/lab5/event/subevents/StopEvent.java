package lab5.event.subevents;

import lab5.event.MarketEvent;

public class StopEvent extends MarketEvent{

	public static void main(String[] args) {
		

	}
	
	public StopEvent(int time) {
		super.time = time;
	}

	public void execute() {
		marketState.run = false;
		
	}

}
