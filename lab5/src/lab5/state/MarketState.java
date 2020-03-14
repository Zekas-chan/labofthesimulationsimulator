package lab5.state;

import java.util.ArrayList;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.classtemplates.state.*;
import lab5.event.EventQueue;
import lab5.event.MarketEvent;

/**
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
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
	public int unikaKunder; // antal kunder som kom, oavsett om dom missades eller inte
	public int unikaKöandeKunder; // antal unika kunder som behövde köa
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

	/**
	 * Konstruerar ett nytt MarketState.
	 * 
	 * @param öppetTider    Anger hur länge butiken är öppen.
	 * @param kassor        Anger hur många kassor som är bemannade.
	 * @param ankomstLambda Anger i hur snabb takt kunder anländer.
	 * @param frö           Fröet för slumpningstider som används.
	 * @param maxKunder     Maximalt antal kunder som kan vistas i butiken.
	 * @param eq            Referens till en EventQueue. @param(saknas) int[]
	 *                      plockTider Anger ett intervall för hur länge det tar att
	 *                      plocka varor. @param(saknas) int[] betalningsTider Anger
	 *                      ett intervall för hur länge det tar att betala.
	 */
	public MarketState(int öppetTider, int kassor, int ankomstLambda, int frö, int maxKunder, EventQueue eq) {
		/*
		 * Parameterblock
		 */
		super(öppetTider); // State.timeMax
		this.eq = eq;
		this.kunderIButiken = new ArrayList<Kund>();
		this.kassaKö = new ArrayList<Kund>();
		this.antalKassor = kassor;
		this.snabbKöpsÖppettider = öppetTider;
		this.ankomstLambda = ankomstLambda;
		this.frö = frö;
		this.maxAntalKunder = maxKunder;
		/*
		 * Initiering av statistikblocket.
		 */
		unikaKunder = 0;
		unikaKöandeKunder = 0;
		antalGenomfördaKöp = 0;
		antalMissadeKunder = 0;
		tidOverksamKassa = 0;
		tidKassaKö = 0;
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
		return this.kundID;
	}

	/**
	 * Returnerar true om det FINNS öppna kassor. False om det INTE finns öppna
	 * kassor.
	 * 
	 * @return
	 */
	public boolean öppnaKassor() {
		if (ledigaKassor == 0) {
			öppnaKassor = false;
		} else {
			öppnaKassor = true;
		}
		return öppnaKassor;
	}

	/**
	 * Skickar en uppdatering till MarketView med det Event som kommer att inträffa.
	 * 
	 * @param e Eventet som är nästkommande.
	 */
	public void incomingEvent(Event e) {

		setChanged();
		notifyObservers(e);

	}

}
