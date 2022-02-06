package ｗ1;

import textio.TextIO;

/**
 * Note:
 * This class defines a solution of the equation and out-put the result.
 * 
 * @author fujita
 *
 */
public class CompareRoot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int A, B, C;	// These values use for computing at subroutine.
		
		// Unless the user inputs N at confirming message, it will repeat this statement.
		do {
			try {
				// Require user input for each value.
				System.out.println("Imput number A: ");
				A = TextIO.getlnInt();
				System.out.println("Imput number B: ");
				B = TextIO.getlnInt();
				System.out.println("Imput number C: ");
				C = TextIO.getlnInt();
				
				// Call the function that computes these values.
				System.out.println(root(A, B, C));
				
			// If the root function throws the exception, the error message is displayed.
			} catch (IllegalArgumentException e) {
				System.out.println(e);
			}
		} while (input_YorN());	// Call the input_YorN() function whether it continues the root function.
		
		System.out.println("Thank you.");	// If the user input "n" or "N," this program exits.
		
	}

	/**
	 * Note:
	 * This subroutine represents whether the user input Yes or No.
	 * @return True means user input Y or y. / False means user input N or n.
	 */
	public static Boolean input_YorN() {
		String userConfirm = "";
		// Unless the user inputs Y, y, N, or n this roop will continue.
		while (!userConfirm.equals("Y") && !userConfirm.equals("y") && !userConfirm.equals("N")
				&& !userConfirm.equals("n")) {
			System.out.println("Could you continue [Y/N]?");
			userConfirm = TextIO.getlnString();		// The user is required Yes or No.
		}
		
		// If the user input "Y" or "y," the function returns true. Otherwise, the function returns false.
		if (userConfirm.equals("Y") || userConfirm.equals("y")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the larger of the two roots of the quadratic equation A*x*x + B*x + C
	 * = 0, provided it has any roots. If A == 0 or if the discriminant, B*B -
	 * 4*A*C, is negative, then an exception of type IllegalArgumentException is
	 * thrown.
	 *
	 * @param A
	 * @param B
	 * @param C
	 * @return The larger of the two roots of the quadratic equation
	 * @throws IllegalArgumentException
	 */
	static public double root(double A, double B, double C) throws IllegalArgumentException {
		if (A == 0) {
			throw new IllegalArgumentException("A can't be zero.");
		} else {
			double disc = B * B - 4 * A * C;
			if (disc < 0)
				throw new IllegalArgumentException("Discriminant < zero.");
			return (-B + Math.sqrt(disc)) / (2 * A);
		}
	}
}
