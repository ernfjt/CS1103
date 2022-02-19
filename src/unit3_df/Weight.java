package unit3_df;

/**
 * This class can get BMI and ideal weight using the instance method object.
 * @author fujita
 *
 */
public class Weight {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create a new instance and pass the height and weight.
		WeightRanges2 normalweight = new WeightRanges2(160, 50);

		// Calling two instance methods and print each result.
		System.out.println("Your BMI is " + normalweight.getBMI2());
		System.out.println("Your ideal weight is " + normalweight.healthWeight());
	}

}
