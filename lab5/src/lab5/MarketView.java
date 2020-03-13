package lab5;

import lab5.classtemplates.event.*;
import java.util.Observable;
import java.util.Observer;

import lab5.classtemplates.view.View;
import lab5.event.MarketEvent;
import lab5.event.subevents.StartEvent;
import lab5.event.subevents.StopEvent;
import lab5.event.subevents.StängerEvent;
import lab5.state.MarketState;

/**
 * Concrete view of the program.
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class MarketView extends View implements Observer {
	MarketState ms;
	boolean optimizationMode;
	/**
	 * Konstruerar en MarketView som observerar en MarketState.
	 * @param ms Referens till ett MarketState
	 * @param mode Om satt till true skrivs bara resultatutskriften ut.
	 */
	public MarketView(MarketState ms, boolean mode) {
		this.ms = ms;
		ms.addObserver(this);
		optimizationMode = mode;
		if (!optimizationMode) {
			initiatePrinting(); // skriver ut raden som indikerar vad respektive informationssnutt är
		}

	}

	/**
	 * 
	 */
	public void update(Observable o, Object arg) throws ClassCastException {
		if (ms.isRunning() && !optimizationMode) {
			if(arg instanceof StopEvent || arg instanceof StartEvent) {
				sparseEvent((MarketEvent)arg);
			}
			
			else {	//Jack och Patrik la till en else
				eventDetails((MarketEvent) arg);
			}
			
		} else if (!ms.isRunning()) {
			results();
		}
	}
	/**
	 * Hjälpmetod för Update
	 */
	private void initiatePrinting() {
		System.out.println("Tid\tHändelse\tKund\tÖ/S\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö...]");
	}
	/**
	 * Hjälpmetod för update. Tar ett MarketEvent som argument för att kunna veta dess typ.
	 * @param a Ett MarketEvent. 
	 */
	private void eventDetails(MarketEvent a) {
		if(a instanceof StängerEvent) {
			System.out.print(ms.globalTime + "\t" + a.toString() + "\t" + "---" + "\t" + isOpen() + "\t"
					+ ms.ledigaKassor + "\t" + ms.tidOverksamKassa + "\t" + ms.kunderIButiken.size() + "\t"
					+ ms.antalGenomfördaKöp + "\t" + ms.antalMissadeKunder + "\t" + ms.unikaKöandeKunder + "\t"
					+ ms.tidKassaKö + "\t" + ms.kassaKö.size() + "\t" + köTillSträng());
			return;
		}
		System.out.print(ms.globalTime + "\t" + a.toString() + "\t" + a.kund.id + "\t" + isOpen() + "\t"
				+ ms.ledigaKassor + "\t" + ms.tidOverksamKassa + "\t" + ms.kunderIButiken.size() + "\t"
				+ ms.antalGenomfördaKöp + "\t" + ms.antalMissadeKunder + "\t" + ms.unikaKöandeKunder + "\t"
				+ ms.tidKassaKö + "\t" + ms.kassaKö.size() + "\t" + köTillSträng());
		
		System.out.println(); //ny rad
	}
	
	private void sparseEvent(MarketEvent e) {
		if(e instanceof StartEvent) {
			System.out.println(ms.globalTime+"\t"+e.toString());
		}else if(e instanceof StopEvent) {
			System.out.println(ms.globalTime+"\t"+e.toString());
		}
	}
	
	/**
	 * Hjälpmetod för Update.
	 * @return Ö om det är öppet, annars S för stängt.
	 */
	private String isOpen() {
		if (ms.öppet) {
			return "Ö";
		} else {
			return "S";
		}
	}
	/**
	 * Hjälpmetod till update. Läser igenom kassakön och gör den till en array i en String.
	 * @return En Sträng med innehållet utformat som en array.
	 */
	private String köTillSträng() {
		String arraystr = "[";
		for (int i = 0; i < ms.kassaKö.size(); i++) {
			arraystr += ms.kassaKö.get(i).id;
			if (i < ms.kassaKö.size() - 1) {
				arraystr += ", ";
			}
		}
		arraystr += "]";
		return arraystr;
	}
	/**
	 * Hjälpmetod för update. Om simuleringen kört klart skrivs simuleringens statistikvariabler ut.
	 */
	private void results() {
		System.out.println("RESULTAT\n========\n \n");
		System.out.println("1) Av " + ms.unikaKunder + " kunder handlade " + ms.antalGenomfördaKöp + " medan "
				+ ms.antalMissadeKunder + " missades.\n");
		System.out.println("2) Total tid " + ms.antalKassor + " kassor varit lediga: " + ms.tidOverksamKassa + " minuter.\n");
		System.out.println("3) Total tid " + ms.unikaKöandeKunder + " tvingats köa: " + ms.tidKassaKö +" minuter."
				+ ". \n Genomsnittlig kötid: " + (ms.tidKassaKö / ms.unikaKöandeKunder + "\n"));
	}
}
