package lab5.event;

import java.util.ArrayList;

import lab5.Kund;
import lab5.classtemplates.event.Event;

/**
 * A queue with events
 * 
 * @author 
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
		list.add(k);
		reorganize();
	}
	
	
	/**
	 * Reorganizes the list
	 */
	public void reorganize() {
		
		//Tar bort alla null events från listan
		for (int i = 0; i < list.size()-1; i++) {
			Kund k = list.get(i);
			if (k.currentEvent == null) {
				list.remove(i);
			}
		}
		
	}
	
	public int size() {
		
		return list.size();
	}

	public boolean isEmpty() {
		
		if (list.size() == 0) {
			return true;
		}
		
		return false;
	}
	
	public void getNext() {
		list.remove(0);
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
