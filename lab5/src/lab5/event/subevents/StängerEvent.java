package lab5.event.subevents;
import lab5.classtemplates.event.Event;
/**
 * Ett event som stänger butiken när dess execute körs.
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class StängerEvent  extends Event{

	public static void main(String[] args) {
		

	}
	/**
	 * Konstruerar ett StängerEvent.
	 * @param time Tiden vid vilken butiken ska stänga.
	 */
	public StängerEvent(int time) {
		super.time = time;
	}
	/**
	 * Stänge butiken när den körs.
	 */
	public void execute() {
		marketState.öppet = false;
		
	}

}
