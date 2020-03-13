package lab5.event.subevents;
import lab5.classtemplates.random.UniformRandomStream;
import lab5.event.EventQueue;
import lab5.event.MarketEvent;
import lab5.state.MarketState;
/**
 * Ett event som stänger butiken när dess execute körs.
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class StängerEvent  extends MarketEvent{

	public static void main(String[] args) {
		

	}
	/**
	 * Konstruerar ett StängerEvent.
	 * @param time Tiden vid vilken butiken ska stänga.
	 */
	public StängerEvent(int time, MarketState ms, EventQueue  eq) {
		super.time = time;
		super.marketState = ms;
		super.eventQueue = eq;
	}
	/**
	 * Stänger butiken när den körs.
	 */
	public void execute() {
		System.out.println("StängerEventet har körts...");	//spår
		marketState.globalTime += super.time();	
		eventQueue.remove(this);
		StopEvent stop = new StopEvent(99999, marketState, eventQueue);
		eventQueue.add(stop);
		marketState.öppet = false;
	}
	
	public String toString() {
		return "Stängning";
	}

}
