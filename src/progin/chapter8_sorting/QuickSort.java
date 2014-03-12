package progin.chapter8_sorting;

public class QuickSort {

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
	
	// two optimisations possible:
	//  - randomise pivot selection (pick any index in lower..upper and swap with numbers[upper])
	//  - instead of recursing on both sub-arrays, we recurse on the smallest and iterate to the other 
	private static void sort(int[] numbers, int lower, int upper) {
		if (lower >= upper) { return; }  // at most one element left; trivially sorted
		// partition lower..splitIndex and splitIndex+2..upper (splitIndex+1 is pivot)
		int pivotPosition = upper;
		int pivot = numbers[pivotPosition];
		int splitIndex = lower-1;
		for (int i = lower; i < upper; i++) {
			if (numbers[i] < pivot) {
				// increment splitIndex and swap with numbers[splitIndex]
				splitIndex += 1;
				int tmp = numbers[splitIndex];
				numbers[splitIndex] = numbers[i];
				numbers[i] = tmp;
			} else {
				// do nothing
			}
		}
		// swap in pivot
		numbers[pivotPosition] = numbers[splitIndex+1];
		numbers[splitIndex+1] = pivot;
		// recursively sort left and right
		sort(numbers, lower, splitIndex);
		sort(numbers, splitIndex+2, upper);
	}

}
