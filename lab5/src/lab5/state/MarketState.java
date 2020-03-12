package lab5.state;

import java.util.ArrayList;

import lab5.Kund;
import lab5.Kund.KundID;
import lab5.classtemplates.state.*;
import lab5.event.EventQueue;

public class MarketState extends State {
	
	public int kundID;
	
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
	public int unikaKunder; //antal kunder som kom, oavsett om dom missades eller inte
	public int unikaKöandeKunder; //antal unika kunder som behövde köa
	public int antalMissadeKunder;
	public int tidOverksamKassa;
	public int tidKassaKö;
	
	/*
	 * Variabler/referenser relevanta under körning.
	 */
	public ArrayList<Kund> kunderIButiken;
	public ArrayList<Kund> kassaKö;
	public EventQueue eq;
	public int ledigaKassor;
	private boolean öppnaKassor;
	public boolean öppet;
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
		this.kassaKö = new ArrayList<Kund>();
		this.antalKassor = kassor;
		this.snabbKöpsÖppettider = öppetTider;
		this.ankomstLambda = ankomstLambda;
		this.frö = frö;
		/*
		 * Initiering av statistikblocket.
		 */
		unikaKunder			= 0;
		unikaKöandeKunder	= 0;
		antalGenomfördaKöp	= 0;
		antalMissadeKunder 	= 0;
		tidOverksamKassa 	= 0;
		tidKassaKö 			= 0;
		/*
		 * Initiering av körningsvariabler;
		 */
		ledigaKassor = antalKassor;
		öppnaKassor = true;
		öppet = true;
		
		this.kundID = 0;
	}
	
	public int getID() {
		kundID++;
		System.out.println(kundID + " id");
		return this.kundID;
	}
	
	/**
	 * Returnerar true om det FINNS öppna kassor.
	 * False om det INTE finns öppna kassor.
	 * @return 
	 */
	public boolean öppnaKassor() {
		if (ledigaKassor == 0) {
			 öppnaKassor = false;
		}
		else {
			 öppnaKassor = true;
		}
		return öppnaKassor;
	}

}
