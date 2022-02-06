package ｗ1;

import java.util.Arrays;

/**
 * Note: Compare the run time of two arrays used different types of a sort. The
 * first array is named sortList1 and uses insert sort, while the second array
 * is named sortList2 and uses Arrays.sort().
 * 
 * Result of run time in seconds: 
 * 	array size 1000 
 * 		sortList1 = 0.002
 * 		sortList2 =	0.001
 *  array size 10000
 *  	sortList1 = 0.029
 *  	sortList2 = 0.004
 *  array size 100000
 * 		sortList1 = 2.555
 * 		sortList2 = 0.026
 * 
 * @author fujita
 *
 */
public class SortAlgorithm {

	/**
	 *  
	 * @param args
	 */
	public static void main(String[] args) {
		int[] sortList1, sortList2; // Two types of arrays
		final int INDEX_VALUE = 100000; // Size of array

		sortList1 = new int[INDEX_VALUE];
		sortList2 = new int[INDEX_VALUE];

		// Fill in the array with random integer.
		for (int i = 0; i < sortList1.length; i++) {
			sortList1[i] = (int) (Integer.MAX_VALUE * Math.random());
		}

		// Copy array from sortList1 to sortList2.
		sortList2 = Arrays.copyOf(sortList1, INDEX_VALUE);

		// System.out.println("SortList1 = "+Arrays.toString(sortList1));
		// System.out.println("SortList2 = "+Arrays.toString(sortList2));

		insertionSort(sortList1); // Call method to use insert sort for the first array.

		long startTime = System.currentTimeMillis();			// For getting start time of sorting process.
		Arrays.sort(sortList2);									// Using Arrays.sort() for the second array.
		long runTime = System.currentTimeMillis() - startTime;	// For getting run time of sorting process.
		// System.out.println("SortReqult2 = "+Arrays.toString(sortList2));
		
		// Print out the result time in seconds of using Arrays.sort().
		System.out.println("Running time in seconds(sortList2) = " + runTime / 1000.0);
	}

	/**
	 * Note: Sort the array arraylist into increasing order.
	 * 
	 * @param arraylist the array for using insert sort
	 */
	static void insertionSort(int[] arraylist) {

		long startTime = System.currentTimeMillis(); // For getting start time of sorting process.

		for (int i = 1; i < arraylist.length; i++) {
			// Assume that item arraylist[0], arraylist[1],・・・arraylist[i-1]
			// have already been sorted. Insert arraylist[i]
			// into the sorted part of the list.

			int temp = arraylist[i]; // The item to be inserted
			int loc = i - 1; // Start at end of list.

			while (loc >= 0 && arraylist[loc] > temp) {
				arraylist[loc + 1] = arraylist[loc]; // Bump item from arraylist[loc] up to loc+1.
				loc = loc - 1; // Go on to next location.
			}
			arraylist[loc + 1] = temp; // Put temp in last vacated space.
		}
		long runTime = System.currentTimeMillis() - startTime; // For getting run time of sorting process.
		// System.out.println("SortReqult1 = "+Arrays.toString(arraylist));
		
		// Print out the result time in seconds of using insert sort.
		System.out.println("Running time in seconds(sortList1) = " + runTime / 1000.0);
	}

}
