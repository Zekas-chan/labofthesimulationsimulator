package lab5.event.subevents;

import lab5.classtemplates.event.Event;

public class StopEvent extends Event{

	public static void main(String[] args) {
		

	}
	
	public StopEvent(int time) {
		super.time = time;
	}

	@Override
	public void execute() {
		marketState.run = false;
		
	}

}
