package lab5.classtemplates.state;

import java.util.Observable;

/**
 * Represents the state of the program
 * in a concrete way
 * 
 * @author 
 *
 */
public class State extends Observable {
	
	private boolean run;	//The emergancy break
	public int globalTime;
	private int timeMax;
	
	
//	public static void main(String[] args) {
//		
//
//	}
	
	/**
	 * Constructor
	 * @param time
	 */
	public State(int time) {
		timeMax = time;
		run = true;
	}
	public boolean isRunning() {
		return run;
	}
}
