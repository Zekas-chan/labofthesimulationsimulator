package lab5.classtemplates.state;

import java.util.Observable;

/**
 * Represents the state of the program
 * in a concrete way
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
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
	 * Konstruerar ett State.
	 * @param time Starttiden för tillståndet.
	 */
	public State(int time) {
		timeMax = time;
		run = true;
	}
	/**
	 * Kollar om simuleringen körs.
	 * @return True om den körs, annars false.
	 */
	public boolean isRunning() {
		return run;
	}
}
