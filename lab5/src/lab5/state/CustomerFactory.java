package lab5.state;
/**
 * 
 * @author Philip Larsson, Patrik Grund
 *
 */
public class CustomerFactory {
	private int KundID;
	public CustomerFactory() {
		KundID = -1;
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
