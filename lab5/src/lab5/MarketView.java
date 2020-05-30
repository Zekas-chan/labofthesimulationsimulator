package lab5;

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
	 * Konstruerar en MarketView som observerar ett MarketState.
	 * 
	 * @param ms   Referens till ett MarketState
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
	 * Skriver ut vilket event som inträffat. Om simuleringen är klar skrivs
	 * istället statistiken för simuleringen ut.
	 */
	public void update(Observable o, Object arg) throws ClassCastException {
		if (ms.isRunning()) {
			if (arg instanceof StopEvent || arg instanceof StartEvent) {
				sparseEvent((MarketEvent) arg);
			}

			else {
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
	 */
	private void eventDetails(MarketEvent a) {
			System.out.print(df.format(a.time) + "\t" + a.toString() + "\t" + (a instanceof StängerEvent ? "---" : a.kund.id) + "\t" + isOpen() + "\t"
					+ ms.getledigaKassor() + "\t" + df.format(ms.getTidOverksamKassa()) + "\t" + ms.getKunderIButiken().size() + "\t"
					+ ms.getAntalGenomfördaKöp() + "\t" + ms.getAntalMissadeKunder() + "\t" + ms.getUnikaKöandeKunder() + "\t"
					+ df.format(ms.getTidKassaKö()) + "\t" + ms.getKassaKö().size() + "\t" + köTillSträng());
		System.out.println();
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
		return ms.isÖppet() ? "Ö" : "S";
	}

	/*
	 * Hjälpmetod till update. Läser igenom kassakön och gör den till en array i en
	 * String. Returnerar en sträng med innehållet utformat som en array.
	 */
	private String köTillSträng() {
		String arraystr = "[";
		for (int i = 0; i < ms.getKassaKö().size(); i++) {
			arraystr += ms.getKassaKö().get(i).id;
			if (i < ms.getKassaKö().size() - 1) {
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
		System.out.println("\nRESULTAT\n========\n \n");
		System.out.println("1) Av " + ms.getUnikaKunder() + " kunder handlade " + ms.getAntalGenomfördaKöp() + " medan "
				+ ms.getAntalMissadeKunder() + " missades.\n");
		System.out.println(
				"2) Total tid " + ms.antalKassor + " kassor varit lediga: " + df.format(ms.getTidOverksamKassa()) + " te.");
		System.out.println("   Genomsnittlig ledig kassatid: " + df.format((ms.getTidOverksamKassa() / ms.antalKassor))
				+ " te (dvs " + df.format(((ms.getTidOverksamKassa() / ms.antalKassor) / ms.getFinalPaymentEvent()) * 100)
				+ "% av tiden från öppning tills sista kunden betalat).\n");
		System.out.println("3) Total tid " + ms.getUnikaKöandeKunder() + " tvingats köa: " + df.format(ms.getTidKassaKö())
				+ " te." + ". \n Genomsnittlig kötid: " + df.format((ms.getTidKassaKö() / ms.getUnikaKöandeKunder())) + " te.\n");
	}
}
