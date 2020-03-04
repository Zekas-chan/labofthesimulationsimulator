package lab5.state.substates;

public class MarketState {
	
	public int antalKassor;
	public int maxAntalKunder;
	public int snabbKöpsÖppettider;
	
	public int antalGenomfördaKöp;
	public int antalMissadeKunder;
	public int tidOverksamKassa;
	public int tidKassaKö;

	public static void main(String[] args) {
		

	}
	
	/**
	 * Constructor
	 */
	public MarketState() {
		
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
