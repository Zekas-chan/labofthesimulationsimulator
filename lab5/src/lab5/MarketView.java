package lab5;

import lab5.classtemplates.event.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
	DecimalFormat df;

	/**
	 * Konstruerar en MarketView som observerar en MarketState.
	 * 
	 * @param ms   Referens till ett MarketState
	 * @param mode Om satt till true skrivs bara resultatutskriften ut.
	 */
	public MarketView(MarketState ms) {
		this.ms = ms;
		ms.addObserver(this);
		df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		df.setMinimumFractionDigits(2);
		parameterPrint();
		informationColumns(); // skriver ut raden som indikerar vad respektive informationssnutt är

	}

	/**
	 * Skrivet ut vilket event som inträffat. Om simuleringen är klar skrivs
	 * istället statistiken för simuleringen ut.
	 */
	public void update(Observable o, Object arg) throws ClassCastException {
		if (ms.isRunning()) {
			if (arg instanceof StopEvent || arg instanceof StartEvent) {
				sparseEvent((MarketEvent) arg);
			}

			else { // Jack och Patrik la till en else
				eventDetails((MarketEvent) arg);
			}

		} else if (!ms.isRunning()) {
			results();
		}
	}

	private void parameterPrint() {
		System.out.println("PARAMETRAR\n==========");
		System.out.println("Antal kassor, N: \t\t" + ms.antalKassor);
		System.out.println("Max som ryms, M: \t\t" + ms.maxAntalKunder);
		System.out.println("Ankomsthastighet, lambda: \t" + ms.ankomstLambda);
		System.out.println("Plocktider, [P_min..Pmax]: \t[" + ms.plockTid[0] + ", " + ms.plockTid[1] + "]");
		System.out.println("Betaltider, [K_min..Kmax]: \t[" + ms.betalTid[0] + ", " + ms.betalTid[1] + "]");
		System.out.println("Frö, f: \t\t\t" + ms.frö);
	}

	/*
	 * Hjälpmetod för Update
	 */
	private void informationColumns() {
		System.out.println("\nFÖRLOPP\n=======");
		System.out.println("Tid\tHändelse\tKund\tÖ/S\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö...]");
	}

	/*
	 * Hjälpmetod för update. Tar ett MarketEvent som argument för att kunna veta
	 * dess typ.
	 * 
	 * @param a Ett MarketEvent.
	 */
	private void eventDetails(MarketEvent a) {
		if (a instanceof StängerEvent) {
			System.out.print(df.format(a.time) + "\t" + a.toString() + "\t" + "---" + "\t" + isOpen() + "\t"
					+ ms.ledigaKassor + "\t" + df.format(ms.tidOverksamKassa) + "\t" + ms.kunderIButiken.size() + "\t"
					+ ms.antalGenomfördaKöp + "\t" + ms.antalMissadeKunder + "\t" + ms.unikaKöandeKunder + "\t"
					+ df.format(ms.tidKassaKö) + "\t" + ms.kassaKö.size() + "\t" + köTillSträng());
		} else {
			System.out.print(df.format(a.time) + "\t" + a.toString() + "\t" + a.kund.id + "\t" + isOpen() + "\t"
					+ ms.ledigaKassor + "\t" + df.format(ms.tidOverksamKassa) + "\t" + ms.kunderIButiken.size() + "\t"
					+ ms.antalGenomfördaKöp + "\t" + ms.antalMissadeKunder + "\t" + ms.unikaKöandeKunder + "\t"
					+ df.format(ms.tidKassaKö) + "\t" + ms.kassaKö.size() + "\t" + köTillSträng());
		}
		System.out.println(); // ny rad
	}

	private void sparseEvent(MarketEvent e) {
		if (e instanceof StartEvent) {
			System.out.println(df.format(e.time) + "\t" + e.toString());
		} else if (e instanceof StopEvent) {
			System.out.println(df.format(e.time) + "\t" + e.toString());
			results();
		}
	}

	/*
	 * Hjälpmetod för update.
	 * 
	 * @return Ö om det är öppet, annars S för stängt.
	 */
	private String isOpen() {
		if (ms.öppet) {
			return "Ö";
		} else {
			return "S";
		}
	}

	/*
	 * Hjälpmetod till update. Läser igenom kassakön och gör den till en array i en
	 * String. Returnerar en sträng med innehållet utformat som en array.
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

	/*
	 * Hjälpmetod för update. Om simuleringen kört klart skrivs simuleringens
	 * statistikvariabler ut.
	 */
	private void results() {
		System.out.println("RESULTAT\n========\n \n");
		System.out.println("1) Av " + ms.unikaKunder + " kunder handlade " + ms.antalGenomfördaKöp + " medan "
				+ ms.antalMissadeKunder + " missades.\n");
		System.out.println("2) Total tid " + ms.antalKassor + " kassor varit lediga: " + df.format(ms.tidOverksamKassa)
				+ " minuter.");
		System.out.println("   Genomsnittlig ledig kassatid: "+df.format((ms.tidOverksamKassa / ms.antalKassor))+" te (dvs "+df.format(((ms.tidOverksamKassa / ms.antalKassor) / ms.finalEvent)*100)+"% av tiden från öppning tills sista kunden betalat).\n");
		System.out.println("3) Total tid " + ms.unikaKöandeKunder + " tvingats köa: " + df.format(ms.tidKassaKö)
				+ " te." + ". \n Genomsnittlig kötid: " + df.format((ms.tidKassaKö / ms.unikaKöandeKunder)) + " te.\n");
	}
}
