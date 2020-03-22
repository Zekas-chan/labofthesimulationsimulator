package lab5;

import java.util.Random;

import lab5.event.EventQueue;
import lab5.event.subevents.StartEvent;
import lab5.state.MarketState;

public class Optimize {
//	MarketState marketState;
//	EventQueue eventQueue;
//	StartEvent startEvent;
	public static void main(String[] args) {
		
	}
	
	/**
	 * Kör en simulering med fixerade parametrar och returnerar sluttillståndet.
	 * @return Sluttillståndet (MarketState)
	 */
	public MarketState metod1(int registers, int FRÖ) {
		int simtid = 10; //öppettid
		int kassor = registers; //kassor
		int maxkunder = 5;
		double lambda = 1.0;
		double[] plocktid = {0.5, 1.0};
		double[] betaltid = {2.0, 3.0};
		int frö = FRÖ;
		
		EventQueue eventQueue = new EventQueue();
		MarketState marketState = new MarketState(simtid, kassor, lambda, frö, maxkunder, plocktid, betaltid, eventQueue);
		StartEvent startEvent = new StartEvent(marketState, eventQueue);
		return marketState;
	}
	/**
	 * Kör flera simuleringar med fixerat frö men varierande mängd kassor tills optimal mängd kassor hittats.
	 */
	public int metod2(int FRÖ) {
		/*
		 * Logiken är inte optimal eller testad än. Tanken är att köra på en ineffektivare metod för att få fram funktionaliteten först och sedan optimera.
		 */
		int missadeKunder = 0;
		int kassor = 1;
		int missadeFörraKörning = metod1(kassor, FRÖ).antalMissadeKunder; //etablerar en baseline med minimal mängd kassor; optimeras sedan för att missa så få kunder som möjligt.
		
		while (missadeKunder > missadeFörraKörning) {
			missadeKunder = metod1(kassor, FRÖ).antalMissadeKunder;
			if(missadeKunder < missadeFörraKörning) {
				kassor++;
			}
			
		}
		
		return kassor;
	}
	
	/**
	 * Kör en simulering med varierande frön.
	 */
	public void metod3() {
		/*
		 * Behöver testas... mycket.
		 */
		Random r = new Random(System.currentTimeMillis());
		int change = 0;
		int previousRun = 0;
		while(change < 100) {
			previousRun = metod2(r.nextInt());
			if()
		}
	}
}
