package lab5.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * A queue with events
 * 
 * @author 
 *
 */
public class EventQueue implements Queue<Event>{
	
	ArrayList<Event> list = new ArrayList<Event>();

	public static void main(String[] args) {
		

	}
	
	
	/**
	 * Reorganizes the list
	 */
	public void reorganize() {
		
	}
	

	public int size() {
		
		return 0;
	}

	public boolean isEmpty() {
		
		return false;
	}

	public boolean contains(Object o) {
		
		return false;
	}

	public Iterator<Event> iterator() {
		
		return null;
	}

	public Object[] toArray() {
		
		return null;
	}

	public <T> T[] toArray(T[] a) {
		
		return null;
	}

	public boolean remove(Object o) {
		
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		
		return false;
	}

	public boolean addAll(Collection<? extends Event> c) {
		
		return false;
	}

	public boolean removeAll(Collection<?> c) {
		
		return false;
	}

	public boolean retainAll(Collection<?> c) {
		return false;
	}

	public void clear() {
		
	}

	public boolean add(Event e) {
		
		return false;
	}

	public boolean offer(Event e) {
		
		return false;
	}

	public Event remove() {
		
		return null;
	}

	public Event poll() {
		
		return null;
	}

	public Event element() {
		
		return null;
	}

	public Event peek() {
		
		return null;
	}

}
