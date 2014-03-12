package progin.chapter8_sorting;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {

		int[] numbers = new int[] { 5,1,3,8,2,9,0 };
		for (int i = 0; i < numbers.length; i++) { System.out.print(numbers[i]); }
		System.out.println();
		sort(numbers);
		for (int i = 0; i < numbers.length; i++) { System.out.print(numbers[i]); }
		
	}
	
	public static void sort(int[] numbers) {
		sort(numbers, 0, numbers.length-1);
	}
	
	private static void sort(int[] numbers, int lower, int upper) {
		if (lower >= upper) { return; }  // at most one element left; trivially sorted
		// recursive sort left and right
		int mid = (lower + upper) / 2;
		sort(numbers, lower, mid);
		sort(numbers, mid+1, upper);
		// merge left and right
		int[] leftCopy = Arrays.copyOfRange(numbers, lower, mid+1);
		int[] rightCopy = Arrays.copyOfRange(numbers, mid+1, upper+1);
		int leftNext = 0;
		int rightNext = 0;
		// for each index between lower and upper, determine which numbers to put
		for (int i = lower; i <= upper; i++) {
			if (leftNext >= leftCopy.length) {
				// left copy empty
				numbers[i] = rightCopy[rightNext];
				rightNext++;
			} else if (rightNext >= rightCopy.length) {
				// right copy empty
				numbers[i] = leftCopy[leftNext];
				leftNext++;
			} else if (rightCopy[rightNext] < leftCopy[leftNext]) {
				// take from right
				numbers[i] = rightCopy[rightNext];
				rightNext++;
			} else {
				// take from left (having the equal case here makes it stable)
				numbers[i] = leftCopy[leftNext];
				leftNext++;
			}
		}
	}

}
