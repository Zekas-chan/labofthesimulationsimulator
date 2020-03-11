package lab5;

import lab5.event.subevents.StartEvent;
import lab5.event.subevents.StopEvent;
import lab5.event.subevents.PlockEvent;
import lab5.event.subevents.BetalaEvent;
import lab5.classtemplates.event.Event;
import lab5.classtemplates.random.RandomMin;
import lab5.event.subevents.AnkomstEvent;

/**
 * Represents a costumer in a supermarket
 * 
 * @author 
 *
 */
public class Kund {
	
	private final int lowerRandomRange = 1, upperRandomRange = 10;
	
	public Event currentEvent;
	
	//Time is described in minutes (simulated time)
	public int maxQueueTimer;
	public int ankomstTid;
	public int plockTid;
	public int betalningsTid;
	
	
	/*
	 * Enbart Spår i main
	 */
	public static void main(String[] args) {
		
		Kund kund1 = new Kund();
		
		System.out.println(kund1.ankomstTid);
		System.out.println(kund1.plockTid);
		System.out.println(kund1.betalningsTid);
		
	}
	
	public Kund() {
		
		//Ger varje kund en random tid till varje event samt till "enrage"
		ankomstTid = new RandomMin(lowerRandomRange,upperRandomRange).getRand();
		plockTid = new RandomMin(lowerRandomRange,upperRandomRange).getRand();
		betalningsTid = new RandomMin(lowerRandomRange,upperRandomRange).getRand();
		maxQueueTimer = new RandomMin(lowerRandomRange,upperRandomRange).getRand(); //Tror jag kan ha svamlat och att den här inte är nödvändig -Philip
		
		//currentEvent = new StartEvent(); //ska inte finnas här?
	}

}
