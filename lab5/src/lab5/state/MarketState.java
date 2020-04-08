package lab5.state;

import java.util.ArrayList;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.classtemplates.random.ExponentialRandomStream;
import lab5.classtemplates.random.UniformRandomStream;
import lab5.classtemplates.state.*;
import lab5.event.EventQueue;
import lab5.event.subevents.StartEvent;

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
	public double snabbKöpsÖppettider;
	public double ankomstLambda;
	public int frö;
	public double[] plockTid;
	public double[] betalTid;

	/*
	 * Variabler för statistik.
	 */
	public int antalGenomfördaKöp;
	public int unikaKunder; // antal kunder som kom, oavsett om dom missades eller inte
	public int unikaKöandeKunder; // antal unika kunder som behövde köa
	public int antalMissadeKunder;
	public double tidOverksamKassa;
	public double tidKassaKö;
	public double finalPaymentEvent;

	/*
	 * Variabler/referenser relevanta under körning.
	 */
	public ArrayList<Kund> kunderIButiken;
	public ArrayList<Kund> kassaKö;
	public EventQueue eq;
	public int ledigaKassor;
	private boolean öppnaKassor;
	public boolean öppet;
	private UniformRandomStream rM; //betalningar
	private UniformRandomStream rMB; //plock
	private ExponentialRandomStream rE; //ankomster
	
	/**
	 * Konstruerar ett nytt MarketState.
	 * 
	 * @param öppetTider	Anger hur länge butiken är öppen.
	 * @param kassor		Anger hur många kassor som är bemannade.
	 * @param ankomstLambda	Anger i hur snabb takt kunder anländer.
	 * @param frö			Fröet för slumpningstider som används.
	 * @param maxKunder		Maximalt antal kunder som kan vistas i butiken.
	 * @param plocktid		Anger ett intervall för hur länge det tar att plocka varor.
	 * @param betaltid		Anger ett intervall för hur länge det tar att betala.
	 */
	public MarketState(double öppetTider, int kassor, double ankomstLambda, int frö, int maxKunder, double[] plocktid, double[] betaltid) {
		/*
		 * Parameterblock
		 */
		super(öppetTider); // State.timeMax
		this.kunderIButiken = new ArrayList<Kund>();
		this.kassaKö = new ArrayList<Kund>();
		this.antalKassor = kassor;
		this.snabbKöpsÖppettider = öppetTider;
		this.ankomstLambda = ankomstLambda;
		this.frö = frö;
		this.maxAntalKunder = maxKunder;
		this.plockTid = plocktid;
		this.betalTid = betaltid;
		/*
		 * Initiering av statistikblocket.
		 */
		unikaKunder = 0;
		unikaKöandeKunder = 0;
		antalGenomfördaKöp = 0;
		antalMissadeKunder = 0;
		tidOverksamKassa = 0;
		tidKassaKö = 0;
		finalPaymentEvent = 0;
		/*
		 * Initiering av körningsvariabler;
		 */
		ledigaKassor = antalKassor;
		öppnaKassor = true;
		öppet = true;
		this.kundID = -1;
		
		/*
		 * Initiering av slumpmotorer.
		 */
		rM = new UniformRandomStream(plockTid[0], plockTid[1], frö);
		rMB = new UniformRandomStream(betalTid[0], betalTid[1], frö);
		rE = new ExponentialRandomStream(ankomstLambda, frö);
		
		/*
		 * Skapar händelsekön och lägger till ett StartEvent.
		 */
		eq = new EventQueue();
		StartEvent startEvent = new StartEvent(this, eq);
	}
	
	/**
	 * Startar simuleringen genom att köra första eventet i kön (StartEvent).
	 */
	public void start() {
		eq.getNext().execute();
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
	 * @param e Eventet som är står på tur.
	 */
	public void incomingEvent(Event e) {
		setChanged();
		notifyObservers(e);
	}
	
	public double getBetalTid() {
		double rmB = rMB.nextDouble();
		return rmB;
	}
	
	public double getPlockTid() {
		double rm = rM.nextDouble();
		return rm;
	}
	
	public double getAnkomst() {
		double re = rE.next();
		return re;
	}

}
