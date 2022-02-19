package unit3_df;

import java.util.Arrays;

public class Stack {
	private int items[];
	private int top = 0;
	private int capacity;

	// Create Constructor
	Stack(int size) {
		items = new int[size];
		capacity = size;
		top = -1;
	}

	public void push(int x) {

		if (isFull()) {
			items = Arrays.copyOf(items, 2 * items.length);
		}
		System.out.println("Insert " + x);
		items[++top] = x;
	}

	public int pop() {
		if (isEmpty())
			throw new IllegalStateException("Can't pop from an empty stack.");
		return items[top--];
	}

	public Boolean isEmpty() {
		return top == -1;
	}

	public Boolean isFull() {
		return top == capacity - 1;
	}

	public void printStack() {
		for (int i = 0; i <= top; i++) {
			System.out.print(items[i] + ", ");
		}
	}

	public static void main(String[] args) {
		Stack stack = new Stack(5);
		// stack.printStack();
		stack.push(1);
		stack.push(2);
		stack.push(3);

		System.out.print("Stack: ");
		stack.printStack();
		stack.pop();
		System.out.print("\nAftter pop out: ");
		stack.printStack();
	}

}
