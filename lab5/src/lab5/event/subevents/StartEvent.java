package lab5.event.subevents;

import lab5.classtemplates.event.Event;
import lab5.state.MarketState;

public class StartEvent extends Event{

	public static void main(String[] args) {
		

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	public StartEvent(MarketState ms) {
		super.marketState = ms;
	}
}
