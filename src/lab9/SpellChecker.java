package lab9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.JFileChooser;

/**
 * This class check spelling for the English words from the user's file using
 * Java Collection Framework. And then, it returns suggested another word.
 * 
 * @author anonymous
 *
 */
public class SpellChecker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashSet<String> wordset = new HashSet<>(); // create HushSet

		try {
			Scanner filein = new Scanner(new File("words.txt")); // reading the words from words.txt
			while (filein.hasNext()) {
				String tk = filein.next();
				// storing the words in a HashSet<String> after converting word to lower case
				wordset.add(tk.toLowerCase());
			}

			Scanner userfilein = new Scanner(getInputFileNameFromUser()); // reading the user's file
			userfilein.useDelimiter("[^a-zA-Z]+"); // skip over any non-letter characters in the file

			while (userfilein.hasNext()) {
				String userwords = userfilein.next(); // reading each word
				userwords = userwords.toLowerCase(); // converting word to lower case
				System.out.print(userwords + " : ");

				TreeSet<String> suggestset = new TreeSet<>(); // create TreeSet
				suggestset = corrections(userwords, wordset); // call corrections() method to check the words

				// If the corrections() method returns an empty set, the program outputs the
				// message "(no suggestions)"
				if (suggestset.isEmpty()) {
					System.out.println("(no suggestions)");
				}

				// printing all suggested words
				int count = 0;
				for (String suggestwords : suggestset) {
					System.out.print(suggestwords);
					if (count < suggestset.size() - 1)
						System.out.print(", ");
					else
						System.out.print("\n");
					count++;
				}

				// wordset.add(tk.toLowerCase());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

//		System.out.println(wordset.size());

	}

	/**
	 * @param badWord    the user's input word
	 * @param dictionary
	 * @return suggested word set
	 */
	static TreeSet<String> corrections(String badWord, HashSet<String> dictionary) {
		TreeSet<String> wordsVariation = new TreeSet<String>();
		String subStr1, subStr2, checkStr;

		for (int i = 0; i < badWord.length(); i++) {
			if (!dictionary.contains(badWord)) {
				subStr1 = badWord.substring(0, i);
				subStr2 = badWord.substring(i + 1);

				// Delete any one of the letters from the misspelled word.
				checkStr = subStr1 + subStr2;
				if (dictionary.contains(checkStr)) {
					wordsVariation.add(checkStr);
				}

				// Change any letter in the misspelled word to any other letter.
				for (char ch = 'a'; ch <= 'z'; ch++) {
					checkStr = subStr1 + ch + subStr2;
					if (dictionary.contains(checkStr)) {
						wordsVariation.add(checkStr);
					}
				}

				// Insert any letter at any point in the misspelled word.
				subStr1 = badWord.substring(0, i);
				subStr2 = badWord.substring(i);
				for (char ch = 'a'; ch <= 'z'; ch++) {
					checkStr = subStr1 + ch + subStr2;
					if (dictionary.contains(checkStr)) {
						wordsVariation.add(checkStr);
					}
				}

				// Insert a space at any point in the misspelled word (and check that both of
				// the words that are produced are in the dictionary)
				char ch = ' ';
				checkStr = subStr1 + ch + subStr2;
				if (dictionary.contains(subStr1) && dictionary.contains(subStr2)) {
					wordsVariation.add(checkStr);
				}
			}
		}
		// Swap any two neighboring characters in the misspelled word.
		for (int i = 1; i < badWord.length(); i++) {
			if (!dictionary.contains(badWord)) {
				subStr1 = badWord.substring(0, i - 1);
				char ch1 = badWord.charAt(i - 1);
				char ch2 = badWord.charAt(i);
				subStr2 = badWord.substring(i + 1);
				checkStr = subStr1 + ch2 + ch1 + subStr2;
				if (dictionary.contains(checkStr)) {
					wordsVariation.add(checkStr);
				}
			}

		}

		return wordsVariation;

	}

	/**
	 * Lets the user select an input file using a standard file selection dialog
	 * box. If the user cancels the dialog without selecting a file, the return
	 * value is null.
	 * 
	 * @return
	 */
	static File getInputFileNameFromUser() {
		JFileChooser fileDialog = new JFileChooser();
		fileDialog.setDialogTitle("Select File for Input");
		int option = fileDialog.showOpenDialog(null);
		if (option != JFileChooser.APPROVE_OPTION)
			return null;
		else
			return fileDialog.getSelectedFile();
	}

}
