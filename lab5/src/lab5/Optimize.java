package lab5;

import java.util.Random;

import lab5.event.EventQueue;
import lab5.event.subevents.StartEvent;
import lab5.state.MarketState;

public class Optimize {
	MarketState marketState;
	EventQueue eventQueue;
	StartEvent startEvent;
	Random r;

	/*
	 * Parametrar
	 */
	int simtid;
	int kassor;
	int maxkunder;
	double lambda;
	double[] plocktid;
	double[] betaltid;
	int frö;
	/*
	 * Statistik
	 */
	int unikaKunder = 0;
	int unikaKöandeKunder = 0;
	int antalGenomfördaKöp = 0;
	int antalMissadeKunder = 0;
	double tidOverksamKassa = 0;
	double tidKassaKö = 0;

	public Optimize() {
		// Initiala parametrar
		this.simtid = 10; // öppettid
		this.kassor = 2; // kassor
		this.maxkunder = 5;
		this.lambda = 1.0;
		this.plocktid = new double[] { 0.5, 1.0 };
		this.betaltid = new double[] { 2.0, 3.0 };
		this.frö = 1234;
		this.r = new Random(System.currentTimeMillis());
	}

	public static void main(String[] args) {
		Optimize s = new Optimize();
		s.optimization();

	}

	private void optimization() {
		for (int i = 0; i < 100; i++) { // kör simuleringen många gånger med olika frön
			frö = r.nextInt(); // slumpa nytt frö
			eventQueue = new EventQueue();
			marketState = new MarketState(simtid, kassor, lambda, frö, maxkunder, plocktid, betaltid, eventQueue);
			startEvent = new StartEvent(marketState, eventQueue);
			// Sammanställ statistiken som genomsnitt
			unikaKunder += marketState.unikaKunder;
			unikaKöandeKunder += marketState.unikaKöandeKunder;
			antalGenomfördaKöp += marketState.antalGenomfördaKöp;
			antalMissadeKunder += marketState.antalMissadeKunder;
			tidOverksamKassa += marketState.tidOverksamKassa;
			tidKassaKö += marketState.tidKassaKö;
		}
		unikaKunder /= 100;
		unikaKöandeKunder /= 100;
		antalGenomfördaKöp /= 100;
		antalMissadeKunder /= 100;
		tidOverksamKassa /= 100;
		tidKassaKö /= 100;
	}

	/**
	 * Ska returnera true om en förbättring har skett i statistiken, annars false.
	 * Bör ta statistik som parametrar.
	 * 
	 * @return
	 */
	private boolean determineImprovement() {

	}

}
