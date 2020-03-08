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

}
