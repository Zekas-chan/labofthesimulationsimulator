package lab5.classtemplates.random;
import java.util.Random;

/**
 * Generated time in a random number
 * described in minutes
 * 
 * @author 
 *
 */
public class RandomMin {
	
	private int rand;

	public static void main(String[] args) {
		

	}
	
	public RandomMin(int lower, int upper) {
		
		Random randomGenerator = new Random();
		rand = randomGenerator.nextInt(upper) + lower;
	}
	
	public int getRand() {
		return rand;
	}

}
