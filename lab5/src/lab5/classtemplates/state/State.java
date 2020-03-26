package lab5.classtemplates.state;

import java.util.Observable;

/**
 * Represents the state of the program in a concrete way Representerar
 * programmets State på ett konkret sätt.
 * 
 * @author Philip Larsson, Patrik Grund, Jack Florberg, Johan Mölder
 *
 */
public class State extends Observable {
	public boolean run; // Nödbromsen.
	public double globalTime; // Tiden simuleringen just nu är vid
	public double timeMax; // Hur länge simuleringen ska pågå.

	/**
	 * Konstruerar ett State.
	 * 
	 * @param time Starttiden för tillståndet.
	 */
	public State(double time) {
		timeMax = time;
		run = true;
	}

	/**
	 * Kollar om simuleringen körs.
	 * 
	 * @return True om den körs, annars false.
	 */
	public boolean isRunning() {
		return run;
	}
}
