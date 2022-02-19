package unit2_lj;

import textio.TextIO;

/**
 * This class computes factorial(N) and fibonacci(N) for a given non-negative
 * integer N.
 * 
 * @author fujita
 *
 */
public class RecursiveTest {

	/**
	 * Call factorial(N) and fibonacci(N) functions
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int input_int;
		do {
			System.out.println("Please input number (above one) : ");
			input_int = TextIO.getlnInt(); // user input integer
		} while (input_int < 0);

		System.out.println("Calculate the factorial:" + factorial(input_int)); // call factorial(N) function
		System.out.println("Calculate the fibonacci:" + fibonacci(input_int)); // call factorial(N) function
	}

	/**
	 * The method computes factorial(N) and return result.
	 * 
	 * @param i Non-negative integer that user input.
	 * @return
	 */
	static int factorial(int i) {
		if (i != 0) {
			return i * factorial(i - 1);
		}
		return 1;
	}

	/**
	 * The method computes fibonacci(N) and return result.
	 * 
	 * @param i Non-negative integer that user input.
	 * @return
	 */
	static int fibonacci(int i) {
		if (i == 0) {
			return 0;
		} else if (i == 1) {
			return 1;
		}
		return fibonacci(i - 1) + fibonacci(i - 2);
	}

}
