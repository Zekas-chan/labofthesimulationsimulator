package lab5;

import lab5.event.Event;
import lab5.random.UniformRandomStream;
/**
 * Represents a costumer in a supermarket
 * 
 * @author 
 *
 */
public class Kund {
	
	private final int lowerRandomRange = 1, upperRandomRange = 10;
	
	public Event currentEvent;
	public int maxQueueTimer;
	public int ankomstTid;
	public int plockTid;
	public int betalningsTid;

	public static void main(String[] args) {
		

	}
	
	public Kund() {
		
		UniformRandomStream random = new UniformRandomStream(1,10);
		
		ankomstTid;
		
	}

}
