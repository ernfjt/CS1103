package unit3_df;

public class Queue {
	int size = 5;
	int items[] = new int[size];
	int head, tail;

	Queue() {
		head = -1;
		tail = -1;
	}

	boolean isFull() {
		if (head == 0 && tail == size - 1) {
			return true;
		}
		return false;
	}

	boolean isEmpty() {
		if (head == -1)
			return true;
		else
			return false;
	}

	public void enqueue(int x) {

		if (isFull()) {
			System.out.println("Queue is full");
		} else {
			if (head == -1) {
				head = 0;
			}
			tail++;
			items[tail] = x;
			System.out.println("Insert " + x);
		}
	}

	public int dequeue() {
		int element;
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return (-1);
		} else {
			element = items[head];

			if (head >= tail) {
				head = -1;
				tail = -1;
			} else {

				head++;
			}
			System.out.println(element + " Deleted");
			return (element);
		}
	}

	void display() {
		int i;
		if (isEmpty()) {
			System.out.println("Empty Queue");
		} else {
			// display the front of the queue
			System.out.println("\nFront index-> " + head);

			// display element of the queue
			System.out.println("Items -> ");
			for (i = head; i <= tail; i++)
				System.out.print(items[i] + "  ");

			// display the rear of the queue
			System.out.println("\nRear index-> " + tail);
		}
	}

	public static void main(String[] args) {
		// create an object of Queue class
		Queue q = new Queue();
		q.dequeue();

		for (int i = 1; i < 6; i++) {
			q.enqueue(i);
		}

		// 6th element can't be added to queue because queue is full
		q.enqueue(6);

		q.display();

		//q.dequeue();

		//q.display();
	}

}
