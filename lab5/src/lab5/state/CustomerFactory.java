package lab5.state;
/**
 * Skapar en kund och tilldelar ett ID till kunden.
 * @author Philip Larsson, Patrik Grund
 *
 */
public class CustomerFactory {
	private int KundID;
	public CustomerFactory() {
		KundID = -1; //inkrementeras av getID; måste börja på -1 då första IDt är 0.
	}
	
	private int getID() {
		KundID++;
		return KundID;
	}
	public Kund createCustomer(MarketState ms) {
		Kund k = new Kund(ms);
		k.id = getID(); // tilldela identifierare
		return k;
	}
}
