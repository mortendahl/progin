package progin.chapter8_sorting;

public class InsertionSort {

	public static void main(String[] args) {

		int[] numbers = new int[] { 5,1,3,8,2,9,0 };
		for (int i = 0; i < numbers.length; i++) { System.out.print(numbers[i]); }
		System.out.println();
		sort(numbers);
		for (int i = 0; i < numbers.length; i++) { System.out.print(numbers[i]); }
		
	}
	
	public static void sort(int[] numbers) {
		// numbers[0..i] already sorted increasingly
		for (int i = 1; i < numbers.length; i++) {
			// find right place for current by moving from right to left
			int j = i;
			int current = numbers[j];
			// run from right to left as long as we're inside array and strictly smaller than left neighbour
			while (j > 0 && current < numbers[j-1]) {
				numbers[j] = numbers[j-1];
				j -= 1;
			}
			numbers[j] = current;
		}
	}

}
