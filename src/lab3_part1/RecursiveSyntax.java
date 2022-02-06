package lab3_part1;

/**
 * This program implements the following rules that is represented BNF format to
 * generate random sentences.
 * 
 * <sentence> ::= <simple_sentence> [ <conjunction> <sentence> ]
 * 
 * <simple_sentence> ::= <noun_phrase> <verb_phrase>
 * 
 * <noun_phrase> ::= <proper_noun> | <determiner> [ <adjective> ]...
 * <common_noun> [ who <verb_phrase> ]
 * 
 * <verb_phrase> ::= <intransitive_verb> | <transitive_verb> <noun_phrase> | is
 * <adjective> |believes that <simple_sentence>
 * 
 * <conjunction> ::= and | or | but | because
 * 
 * <proper_noun> ::= Fred | Jane | Richard Nixon | Miss America
 * 
 * <common_noun> ::= man | woman | fish | elephant | unicorn
 * 
 * <determiner> ::= a | the | every | some
 * 
 * <adjective> ::= big | tiny | pretty | bald
 * 
 * <intransitive_verb> ::= runs | jumps | talks | sleeps
 * 
 * <transitive_verb> ::= loves | hates | sees | knows | looks for | finds
 * 
 * The program generates and outputs one random sentence every three seconds
 * until it is halted (for example, by typing Control-C in the terminal window
 * where it is running).
 * 
 * @author fujita
 */
public class RecursiveSyntax {

	// The last seven rules in the BNF list are put in the array to implement.
	static final String[] conjunctions = { "and", "or", "but", "because" };

	static final String[] proper_nouns = { "Fred", "Jane", "Richard Nixon", "Miss America" };

	static final String[] common_nouns = { "man", "woman", "fish", "elephant", "unicorn" };

	static final String[] determiners = { "a", "the", "every", "some" };

	static final String[] adjectives = { "big", "tiny", "pretty", "bald" };

	static final String[] intransitive_verbs = { "runs", "jumps", "talks", "sleeps" };

	static final String[] transitive_verbs = { "loves", "hates", "sees", "knows", "looks for", "finds" };

	/**
	 * Main routine call the randomSentence subroutine.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		while (true) {
			randomSentence();
			System.out.println(".\n\n");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * The randomSentence subroutine calls the randomNounPhrase subroutine, randomVerbPhrase
	 * subroutine, or itself.
	 */
	static void randomSentence() {
		randomNounPhrase();
		randomVerbPhrase();
		if (Math.random() > 0.75) {
			System.out.print(" " + randomItem(conjunctions));
			randomSentence();
		}
	}

	/**
	 * The randomNounPhrase subroutine calls the randomVerbPhrase.
	 */
	static void randomNounPhrase() {

		if (Math.random() > 0.2) {
			System.out.print(" " + randomItem(proper_nouns));
		} else {
			if (Math.random() > 0.5)
				System.out.print(" " + randomItem(determiners));
			while (Math.random() > 0.75) {
				System.out.print(" " + randomItem(adjectives));
			}
		}
		System.out.print(" " + randomItem(common_nouns));
		if (Math.random() > 0.2) {
			System.out.print(" who");
			randomVerbPhrase();
		}
	}

	/**
	 * The randomVerbPhrase subroutine calls the randomNounPhrase subroutine or
	 * itself.
	 */
	static void randomVerbPhrase() {

		if (Math.random() > 0.75) {
			System.out.print(" " + randomItem(intransitive_verbs));
		} else if (Math.random() > 0.5) {
			System.out.print(" " + randomItem(transitive_verbs));
			randomNounPhrase();
		} else if (Math.random() > 0.2) {
			System.out.print(" is " + randomItem(adjectives));
		} else {
			System.out.print(" believes that");
			randomNounPhrase();
			randomVerbPhrase();
		}
	}

	/**
	 * To return random text from each array.
	 * @param listOfStrings
	 * @return
	 */
	static String randomItem(String[] listOfStrings) {
		int i;
		i = (int) (Math.random() * listOfStrings.length);
		return listOfStrings[i];
	}
}
