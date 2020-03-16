package lab5.classtemplates.random;

import java.util.Random;


public class UniformRandomStream {

	private Random rand;
	private int lower, width;
	private double lowerD, widthD;
	
	public UniformRandomStream(int lower, int upper, long seed) {
		rand = new Random(seed);
		this.lower = lower;
		this.width = upper-lower;
	}
	
	public UniformRandomStream(double lower, double upper, long seed) {
		rand = new Random(seed);
		this.lowerD = lower;
		this.widthD = upper;
	}
	
	public UniformRandomStream(int lower, int upper) {
		rand = new Random();
	    this.lower = lower;
	    this.width = upper-lower;
	}
	
	public double nextDouble() {
		return lowerD+rand.nextDouble()*widthD;
	}
	
	public int next() {
	    return lower+rand.nextInt()*width;
	}
}
