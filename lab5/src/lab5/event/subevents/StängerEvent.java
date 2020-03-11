package lab5.event.subevents;
import lab5.classtemplates.event.Event;

public class StängerEvent  extends Event{

	public static void main(String[] args) {
		

	}
	
	public StängerEvent(int time) {
		super.time = time;
	}

	public void execute() {
		marketState.öppet = false;
		
	}

}
