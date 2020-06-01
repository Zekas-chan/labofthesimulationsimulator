package lab5.event;

import java.util.ArrayList;
import java.util.Collections;
import lab5.classtemplates.event.Event;

/**
 * En generell händelsekö.
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class EventQueue {

	private ArrayList<Event> list = new ArrayList<Event>(); // intern datastruktur

	/**
	 * Lägger till ett event i kön och omorganiserar den sedan.
	 * 
	 * @param e Eventet som ska läggas till.
	 */
	public void add(Event e) {
		getList().add(e);
		reorganize();
	}

	/*
	 * Omorganiserar listan.
	 */
	private void reorganize() {

		// Sortering
		ArrayList<Double> sortedTime = new ArrayList<Double>();
		ArrayList<Event> sortedEvent = new ArrayList<Event>();

		for (int i = 0; i < list.size(); i++) {

			try {
				Event e = list.get(i);
				double time = e.time();
				sortedTime.add(time);
			} catch (NullPointerException e) {
				break;
			}

		}

		Collections.sort(sortedTime);

		for (int i = 0; i < sortedTime.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (sortedTime.get(i) == list.get(j).time()) {
					sortedEvent.add(list.get(j));
					break;
				}

			}
		}

		list = sortedEvent;

		// Debug
//		for(int i = 0; i < list.size(); i++) {
//			System.out.print(list.get(i).toString()+", ");
//		}
//		System.out.println();
	}

	/**
	 * Tar bort ett specifikt event ur kön.
	 * 
	 * @param e Eventet som ska avlägsnas.
	 */
	public void remove(Event e) {
		list.remove(e);
		reorganize();
	}

	/**
	 * Beskriver storleken på den interna datastrukturen.
	 * 
	 * @return Storleken som en int.
	 */
	public int size() {

		return getList().size();
	}

	/**
	 * Beskriver huruvida den interna datalagringen innehåller något.
	 * 
	 * @return True om något finns, annars false.
	 */
	public boolean isEmpty() {

		if (getList().size() == 0) {
			return true;
		}

		return false;
	}

	/**
	 * Hämtar det första Eventet som står i kö.
	 */
	public Event getNext() {
		return getList().get(0);
	}

	/**
	 * Åtkomstmetod för den interna listan.
	 * 
	 * @return En ArrayList.
	 */
	public ArrayList<Event> getList() {
		return list;
	}
}
