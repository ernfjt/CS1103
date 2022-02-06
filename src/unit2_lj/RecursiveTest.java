package unit2_lj;

import textio.TextIO;

public class RecursiveTest {

	public static void main(String[] args) {
		int input_int;
		System.out.println("Please input number (above one) : ");
		input_int = TextIO.getlnInt();

		System.out.println("Calculate the factorial:" + factorial(input_int));
		System.out.println("Calculate the fibonacci:" + fibonacci(input_int));
	}

	static int factorial(int i) {
		if (i != 0) {
			return i * factorial(i - 1);
		}
		return 1;
	}

	static int fibonacci(int i) {
		if (i == 0) {
			return 0;
		} else if (i == 1) {
			return 1;
		}
		return fibonacci(i - 1) + fibonacci(i - 2);
	}

}
