package lab5;

import lab5.classtemplates.event.*;
import java.util.Observable;
import java.util.Observer;

import lab5.classtemplates.view.View;
import lab5.state.MarketState;

/**
 * Concrete view of the program
 * WORK IN PROGRESS
 * @author 
 *
 */
public class MarketView extends View implements Observer {
	MarketState ms;
	public MarketView(MarketState ms){
		this.ms = ms;
		ms.addObserver(this);
		initiatePrinting(); //skriver ut raden som indikerar vad respektive informationssnutt är
	}
	
	/*
	 * Utskriftsmall:
	 * Tid Händelse  Kund  Öppet/Stängt  led    ledT    I     $    :-(   köat    köT   köar  [Kassakö..]
	 */
	public void update(Observable o, Object arg) throws ClassCastException {
		if(ms.isRunning()) {
			eventDetails((Event)arg);
		}else {
			results();
		}
	}
	
	private void initiatePrinting() {
		System.out.println("Tid		Händelse	Kund	Ö/S		led		ledT	I	$	:-(		köat	köT		köar	[Kassakö...]");
	}
	
	private void eventDetails(Event a) {
		System.out.print(ms.globalTime+"	"+a.toString()+"	"+a.kund.id+"	"+isOpen()+"	"+ms.öppnaKassor()+"	"+ms.tidOverksamKassa+"	"+ms.kunderIButiken.size()+"	"+ms.antalGenomfördaKöp+"	"+ms.antalMissadeKunder+"	"+ms.unikaKöandeKunder+"	"+ms.tidKassaKö+"	"+ms.KassaKö.length+"	"+köTillSträng());
	}
	
	private String isOpen() {
		if(ms.butikÖppen) {
			return "Ö";
		}else {
			return "S";
		}
	}
	
	private String köTillSträng() {
		String arraystr = "[";
		for(int i = 0; i < ms.KassaKö.length; i++) {
			arraystr += KassaKö[i].kund.id+", ";
		}
		arraystr += "]";
		return arraystr;
	}
	
	public void results() {
		System.out.println("RESULTAT\n======\n \n");
		System.out.println("1) Av "+ms.unikaKunder+" kunder handlade "+ms.antalGenomfördaKöp+" medan "+ms.antalMissadeKunder+" missades.\n");
		System.out.println("Total tid "+ms.antalKassor+" varit lediga: "+ms.tidOverksamKassa+"\n");
		System.out.println("3) Total tid "+ms.unikaKöandeKunder+" tvingats köa: "+ms.tidKassaKö+". \n Genomsnittlig kötid: "+(ms.tidKassaKö/ms.unikaKöandeKunder+"\n"));
	}

}
