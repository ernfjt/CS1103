package unit4;

import java.util.Set;
import java.util.TreeSet;

import textio.TextIO;

/**
 * This class can be used as a "set calculator" for simple operations on sets of
 * non-negative integers.
 * 
 * @author fujita
 *
 */
public class SetCalc {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<Integer> A, B; // create two character set
		char op;
		A = readData();
		// if user input is not correct throw an exception.
		if (TextIO.peek() != '+' && TextIO.peek() != '-' && TextIO.peek() != '*')
			throw new IllegalArgumentException("Operatpr is not correct");
		TextIO.skipBlanks();
		op = TextIO.getAnyChar(); // Get operation
		TextIO.skipBlanks();
		B = readData();
		//
		switch (op) {
		case '+':
			A.addAll(B); // Computing the union of A and B
			break;
		case '*':
			A.retainAll(B); // Computing the intersection of A and B
			break;
		case '-':
			A.removeAll(B); // Computing the difference of A and B
		}

		System.out.println("A is :" + A);
	}

	/**
	 * This class read character from user's input.
	 * 
	 * @return set number
	 */
	public static Set<Integer> readData() {
		Set<Integer> setData = new TreeSet<Integer>(); // To represent sets of non-negative integers.
		System.out.println("input:");
		TextIO.skipBlanks();
		// If the user doesn't input the expected character, the program throws an
		// exception.
		if (TextIO.peek() != '[')
			throw new IllegalArgumentException("Start of input is [");
		TextIO.getAnyChar();
		TextIO.skipBlanks();
		while (TextIO.peek() != ']') { // Continuing the program until the user input the expected character.
			TextIO.skipBlanks();
			// Only number are added at the setData.
			if (TextIO.peek() == ',') {
				TextIO.getAnyChar();
			} else if (Character.isDigit(TextIO.peek())) {
				setData.add(Integer.valueOf(TextIO.getInt()));
			} else {
				throw new IllegalArgumentException("input data is not correct");
			}
			TextIO.skipBlanks();
		}
		TextIO.getAnyChar();

		return setData;
	}

}
