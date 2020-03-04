package lab5.state;

import lab5.Kund;

public class MarketState extends State {

	private int freeRegisters; //lediga kassor
	private Kund[] CustomersInStore; //array som ska innehålla kunder.
	//statistik
	/*
	 * Dessa spelar roll senare för själva optimeringen. Lägger in det nu så att vi har koll på dom.
	 * -Philip
	 */
	private int idleRegisterTime; //summa tid kassor varit lediga.
	private int missedCustomers; //Missade kunder, om affären var full.
	private int timeSpentQueuing; //summa tid spenderad med att vänta på ledig kassa.
	private int customersServed; //antal kunder som betjänats.
	
	public MarketState(int time, int registers, int arrivalLambda, int gatherTime, int payTime, int seed, int storeCapacity) {
		super(time); //java gnäller om saker som finns i superklassen inte konstrueras där
		this.CustomersInStore = new Kund[storeCapacity];
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
