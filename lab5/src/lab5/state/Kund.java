package lab5.state;

import lab5.classtemplates.event.Event;

/**
 * Representerar en kund i en butik.
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class Kund {

	public int id; // kundens unika identifierare

	private MarketState ms;

	public Event currentEvent;

	// Time is described in time units (simulated time)
	public double queueTimer;

	public double ankomstTid;
	public double plockTid;
	public double betalningsTid;

	/**
	 * Konstruerar ett kundobjekt.
	 */
	Kund(MarketState ms) {
		this.ms = ms;

		ankomstTid = ms.getAnkomst();

		queueTimer = 0;

	}

	/**
	 * Hämtar tiden det tar för kunden att plocka varor.
	 * 
	 * @return Tiden det tar.
	 */
	public double getPlockTid() {
		plockTid = ms.getPlockTid();
		return plockTid;
	}

	/**
	 * Hämtar tiden det tar för kunden att betala för sina varor.
	 * 
	 * @return Tiden det tar.
	 */
	public double getBetalTid() {
		betalningsTid = ms.getBetalTid();
		return betalningsTid;
	}
}
