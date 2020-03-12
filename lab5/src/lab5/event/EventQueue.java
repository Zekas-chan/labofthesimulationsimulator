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
	
	private ArrayList<Kund> list = new ArrayList<Kund>();

	public static void main(String[] args) {

	}
	/**
	 * Lägger till ett event i kön och omorganiserar den sedan.
	 * @param e Eventet som ska läggas till.
	 */
	public void add(Kund k) {
		getList().add(k);
		reorganize();
	}
	
	
	/**
	 * Omorganiserar listan.
	 */
	public void reorganize() {
		
		//Tar bort alla null events från listan
		for (int i = 0; i < getList().size(); i++) {
			Kund k = getList().get(i);
			if (k.currentEvent == null) {
				getList().remove(i);
			}
		}
		
		//Sortering
		ArrayList<Integer> sortedTime = new ArrayList<Integer>();
		ArrayList<Kund> sortedKund = new ArrayList<Kund>();
		
		for (int i = 0; i < getList().size(); i++) {
			Kund k = getList().get(i);
			int time = k.currentEvent.time();
			
			sortedTime.add(time);
			
		}
		
		Collections.sort(sortedTime);
		
		for (int i = 0; i < sortedTime.size(); i++) {
			for (int j = 0; j < getList().size(); j++) {
				if (sortedTime.get(i) == getList().get(j).currentEvent.time()) {
					sortedKund.add(getList().get(j));
					break;
				}
				
			}
		}
		
		list = sortedKund;
		
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
	public ArrayList<Kund> getList() {
		return list;
	}
	public int antalBetalaEvent() {
		int räknare = 0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).currentEvent instanceof BetalaEvent) {
				räknare++;
			}
		}
		return räknare;
		
	}
	
	/* Detta har vi redan
	public void addEvent(Event event) {
		list.add(event);
	}
	
	public boolean hasNext() {
		return (list.size() > 0) ? true : false;
		//return isEmpty(); //fungerar också
	}*/
}
