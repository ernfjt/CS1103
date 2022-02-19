package turing;

/**
 * This class represents Turing machine tapes. A Turing machine works on a
 * "tape" that is used for both input and output. The tape is made up of little
 * squares called cells lined up in a horizontal row that stretches,
 * conceptually, off to infinity in both directions. Each cell can hold one
 * character. Initially, the content of a cell is a blank space. One cell on the
 * tape is considered to be the current cell. This is the cell where the machine
 * is located. As a Turing machine computes, it moves back and forth along the
 * tape and the current cell changes.
 * 
 * A Turing machine tape can be represented by a doubly-linked list where each
 * cell has a pointer to the previous cell (to its left) and to the next cell
 * (to its right). The pointers will allow the machine to advance from one cell
 * to the next cell on the left or to the next cell on the right.
 * 
 * @author anonymous
 *
 */
public class Tape {

	private Cell currentCell;

	/**
	 * Creating a constructor creates a tape that initially consists of a single
	 * cell. The cell contains a blank space, and the current pointer points to it.
	 */
	public Tape() {
		Cell newCell = new Cell();
		newCell.content = ' ';
		newCell.prev = null;
		newCell.next = null;
		currentCell = newCell;
	}

	/**
	 * @return The pointer of the current cell.
	 */
	public Cell getCurrentCell() {
		return currentCell;
	}

	/**
	 * @return The char from the current cell.
	 */
	public char getContent() {
		return currentCell.content;
	}

	/**
	 * Changes the char in the current cell to the specified value.
	 * 
	 * @param ch : The char in the current cell
	 */
	public void setContent(char ch) {
		currentCell.content = ch;
	}

	/**
	 * Moves the current cell one position to the left along the tape.If the current
	 * cell is the leftmost cell that exists, then a new cell must be created and
	 * added to the tape at the left of the current cell, and then the current cell
	 * pointer can be moved to point to the new cell.
	 */
	public void moveLeft() {

		if (currentCell.prev == null) { // the current cell is the leftmost cell that exists
			Cell newCell = new Cell(); // create instance
			newCell.next = currentCell; // the new cell pointer point to the current cell
			newCell.content = ' '; // contents of the new cell be a blank space
			newCell.prev = null; // the new cell is the leftmost cell, so the previous pointer is null
			currentCell.prev = newCell; // the current cell point to the new cell
		}
		currentCell = currentCell.prev; // moves the current cell one position to the left along the tape

	}

	/**
	 * Moves the current cell one position to the right along the tape.If the
	 * current cell is the rightmost cell that exists, then a new cell must be
	 * created and added to the tape at the right of the current cell, and then the
	 * current cell pointer can be moved to point to the new cell.
	 */
	public void moveRight() {
		if (currentCell.next == null) { // the current cell is the rightmost cell that exists
			Cell newCell = new Cell(); // create instance
			newCell.content = ' '; // contents of the new cell be a blank space
			newCell.next = null; // the new cell is the rightmost cell, so the next pointer is null
			newCell.prev = currentCell; // the new cell pointer point to the current cell
			currentCell.next = newCell; // the current cell point to the new cell
		}
		currentCell = currentCell.next; // moves the current cell one position to the right along the tape
	}

	/**
	 * Returns a String consisting of the chars from all the cells on the tape, read
	 * from left to right, except that leading or trailing blank characters should
	 * be discarded. The current cell pointer should not be moved by this method; it
	 * should point to the same cell after the method is called as it did before.
	 * You can create a different pointer to move along the tape and get the full
	 * contents.
	 * 
	 * @return A String consisting of the chars from all the cells on the tape
	 */
	public String getTapeContents() {
		Cell tempCell = currentCell;
		String tapeContents = "";
		while (tempCell.prev != null) { // move to the leftmost cell
			tempCell = tempCell.prev;
		}

		while (tempCell.next != null) {
			tapeContents += tempCell.content; // take out all contents and add them to the variable
			tempCell = tempCell.next; // move next
		}
		tapeContents = tapeContents.trim(); // remove blank from leading or trailing characters
		return tapeContents;
	}
}
