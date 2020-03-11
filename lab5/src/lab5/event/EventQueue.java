package lab5.event;

import java.util.ArrayList;

import lab5.classtemplates.event.Event;

/**
 * A queue with events
 * 
 * @author 
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
		list.add(e);
		reorganize();
	}
	
	
	/**
	 * Reorganizes the list
	 */
	public void reorganize() {
		
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
	
	public void addEvent(Event event) {
		list.add(event);
	}
	
	public boolean hasNext() {
		return (list.size() > 0) ? true : false;
		//return isEmpty(); //fungerar också
	}
}
