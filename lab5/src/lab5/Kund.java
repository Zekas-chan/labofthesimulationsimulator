package lab5;

import lab5.event.subevents.StartEvent;
import lab5.event.subevents.StopEvent;
import lab5.event.subevents.PlockEvent;
import lab5.event.subevents.BetalaEvent;
import lab5.classtemplates.event.Event;
import lab5.classtemplates.random.RandomMin;
import lab5.event.subevents.AnkomstEvent;

/**
 * Represents a customer in a supermarket
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class Kund {
	
	private final int lowerRandomRange = 1, upperRandomRange = 10;
	
	public int id;
	
	public Event currentEvent;
	
	//Time is described in minutes (simulated time)
	public int queueTimer;
	public int ankomstTid;
	public int plockTid;
	public int betalningsTid;
	
	
	/*
	 * Enbart Spår i main
	 */
	public static void main(String[] args) {
		
	}
	
	public Kund() {
		
		//Ger varje kund en random tid till varje event samt till "enrage"
		ankomstTid = new RandomMin(lowerRandomRange,upperRandomRange).getRand();
		plockTid = new RandomMin(lowerRandomRange,upperRandomRange).getRand();
		betalningsTid = new RandomMin(lowerRandomRange,upperRandomRange).getRand();
		queueTimer = 0;
		
		System.out.println("Ny kund skapad");
		
	}
}

