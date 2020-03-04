package lab5.state;

/**
 * Represents the state of the program
 * in a concrete way
 * 
 * @author 
 *
 */
public class State {
	
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
		
	}

}
