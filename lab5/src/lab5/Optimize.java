package lab5;

import java.util.Random;
import lab5.state.MarketState;

public class Optimize {
	public static void main(String[] args) {
		Optimize opt = new Optimize();
		opt.metod3();
	}
	int simtid = 50; // öppettid
	int maxkunder = 5;
	double lambda = 1.0;
	double[] plocktid = { 0.5, 1.0 };
	double[] betaltid = { 2.0, 3.0 };

	/**
	 * Kör en simulering med fixerade parametrar och returnerar sluttillståndet.
	 * 
	 * @return Sluttillståndet (MarketState)
	 */
	/**
	 * Kör en simulering med angivna parametrar.
	 * @param registers Antal kassor.
	 * @param FRÖ Fröet som ska användas.
	 * @return Sluttillståndet (MarketState-objekt).
	 */
	public MarketState metod1(int registers, int FRÖ) {
		MarketState marketState = new MarketState(simtid, registers, lambda, FRÖ, maxkunder, plocktid, betaltid);
		marketState.start();
		return marketState;
	}

	/**
	 * Kör simuleringar tills ett antal kassor som missar minimal mängd kunder hittats.
	 * @param FRÖ Fröet som simuleringen ska använda.
	 * @return Optimal mängd kassor.
	 */
	public int metod2(int FRÖ) {
		int kassor = 1;
		int missade = 0;
		int missadeNext = 0;
		
		while(true) {
			missade = metod1(kassor, FRÖ).antalMissadeKunder;
			missadeNext = metod1(kassor+1, FRÖ).antalMissadeKunder;
			if(missade == missadeNext) {
				break;
			}else {
				kassor++;
			}
		}
		
		return kassor;
	}

	/**
	 * Kör en simulering med varierande frön.
	 */
	public void metod3() {
		Random r = new Random(System.currentTimeMillis());
		int previousRun = metod2(r.nextInt(Integer.MAX_VALUE));
		int check = 0;
		int change = 0;
		while (change < 100) {
			check = metod2(r.nextInt(Integer.MAX_VALUE));
			if(check > previousRun) { //om mängden kassor är större än förra körningen
				change = 0; //nollställ räkning
				previousRun = check; //nytt högsta
			}
			change++;
		}
		System.out.println("Optimal mängd kassor beräknat till att vara "+check);
	}
}
