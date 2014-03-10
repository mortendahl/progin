package progin.chapter7_recursion;

public class BinarySearch {

	public static void main(String[] args) {
		//int[] numbers = new int[] {};
		//int[] numbers = new int[] { 1 };
		//int[] numbers = new int[] { 1, 4 };
		int[] numbers = new int[] { 1, 4, 5, 7, 8, 9 };
		//int[] numbers = new int[] { 1, 1, 4, 4, 5, 5, 7, 7, 8, 8, 9, 9 };
		for (int x = 0; x <= 10; x++) {
			System.out.print(x + ": ");
			System.out.print(" " + searchRecursive(numbers, x));
			System.out.print(" " + searchIterative(numbers, x));
			System.out.print(" " + searchIterativeOptimised(numbers, x));
			System.out.println();
			
			System.out.println();
		}
	}
	
	// straight-forward recursive approach
	public static int searchRecursive(int[] numbers, int target) {
		return searchRecursive(numbers, target, 0, numbers.length-1);
	}
	
	private static int searchRecursive(int[] numbers, int target, int lower, int upper) {
		if (! (lower <= upper)) {
			// not found
			return -1;
		}
		int mid = (lower + upper) / 2;
		if (numbers[mid] == target) {
			// found it
			return mid;
		} else if (numbers[mid] < target) {
			// look in right sub-array
			return searchRecursive(numbers, target, mid+1, upper);
		} else { // numbers[mid] > target
			// look in left sub-array
			return searchRecursive(numbers, target, lower, mid-1);
		}
	}
	
	// iterative approach based on (tail-) recursive approach
	public static int searchIterative(int[] numbers, int target) {
		return searchIterative(numbers, target, 0, numbers.length-1);
	}
	
	private static int searchIterative(int[] numbers, int target, int lower, int upper) {
		while (lower <= upper) {
			int mid = (lower + upper) / 2;
			if (numbers[mid] == target) {
				// found it
				return mid;
			} else if (numbers[mid] < target) {
				// look in right sub-array
				lower = mid+1;
			} else { // numbers[mid] > target
				// look in left sub-array
				upper = mid-1;
			}
		}
		return -1;
	}
	
	// optimised iterative approach, using half the comparisons
	public static int searchIterativeOptimised(int[] numbers, int target) {
		return searchIterativeOptimised(numbers, target, 0, numbers.length-1);
	}
		
	private static int searchIterativeOptimised(int[] numbers, int target, int lower, int upper) {
		while (lower < upper) {
			int mid = (lower + upper) / 2;
			if (numbers[mid] < target) {
				lower = mid+1;
			} else {
				upper = mid;
			}
		}
		if (lower == upper) {
			return (numbers[lower] == target ? lower : -1);
		}
		return -1;
	}

}
