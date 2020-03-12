package lab5.event.subevents;
import lab5.event.MarketEvent;

public class StängerEvent  extends MarketEvent{

	public static void main(String[] args) {
		

	}
	
	public StängerEvent(int time) {
		super.time = time;
	}

	public void execute() {
		marketState.öppet = false;
		
	}

}
