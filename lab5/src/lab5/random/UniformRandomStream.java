package lab5.random;

import java.util.Random;


public class UniformRandomStream {

	private Random rand;
	private int lower, width;
	
	public UniformRandomStream(int lower, int upper, long seed) {
		rand = new Random(seed);
		this.lower = lower;
		this.width = upper-lower;
	}
	
	public UniformRandomStream(int lower, int upper) {
		rand = new Random();
	    this.lower = lower;
	    this.width = upper-lower;
	}
	
	public int next() {
	    return lower+rand.nextInt()*width;
	}
}
