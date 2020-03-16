package lab5.classtemplates.random;

import java.util.Random;

public class ExponentialRandomStream {
	
	private Random rand;
	private double lambda;
	  
	public ExponentialRandomStream(double lambda, long seed) {
	  	rand = new Random(seed);
	  	this.lambda = lambda;
	}
	  
	public ExponentialRandomStream(double lambda) {
		rand = new Random();
	    this.lambda = lambda;
	}
	  
	public double next() {
	  	return -Math.log(rand.nextDouble())/lambda;
	}
	
	public static void main(String[] args) {
		ExponentialRandomStream r = new ExponentialRandomStream(1, 1234);
		for(int i = 0; i < 10; i++) {
			System.out.println("theory check: "+r.next());
		}
		//ger alltid samma sekvens med det här fröet.
	}
}
