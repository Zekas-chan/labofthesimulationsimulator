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
	public final int antalKassor;
	public final int maxAntalKunder;
	public final double snabbKöpsÖppettider;
	public final double ankomstLambda;
	public final int frö;
	public final double[] plockTid;
	public final double[] betalTid;

	/*
	 * Variabler för statistik.
	 */
	int antalGenomfördaKöp;
	int unikaKunder; // antal kunder som kom, oavsett om dom missades eller inte
	int unikaKöandeKunder; // antal unika kunder som behövde köa
	int antalMissadeKunder;
	double tidOverksamKassa;
	double tidKassaKö;
	double finalPaymentEvent;

	/*
	 * Variabler/referenser relevanta under körning.
	 */
	private ArrayList<Kund> kunderIButiken;
	private ArrayList<Kund> kassaKö;
	private EventQueue eq;
	private int ledigaKassor;
	private boolean öppnaKassor;
	private boolean öppet;
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
		this.setKunderIButiken(new ArrayList<Kund>());
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
		setÖppet(true);
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
	
	//Setters
	public void setKunderIButiken(ArrayList<Kund> kunderIButiken) {
		this.kunderIButiken = kunderIButiken;
	}
	
	public void setÖppet(boolean öppet) {
		this.öppet = öppet;
	}
	
	public void incUnikaKunder() {
		unikaKunder++;
	}
	
	public void incUnikaKöandeKunder() {
		unikaKöandeKunder++;
	}
	
	public void incLedigaKassor(int antal) {
		ledigaKassor += antal;
	}
	
	public void decLedigaKassor() {
		ledigaKassor--;
	}
	
	public void incMissadeKunder() {
		antalMissadeKunder++;
	}
	
	public void incAntalGenomfördaKöp() {
		antalGenomfördaKöp++;
	}
	
	public void addTidKassaKö(double time) {
		tidKassaKö += time;
	}
	
	public void addTidOverksamKassa(double time) {
		tidOverksamKassa += time;
	}
	
	public void setFinalPaymentEvent(double time) {
		finalPaymentEvent = time;
	}
	
	//======================================================
	//Getters
	public int getAntalGenomfördaKöp() {
		return antalGenomfördaKöp;
	}
	
	public double getTidOverksamKassa() {
		return tidOverksamKassa;
	}
	
	public int getledigaKassor() {
		return ledigaKassor;
	}
	
	public int getID() {
		kundID++;
		return this.kundID;
	}
	
	public double getSnabbKöpsÖppettider() {
		return snabbKöpsÖppettider;
	}

	public ArrayList<Kund> getKunderIButiken() {
		return kunderIButiken;
	}
	
	public boolean isÖppet() {
		return öppet;
	}
	
	public int getAntalMissadeKunder() {
		return antalMissadeKunder;
	}
	
	public int getUnikaKöandeKunder() {
		return unikaKöandeKunder;
	}
	
	public int getUnikaKunder() {
		return unikaKunder;
	}
	
	public double getTidKassaKö() {
		return tidKassaKö;
	}
	
	/**
	 * Genererar en betaltid.
	 * @return
	 */
	public double getBetalTid() {
		double rmB = rMB.nextDouble();
		return rmB;
	}
	
	/**
	 * Genererar en plocktid.
	 * @return
	 */
	public double getPlockTid() {
		double rm = rM.nextDouble();
		return rm;
	}
	
	/**
	 * Genererar en ankomsttid.
	 * @return
	 */
	public double getAnkomst() {
		double re = rE.next();
		return re;
	}

	public ArrayList<Kund> getKassaKö() {
		return kassaKö;
	}

	public double getFinalPaymentEvent() {
		return finalPaymentEvent;
	}

}
