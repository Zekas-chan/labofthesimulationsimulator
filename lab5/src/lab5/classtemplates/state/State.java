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
	
	public boolean run;	//The emergancy break
	public int globalTime;
	public int timeMax;
	
	
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
