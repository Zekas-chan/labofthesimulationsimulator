package lab5.state;

import lab5.Kund;
import lab5.classtemplates.state.*;

public class MarketState extends State {
	
	public int antalKassor;
	public int maxAntalKunder;
	public int snabbKöpsÖppettider;
	
	public int antalGenomfördaKöp;
	public int antalMissadeKunder;
	public int tidOverksamKassa;
	public int tidKassaKö;

	private int ledigaKassor;
	private Kund[] kunderIButiken;
	public static void main(String[] args) {
		

	}
	
	/**
	 * Constructor
	 */
	public MarketState(int time, int registers, int arrivalLambda, int gatherTime, int payTime, int seed, int storeCapacity) {
		super(time); //java gnäller om saker som finns i superklassen inte konstrueras där
		this.kunderIButiken = new Kund[storeCapacity];
	}
	
	/**
	 * Returnerar true om det FINNS öppna kassor.
	 * False om det INTE finns öppna kassor.
	 * @return 
	 */
	public boolean öppnaKassor() {
		
		return false;
	}

}
