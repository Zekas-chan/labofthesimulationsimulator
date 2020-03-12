package lab5.event;

import java.util.ArrayList;
import java.util.Collections;

import lab5.Kund;
import lab5.classtemplates.event.Event;
import lab5.event.subevents.BetalaEvent;

/**
 * A queue with events
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class EventQueue{
	
	private ArrayList<Event> list = new ArrayList<Event>();

	public static void main(String[] args) {

	}
	/**
	 * Lägger till ett event i kön och omorganiserar den sedan.
	 * @param e Eventet som ska läggas till.
	 */
	public void add(Event e) {
		getList().add(e);
		reorganize();
	}
	
	
	/**
	 * Omorganiserar listan.
	 */
	public void reorganize() {
		
		//Sortering
		ArrayList<Integer> sortedTime = new ArrayList<Integer>();
		ArrayList<Event> sortedEvent = new ArrayList<Event>();
		
		for (int i = 0; i < list.size(); i++) {
			
			try {
				Event e = list.get(i);
				int time = e.time();
				sortedTime.add(time);
			}
			catch(NullPointerException e) {
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
		System.out.println(list + " print i eventqueue");
	}
	
	public void remove(Event e) {
		list.remove(e);
		reorganize();
	}
	
	public int size() {
		
		return getList().size();
	}

	public boolean isEmpty() {
		
		if (getList().size() == 0) {
			return true;
		}
		
		return false;
	}
	
	public void getNext() {
		getList().get(0);
	}
	public ArrayList<Event> getList() {
		return list;
	}
	public int antalBetalaEvent() {
		int räknare = 0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i)instanceof BetalaEvent) {
				räknare++;
			}
		}
		return räknare;
		
	}
}
