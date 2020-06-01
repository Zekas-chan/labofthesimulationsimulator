package lab5;

import java.util.Random;

import lab5.event.EventQueue;
import lab5.event.subevents.StartEvent;
import lab5.state.MarketState;

/**
 * Runs the simulation
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class Simulator {
	public MarketState marketState;
	public EventQueue eventQueue;
	public StartEvent startEvent;
	public MarketView vy;

	public static void main(String[] args) {
		Simulator s = new Simulator();
		s.runH2();
	}

	/**
	 * Simulering med testparametrar. Använder ett slumpat frö.
	 */
	public void run() {
		Random r = new Random(System.currentTimeMillis());
		int simtid = 15; // öppettid (när StängerEvent händer)
		int kassor = 5; // kassor
		int maxkunder = 10;
		double lambda = 2;
		double[] plocktid = { 0.5, 1.0 };
		double[] betaltid = { 2.0, 4.0 };
		int frö = r.nextInt(Integer.MAX_VALUE);

		marketState = new MarketState(simtid, kassor, lambda, frö, maxkunder, plocktid, betaltid);
		vy = new MarketView(marketState);
		marketState.start();

	}

	/**
	 * Testfall 1 med parametrar givna från uppgiften.
	 */
	public void runH1() { // simuleringsmetod som ska ge identiskt resultat till Håkans första exempel
		// kör Håkans första exempel med identiska parametrar.
		double simtid = 10; // öppettid
		int kassor = 2; // kassor
		int maxkunder = 5;
		double lambda = 1.0;
		double[] plocktid = { 0.5, 1.0 };
		double[] betaltid = { 2.0, 3.0 };
		int frö = 1234;

		eventQueue = new EventQueue();
		marketState = new MarketState(simtid, kassor, lambda, frö, maxkunder, plocktid, betaltid);
		vy = new MarketView(marketState);
		marketState.start();
	}

	/**
	 * Testfall 2 med parametrar givna från uppgiften.
	 */
	public void runH2() {
		// kör Håkans andra exempel med identiska parametrar.
		double simtid = 8; // öppettid
		int kassor = 2; // kassor
		int maxkunder = 7;
		double lambda = 3.0;
		double[] plocktid = { 0.6, 0.9 };
		double[] betaltid = { 0.35, 0.6 };
		int frö = 13;

		marketState = new MarketState(simtid, kassor, lambda, frö, maxkunder, plocktid, betaltid);
		vy = new MarketView(marketState);
		marketState.start();
	}

}
