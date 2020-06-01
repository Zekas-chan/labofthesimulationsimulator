package lab5;

import java.util.Random;
import lab5.state.MarketState;
/**
 * Hittar den optimala mängden kassor för en butik med specifika parametrar.
 * @author Philip Larsson, Patrik Grund
 *
 */
public class Optimize {
	public static void main(String[] args) {
		Optimize opt = new Optimize();
		opt.findOptimalRegisters();
	}
	int simtid = 500; // öppettid
	int maxkunder = 5;
	double lambda = 1.0;
	double[] plocktid = { 0.5, 1.0 };
	double[] betaltid = { 2.0, 3.0 };
	
	/**
	 * Kör en simulering med angivna parametrar.
	 * @param registers Antal kassor.
	 * @param FRÖ Fröet som ska användas.
	 * @return Sluttillståndet (MarketState-objekt).
	 */
	public MarketState runSim(int registers, int FRÖ) { //metod 1
		MarketState marketState = new MarketState(simtid, registers, lambda, FRÖ, maxkunder, plocktid, betaltid);
		marketState.start();
		return marketState;
	}

	/**
	 * Kör simuleringar tills ett antal kassor som missar minimal mängd kunder hittats.
	 * @param FRÖ Fröet som simuleringen ska använda.
	 * @return Optimal mängd kassor.
	 */
	public int findRegisters(int FRÖ) { //metod 2
		int kassor = 1;
		int missade = 0;
		int missadeNext = 0;
		
		while(true) {
			missade = runSim(kassor, FRÖ).getAntalMissadeKunder();
			missadeNext = runSim(kassor+1, FRÖ).getAntalMissadeKunder();
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
	public void findOptimalRegisters() { //metod 3
		Random r = new Random(System.currentTimeMillis());
		int previousRun = 0;
		int check = 0;
		int change = 0;
		while (change < 9999) {
			check = findRegisters(r.nextInt(Integer.MAX_VALUE));
			if(check > previousRun) { //om mängden kassor är större än förra körningen
				change = 0; //nollställ räkning
				previousRun = check; //nytt högsta
			}
			change++;
		}
		System.out.println("Optimal mängd kassor beräknat till att vara "+check);
	}
}
