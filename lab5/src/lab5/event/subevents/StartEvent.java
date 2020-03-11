package lab5.event.subevents;

import lab5.classtemplates.event.Event;
import lab5.state.MarketState;

public class StartEvent extends Event{

	public static void main(String[] args) {
		

	}

	@Override
	public void execute() {
		super.marketState.eq.addEvent(new AnkomstEvent(null, super.marketState)); //ta bort null om AnkomstEvent ska skapa kunden
	}
	public StartEvent(MarketState ms) {
		super(ms);
	}
}
