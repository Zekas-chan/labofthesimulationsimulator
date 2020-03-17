package lab5;

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
		s.runH1();
	}
	
	/**
	 * Denna metod kör simuleringen.
	 */
	public void run() {
		eventQueue = new EventQueue();
		marketState = new MarketState(600, 1, 0, 0, 10, eventQueue); //(360, 5, 0, 0, 0, 0, 50);
		/*
		 * Det behövs fler parametrar:
		 * Plocktider, [P_min..Pmax]: [0.5..1.0]
		 * Betaltider, [K_min..Kmax]: [2.0..3.0]
		 * -Philip
		 */
		vy = new MarketView(marketState, false);
		startEvent = new StartEvent(marketState, eventQueue);
		System.out.println(marketState.antalGenomfördaKöp);
				
	}
	
	public void runH1() { //simuleringsmetod som ska ge identiskt resultat till Håkans första exempel
		//kör Håkans första exempel med identiska parametrar.
		int simtid = 10; //öppettid
		int kassor = 2; //kassor
		int maxkunder = 5;
		double lambda = 1.0;
		double[] plocktid = {0.5, 1.0};
		double[] betaltid = {2.0, 3.0};
		int frö = 1234;
		
		eventQueue = new EventQueue();
		marketState = new MarketState(simtid, kassor, lambda, frö, maxkunder, plocktid, betaltid, eventQueue);
		vy = new MarketView(marketState, false);
		startEvent = new StartEvent(marketState, eventQueue);
	}
	
	public void runH2() {
		//kör Håkans andra exempel med identiska parametrar.
		int simtid = 8; //öppettid
		int kassor = 2; //kassor
		int maxkunder = 7;
		double lambda = 3.0;
		double[] plocktid = {0.6, 0.9};
		double[] betaltid = {0.35, 0.6};
		int frö = 13;
		
		eventQueue = new EventQueue();
		marketState = new MarketState(simtid, kassor, lambda, frö, maxkunder, plocktid, betaltid, eventQueue);
		vy = new MarketView(marketState, false);
		startEvent = new StartEvent(marketState, eventQueue);
	}


}
