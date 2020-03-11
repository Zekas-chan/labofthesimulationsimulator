package lab5.state;

import java.util.ArrayList;

import lab5.Kund;
import lab5.Kund.KundID;
import lab5.classtemplates.state.*;
import lab5.event.EventQueue;

public class MarketState extends State {
	
	public KundID id;
	
	/*
	 * Variabler för parametrar.
	 */
	public int antalKassor;
	public int maxAntalKunder;
	public int snabbKöpsÖppettider;
	public int ankomstLambda;
	public int frö;
	
	/*
	 * Variabler för statistik.
	 */
	public int antalGenomfördaKöp;
	public int antalMissadeKunder;
	public int tidOverksamKassa;
	public int tidKassaKö;
	
	/*
	 * Variabler/referenser relevanta under körning.
	 */
	public ArrayList<Kund> kunderIButiken;
	public EventQueue eq;
	private int ledigaKassor;
	private boolean öppnaKassor;
	public static void main(String[] args) {
		

	}
	
	/*
	 * 
	 */
	public MarketState(int öppetTider, int kassor, int ankomstLambda, int frö, int maxKunder, EventQueue eq) {
		/*
		 * Parameterblock
		 */
		super(öppetTider); //State.timeMax
		this.eq = eq;
		this.kunderIButiken = new ArrayList<Kund>();
		this.antalKassor = kassor;
		this.snabbKöpsÖppettider = öppetTider;
		this.ankomstLambda = ankomstLambda;
		this.frö = frö;
		/*
		 * Initiering av statistikblocket.
		 */
		antalGenomfördaKöp	= 0;
		antalMissadeKunder 	= 0;
		tidOverksamKassa 	= 0;
		tidKassaKö 			= 0;
		/*
		 * Initiering av körningsvariabler;
		 */
		ledigaKassor = antalKassor;
		öppnaKassor = true;
		
		id = new KundID();
	}
	
	/**
	 * Returnerar true om det FINNS öppna kassor.
	 * False om det INTE finns öppna kassor.
	 * @return 
	 */
	public boolean öppnaKassor() {
		return öppnaKassor;
	}
	
	public class KundID{
		
		private int id = 0;
		
		public int getID() {
			
			id++;
			
			return id;
		}
		
	}

}
