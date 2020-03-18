package lab5;

import lab5.event.subevents.StartEvent;
import lab5.event.subevents.StopEvent;
import lab5.state.MarketState;
import lab5.event.subevents.PlockEvent;
import lab5.event.subevents.BetalaEvent;
import lab5.classtemplates.event.Event;
import lab5.classtemplates.random.RandomMin;
import lab5.classtemplates.random.UniformRandomStream;
import lab5.event.subevents.AnkomstEvent;

/**
 * Represents a customer in a supermarket
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class Kund {

	public int id; //kundens unika identifierare
	
	private MarketState ms;

	public Event currentEvent;

	// Time is described in minutes (simulated time)
	public int queueTimer;
	
	public double ankomstTid;
	public double plockTid;
	public double betalningsTid;

	/*
	 * Enbart Spår i main
	 */
	public static void main(String[] args) {

	}

	/**
	 * Generar en kund. När kunden genererats bestäms tiderna det tar för kunden att
	 * göra olika saker i butiken.
	 */
	public Kund(MarketState ms) {
		this.ms = ms;
		ankomstTid = ms.getAnkomst();
		plockTid = ms.getPlockTid();
		betalningsTid = ms.getBetalTid();

	}
}
