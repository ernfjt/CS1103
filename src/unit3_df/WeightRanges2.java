package unit3_df;

/**
 * Define the behavior of a given weight and height.
 * @author fujita
 *
 */
public class WeightRanges2 {

	double height;
	double weight;

	/**
	 * Create constructor
	 * @param h height
	 * @param w weight
	 */
	public WeightRanges2(double h, double w) {
		this.height = h;
		this.weight = w;
	}

	/**
	 * Get BMI calculated using height and weight and then return the result.
	 * @return BMI
	 */
	public double getBMI2() {
		return this.weight / (this.height * 0.01) / (this.height * 0.01);
	}

	/**
	 * Get the ideal body weight calculated using height and then return the result.
	 * @return ideal body weight
	 */
	public double healthWeight() {
		return (this.height * 0.01) * (this.height * 0.01) * 22;
	}

}
